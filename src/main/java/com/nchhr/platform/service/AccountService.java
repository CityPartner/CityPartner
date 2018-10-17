package com.nchhr.platform.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.nchhr.platform.Enum.CodeEnum;
import com.nchhr.platform.dao.AccountDao;
import com.nchhr.platform.dao.WeChatUserDao;
import com.nchhr.platform.entity.PhoneCodeEntity;
import com.nchhr.platform.entity.PlatformUserEntity;
import com.nchhr.platform.entity.WeChatUserEntity;
import com.nchhr.platform.util.CodeUtils;
import com.nchhr.platform.util.MD5Utils;
import com.nchhr.platform.util.PhoneCodeUtils;
import com.nchhr.platform.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AccountService {

    @Resource
    AccountDao accountDao;

    @Resource
    WeChatUserDao weChatUserDao;

    @Autowired
    CookiesService cookiesService;

    public String login(String phone, String pwd, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        PlatformUserEntity platformUserEntity = accountDao.loadByPhone(phone);
        if (platformUserEntity == null) {
            //用户不存在
            return "2";
        }
        if (platformUserEntity != null) {
            if (pwd.equals(platformUserEntity.getPassword())) {
                //保存session
                session.setAttribute("PlatformInfo", platformUserEntity);
                //保存cookies
                cookiesService.saveCookies(platformUserEntity.getP_id(), response, request);
                //登录成功
                return "1";
            } else {
                //密码错误
                return "3";
            }
        } else {
            //系统异常
            return "4";
        }
    }

    public String getCode(String phone, HttpSession session) {
        PlatformUserEntity platformUserEntity = accountDao.loadByPhone(phone);
        if (platformUserEntity == null) {
            //获取随机验证码，保存在session中
            CodeUtils codeUtils = new CodeUtils();
            PhoneCodeUtils phoneCodeUtils = new PhoneCodeUtils();

            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            //替换成你的AK
            final String accessKeyId = CodeEnum.AccessId.getValue();//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = CodeEnum.AccessKeySecre.getValue();//你的accessKeySecret，参考本文档步骤2
            //获得随机验证码
            String usercode = codeUtils.createRandom(true, 6);
            //保存phone code到session中
            if (phoneCodeUtils.save(phone, usercode, session) == false) {
                //4代表获取验证码频繁
                return "4";
            }
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            try {
                DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            } catch (ClientException e) {
                e.printStackTrace();
            }
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(CodeEnum.SignName.getValue());
            //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
            request.setTemplateCode(CodeEnum.SMSTemplateCode.getValue());
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam("{\"code\":\"" + usercode + "\"}");
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            // request.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = null;
            try {
                sendSmsResponse = acsClient.getAcsResponse(request);
            } catch (ClientException e) {
                e.printStackTrace();
                //3未知错误
                System.out.println("验证码发送失败");
                return "3";
            }
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                System.out.println("验证码发送成功！");
                return "1";
            }


        }
        if (platformUserEntity != null) {
            //2代表已注册
            return "2";
        }

        return "3";
    }

    //清除验证码
    public String deleteCode(String phone, HttpSession session) {
        PhoneCodeEntity phonecode = (PhoneCodeEntity) session.getAttribute(phone);
        if (phonecode == null) {
            //清除成功
            System.out.println("清除成功");
            return "1";
        } else {
            try {
                session.removeAttribute(phone);
                System.out.println("清除成功");
                return "1";
            } catch (Exception e) {
                //清除失败
                System.out.println("清除失败");
                System.out.println(e.getMessage());
                return "2";

            }

        }
    }

    @Transactional
    public String RegisterLogin(String userPhone, String code, String pwd, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        MD5Utils md5Utils = new MD5Utils();
        //再次判断注册手机号已被注册（是否）
        PlatformUserEntity platformUserEntity = accountDao.loadByPhone(userPhone);
        if (platformUserEntity == null) {
            PhoneCodeEntity phoneCode = (PhoneCodeEntity) session.getAttribute(userPhone);
            if (phoneCode == null) {
                //验证码过期
                return "5";
            }
            if (userPhone.equals(phoneCode.getPhone())) {
                String mdCode;
                try {
                    mdCode = md5Utils.MD5Encode(code, "utf8");

                } catch (Exception e) {
                    return "4";
                }
                if (mdCode.equals(phoneCode.getCode())) {
                    //1成功
                    //添加商城用户
                    CodeUtils codeUtils = new CodeUtils();
                   /* //获取微信id
                    WeChatUserEntity weChatUserEntity = (WeChatUserEntity)session.getAttribute("weChatUser");
//                    WeChatUserEntity weChatUserEntity = new WeChatUserEntity();

                    weChatUserDao.addWeCharUser(weChatUserEntity.getOpenid(),
                            weChatUserEntity.getCity(),
                            weChatUserEntity.getCountry(),
                            weChatUserEntity.getGroupid(),
                            weChatUserEntity.getHeadimgurl(),
                            weChatUserEntity.getLanguage(),
                            weChatUserEntity.getNickname(),
                            weChatUserEntity.getProvince(),
                            weChatUserEntity.getQr_scene(),
                            weChatUserEntity.getQr_scene_str(),
                            weChatUserEntity.getRemark(),
                            weChatUserEntity.getSex(),
                            weChatUserEntity.getSubscribe(),
                            weChatUserEntity.getSubscribe_scene(),
                            weChatUserEntity.getSubscribe_time(),
                            weChatUserEntity.getTagid_list(),
                            weChatUserEntity.getUnionid());*/

                    String Pid = "M" + codeUtils.createRandom(false, 16);
                    System.out.println(Pid);

                    boolean b = accountDao.RegisterLogin(Pid, userPhone, pwd,TimeUtils.getTime(),"123456");
                    System.out.println("是否添加成功平台用户："+b);
                    if (b == true) {
                                PlatformUserEntity platformUserEntity1 = accountDao.loadByid(Pid);
                                //保存cookies
                                cookiesService.saveCookies(Pid,response,request);
                                session.setAttribute("PlatformInfo", platformUserEntity1);
                                //1代表成功
                                return "1";


                    } else {
                        //4其他错误
                        return "4";
                    }

                } else {
                    //3验证码错误
                    return "3";
                }
            } else {
                //2手机号错误
                return "2";
            }
        } else {
            return "4";
        }


    }

    public String  ResetPassword(String phone,  HttpServletResponse response, HttpSession session) {

        PlatformUserEntity platformUserEntity = accountDao.loadByPhone(phone);

        if (platformUserEntity != null){
            //获取随机验证码，保存在session中
            CodeUtils codeUtils = new CodeUtils();
            PhoneCodeUtils phoneCodeUtils = new PhoneCodeUtils();

            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            //替换成你的AK
            final String accessKeyId = CodeEnum.AccessId.getValue();//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = CodeEnum.AccessKeySecre.getValue();//你的accessKeySecret，参考本文档步骤2
            //获得随机验证码
            String usercode = codeUtils.createRandom(true, 6);
            //保存phone code到session中
            if (phoneCodeUtils.save(phone, usercode, session) == false) {
                //4代表获取验证码频繁
                return "4";
            }
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            try {
                DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            } catch (ClientException e) {
                e.printStackTrace();
            }
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(CodeEnum.SignName.getValue());
            //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
            request.setTemplateCode(CodeEnum.SMSTemplateCode.getValue());
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam("{\"code\":\"" + usercode + "\"}");
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            // request.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = null;
            try {
                sendSmsResponse = acsClient.getAcsResponse(request);
            } catch (ClientException e) {
                e.printStackTrace();
                //3未知错误
                System.out.println("验证码发送失败");
                return "3";
            }
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                System.out.println("验证码发送成功！");
                return "1";
            }
        }else {
            //2该用户不存在，请去注册
            return "2";
        }
        return "3";

    }

    public String ChangePassword(String newpwd, String code, String newphone, HttpSession session) {
        System.out.println("开始修改密码！");
        MD5Utils md5Utils = new MD5Utils();
        //再次判断注册手机号已被注册（是否）
        PlatformUserEntity platformUserEntity = accountDao.loadByPhone(newphone);
        if (platformUserEntity == null) {
            //2代表手机未被注册注册
            return "2";
        } else {

            PhoneCodeEntity phoneCode = (PhoneCodeEntity) session.getAttribute(newphone);
            if (phoneCode == null) {
                //5验证码过期
                return "5";
            }
//            System.out.println("phoneCode"+phoneCode);
//            System.out.println(newphone.equals(phoneCode.getPhone()));
            if (newphone.equals(phoneCode.getPhone()) == false) {
                //手机输入错误！
                return "6";
            }


            if (phoneCode.getCode() != null) {
                String mdCode;
                try {
                    mdCode = md5Utils.MD5Encode(code, "utf8");

                } catch (Exception e) {
                    //4修改失败
                    return "4";

                }
                if (mdCode.equals(phoneCode.getCode())) {
                    if (accountDao.updatePwd(platformUserEntity.getP_id(), newpwd)) {
                        //1成功
                        return "1";
                    }
                    //修改失败
                    return "4";
                } else {
                    //验证码错误
                    return "3";
                }
            } else {
                //修改失败
                return "4";
            }
        }
    }
}
