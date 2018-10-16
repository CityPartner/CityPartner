package com.nchhr.platform.entity;

import java.io.Serializable;

public class FixedOverheadEntity implements Serializable{

    private String F_id;
    private String rebate_time;
    private String rebate_amount;

    public String getF_id() {
        return F_id;
    }

    public void setF_id(String f_id) {
        F_id = f_id;
    }

    public String getRebate_time() {
        return rebate_time;
    }

    public void setRebate_time(String rebate_time) {
        this.rebate_time = rebate_time;
    }

    public String getRebate_amount() {
        return rebate_amount;
    }

    public void setRebate_amount(String rebate_amount) {
        this.rebate_amount = rebate_amount;
    }

    @Override
    public String toString() {
        return "FixedOverheadEntity{" +
                "F_id='" + F_id + '\'' +
                ", rebate_time='" + rebate_time + '\'' +
                ", rebate_amount='" + rebate_amount + '\'' +
                '}';
    }

}
