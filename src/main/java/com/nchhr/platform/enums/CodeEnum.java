package com.nchhr.platform.enums;

public enum CodeEnum {

    AccessId("AccessKeyId", "LTAIMCtxtFp03EBf"),
    AccessKeySecre("AccessKeySecret", "nDr1n0ZRemha5MrkYnQ2ZLXprTmTdM"),
    //短信模板
    SMSTemplateCode("SMSTemplateCode", "SMS_146807344"),
    SignName("SignName", "南昌城市合伙人"),

    //短信提醒提现
    SMSTemplateCode1("SMSTemplateCode","SMS_150172325"),
    SignName1("SignName", "平台提醒"),


    //短信申请验证码
    SMSTemplateCode2("SMSTemplateCode","SMS_150182420"),
    SignName2("SignName", "申请提现"),;





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
