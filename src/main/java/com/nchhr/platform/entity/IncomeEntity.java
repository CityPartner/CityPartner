package com.nchhr.platform.entity;

import java.io.Serializable;

public class IncomeEntity implements Serializable {

    private String In_id;                   //收入id
    private String M_id;                    //投资人id
    private String time;                    //收入时间
    private String income_amount;           //投资人收入金额
    private String unallocated_amount;      //未分配利润

    public String getIn_id() {
        return In_id;
    }

    public void setIn_id(String in_id) {
        In_id = in_id;
    }

    public String getM_id() {
        return M_id;
    }

    public void setM_id(String m_id) {
        M_id = m_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIncome_amount() {
        return income_amount;
    }

    public void setIncome_amount(String income_amount) {
        this.income_amount = income_amount;
    }

    public String getUnallocated_amount() {
        return unallocated_amount;
    }

    public void setUnallocated_amount(String unallocated_amount) {
        this.unallocated_amount = unallocated_amount;
    }

    @Override
    public String toString() {
        return "IncomeEntity{" +
                "In_id='" + In_id + '\'' +
                ", M_id='" + M_id + '\'' +
                ", time='" + time + '\'' +
                ", income_amount='" + income_amount + '\'' +
                ", unallocated_amount='" + unallocated_amount + '\'' +
                '}';
    }
}
