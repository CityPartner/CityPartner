package com.nchhr.platform.Enum;

public enum CodeEnum {

    AccessId("AccessKeyId", "LTAIMCtxtFp03EBf"),
    AccessKeySecre("AccessKeySecret", "nDr1n0ZRemha5MrkYnQ2ZLXprTmTdM"),
    //短信模板
    SMSTemplateCode("SMSTemplateCode", "SMS_146807344"),
    SignName("SignName", "南昌城市合伙人"),;

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
