package com.nchhr.platform.service;

import com.nchhr.platform.ModelVo.IncomeVo;
import com.nchhr.platform.dao.FixedOverheadDao;
import com.nchhr.platform.dao.IncomeDao;
import com.nchhr.platform.dao.Mall_userDao;
import com.nchhr.platform.entity.FixedOverheadEntity;
import com.nchhr.platform.entity.IncomeEntity;
import com.nchhr.platform.entity.Mall_userEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FundsService {

    @Autowired
    IncomeDao incomeDao;

    @Autowired
    FixedOverheadDao fixedOverheadDao;

    @Autowired
    Mall_userDao mall_userDao;

    /**
     * 加载收入数据
     * @return
     */
    public List<IncomeVo> income() {

        List<IncomeVo> incomeVos = new ArrayList<>();
        List<IncomeEntity> incomeEntities = incomeDao.loadList();

        for (IncomeEntity incomeEntity : incomeEntities) {
            IncomeVo incomeVo = new IncomeVo();
            incomeVo.setIncomeEntity(incomeEntity);
            incomeVo.setMall_userEntity(mall_userDao.loadById(incomeEntity.getM_id()));
            incomeVos.add(incomeVo);
        }
        return incomeVos;
    }

    /**
     * 加载开销数据
     * @return
     */
    public List<FixedOverheadEntity> overHead() {
        return fixedOverheadDao.loadList();
    }
}
