package com.nchhr.platform.ModelVo;

import com.nchhr.platform.entity.IncomeEntity;
import com.nchhr.platform.entity.Mall_userEntity;

public class IncomeVo {
    private IncomeEntity incomeEntity;
    private Mall_userEntity mall_userEntity;

    public IncomeEntity getIncomeEntity() {
        return incomeEntity;
    }

    public void setIncomeEntity(IncomeEntity incomeEntity) {
        this.incomeEntity = incomeEntity;
    }

    public Mall_userEntity getMall_userEntity() {
        return mall_userEntity;
    }

    public void setMall_userEntity(Mall_userEntity mall_userEntity) {
        this.mall_userEntity = mall_userEntity;
    }

    @Override
    public String toString() {
        return "IncomeVo{" +
                "incomeEntity=" + incomeEntity +
                ", mall_userEntity=" + mall_userEntity +
                '}';
    }
}
