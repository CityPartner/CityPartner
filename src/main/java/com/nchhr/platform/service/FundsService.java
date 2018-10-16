package com.nchhr.platform.service;

import com.nchhr.platform.dao.FixedOverheadDao;
import com.nchhr.platform.dao.IncomeDao;
import com.nchhr.platform.entity.FixedOverheadEntity;
import com.nchhr.platform.entity.IncomeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundsService {

    @Autowired
    IncomeDao incomeDao;

    @Autowired
    FixedOverheadDao fixedOverheadDao;

    /**
     * 加载收入数据
     * @return
     */
    public List<IncomeEntity> income() {
        return incomeDao.loadList();
    }

    /**
     * 加载开销数据
     * @return
     */
    public List<FixedOverheadEntity> overHead() {
        return fixedOverheadDao.loadList();
    }
}
