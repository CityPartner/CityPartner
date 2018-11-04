package com.nchhr.platform.service;

import com.nchhr.platform.ModelVo.WalletProVo;
import com.nchhr.platform.dao.InvestDao;
import com.nchhr.platform.dao.WalletDao;
import com.nchhr.platform.entity.ProjectWalletIncome;
import com.nchhr.platform.entity.ProjectWalletWithdraw;
import com.nchhr.platform.util.GetCodeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class WalletService {
    @Resource
    WalletDao walletDao;
    @Resource
    InvestDao investDao;

    private final static int MIN_WITHDRAW_AMOUNT = 10;

    /*
     *获取用户收入list
     * @author HWG
     */
    public List<ProjectWalletIncome> getAllIncome(String user_id){
        return walletDao.getAllIncomeList(user_id);
    }


    /*
     *获取用户提现list
     * @author HWG
     */
    public List<ProjectWalletWithdraw> getAllWithdraw(String user_id){
        return walletDao.getAllWithdrawList(user_id);
    }

    /*
      分项目获取用户钱包余额
      @author HWG
     */
    public List<WalletProVo> getAllAmount(String P_id){
        return walletDao.getWallet(P_id);
    }

    //获取项目钱包金额
    public String getWalletAmount(String userId, String projectId) {

        return walletDao.getWalletAmount(userId, projectId);
    }

    /*
        添加一条钱包提现申请记录
        @author JC
        @version 1.0
        @return
            0：尚有申请未处理
            1：提现申请成功
            2：申请人实名格式出错
            3：申请提现金额低于最低额度
            4：申请提现金额高于最高额度
            5：提现金额格式出错
            6：验证码错误
     */
    public String addWalletApplyRecord(String userId, String projectId, String applyName, String withdrawAmount, String phone, String verifyCode, HttpSession httpSession) {
        // 是否有提现申请正在处理
        if (isApplying(userId, projectId))
            return "0";

        // 判断验证码
        if (!GetCodeUtils.judgeCode(phone, verifyCode, httpSession).equals("1"))
            return "6";

        // 检查申请人姓名
        if (null == applyName || applyName.trim().isEmpty())
            return "2";

        // 检查提现金额
        if (null != withdrawAmount) {
            Pattern pattern = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");
            if (pattern.matcher(withdrawAmount).matches()) {
                if (Double.parseDouble(withdrawAmount) < MIN_WITHDRAW_AMOUNT)
                    return "3";
                if (Double.parseDouble(withdrawAmount) > Double.parseDouble(getWalletAmount(userId, projectId)))
                    return "4";
                String withdrawId = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
                Timestamp applyTime = new Timestamp(new Date().getTime());
                int withdrawStatus = 0;
                walletDao.addWalletApplyRecord(withdrawId, userId, projectId, withdrawAmount, applyTime, applyName, withdrawStatus);

                //TODO 短信通知项目发起人进行处理
                GetCodeUtils.getCode(phone, httpSession, "0");

                return "1";
            }
        }
        return "5";
    }

    //查看是否还有提现申请未处理
    public boolean isApplying(String userId, String projectId) {
        if (walletDao.getLatestWalletStatus(userId, projectId) == null)
            return false;
        if (walletDao.getLatestWalletStatus(userId, projectId) != 0)
            return false;
        return true;//true
    }

    //------------------以下为处理提现功能
    //获取某个项目所有的提现申请
    public List<ProjectWalletWithdraw> getWithdrawApplyList(String projectId) {
        int withdrawStatus = 0;
        return walletDao.getProjectWithdrawList(projectId, withdrawStatus);
    }

    //处理一条提现申请
    public Integer handleWithdraw(String userId, String projectId, String withdrawId) {
        //TODO 防止提现被异步处理！！！
        Integer preWithdrawStatus = walletDao.getWithdrawStatus(withdrawId);
        if (preWithdrawStatus == 1)
            return 0;
        ProjectWalletWithdraw projectWalletWithdraw = walletDao.getWithdrawById(withdrawId);
        String withdrawUserId = projectWalletWithdraw.getUserId();//
        String withdrawProjectId = projectWalletWithdraw.getProjectId();
        //钱包信息更新准备
        String withdrawAmount = projectWalletWithdraw.getWithdrawAmount();
        String walletAmount = walletDao.getWalletAmount(withdrawUserId, withdrawProjectId);
        //提现信息更新准备
        Timestamp hadleTime = new Timestamp(new Date().getTime());
        String handleName = investDao.getInvestorNameById(userId, projectId);
        Integer withdrawStatus = 1;
        System.out.println("-----"+withdrawUserId+"---"+withdrawProjectId+"---"+withdrawAmount+"---"+walletAmount);
        if (walletDao.updateWalletAmount(withdrawUserId, withdrawProjectId, (Double.parseDouble(walletAmount) - Double.parseDouble(withdrawAmount))+""))
            return walletDao.setWithdrawStatus(withdrawId, hadleTime, handleName, withdrawStatus);
        else
            return 0;
    }

    //获取申请提现人的姓名，前期从项目投资表获取，后期即为实名
    public String getApplyName(String userId, String projectId) {
        return investDao.getInvestorNameById(userId, projectId);
    }
}
