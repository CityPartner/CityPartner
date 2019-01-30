package com.nchhr.platform.ModelVo;


import com.nchhr.platform.entity.PageEntity;

import java.util.List;

public class indexFundVo {

    private String times;
    private String  income_amounts;


    public String getIncome_amounts() {
        return income_amounts;
    }

    public void setIncome_amounts(String income_amounts) {
        this.income_amounts = income_amounts;
    }


    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getIncome_amount() {
        return income_amounts;
    }

    public void setIncome_amount(String income_amount) {
        this.income_amounts = income_amount;
    }

    @Override
    public String toString() {
        return "indexFundVo{" +
                "times='" + times + '\'' +
                ", income_amounts='" + income_amounts + '\'' +
                '}';
    }
}
