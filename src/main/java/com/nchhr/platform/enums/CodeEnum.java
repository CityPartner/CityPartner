package com.nchhr.platform.enums;

public enum CodeEnum {

    AccessId("AccessKeyId", "LTAIt2OMF0RdFsw0"),
    AccessKeySecre("AccessKeySecret", "CKNdqWQgzmYmPdeZetwpu0HmXHmEQH"),


    //用户注册
    SMSTemplateCode("SMSTemplateCode", "SMS_147125242"),
    SignName("SignName", "南昌城市合伙人"),

    //修改密码
    SMSTemplateCode1("SMSTemplateCode","SMS_147125241"),
    SignName1("SignName", "南昌城市合伙人"),


    //短信申请验证码
    SMSTemplateCode2("SMSTemplateCode","SMS_150576690"),
    SignName2("SignName", "南昌城市合伙人"),

    //商城提醒去提现
    SMSTemplateCode3("SMSTemplateCode","SMS_150571761"),
    SignName3("SignName","南昌城市合伙人");





    private String key;
    private String value;



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    CodeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
