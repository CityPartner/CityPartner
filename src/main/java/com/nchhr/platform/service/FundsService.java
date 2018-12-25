package com.nchhr.platform.service;

import com.nchhr.platform.ModelVo.IncomeVo;
import com.nchhr.platform.ModelVo.ProWalletInVo;
import com.nchhr.platform.ModelVo.indexFundVo;
import com.nchhr.platform.ModelVo.indexPage;
import com.nchhr.platform.dao.*;
import com.nchhr.platform.entity.*;
import com.nchhr.platform.util.CodeUtils;
import com.nchhr.platform.util.PaginationUtils;
import com.nchhr.platform.util.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FundsService {

    @Resource
    IncomeDao incomeDao;

    @Resource
    FixedOverheadDao fixedOverheadDao;

    @Resource
    FactoryRebateDao factoryRebateDao;

    @Resource
    Mall_userDao mall_userDao;

    @Resource
    ProjectInvestDao projectInvestDao;

    @Resource
    ProjectWalletIncomeDao projectWalletIncomeDao;

    @Resource
    WalletDao walletDao;

    @Resource
    PlatformUserDao platformUserDao;

    /**
     * 加载收入数据
     *
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
    public List<FactoryRebateEntity> Rebate1(){
        return factoryRebateDao.loadList(0,10);
    }

    /**
     * 加载返点数据
     *
     * @return
     * @param index
     * @param fromIndex
     * @param pageSize
     * @param currentPage
     */
    public indexPage Rebate(String index, String fromIndex, String pageSize, String currentPage) {

        indexPage indexPage = new indexPage();
        List<indexFundVo> indexFundVos = new ArrayList<>();
        List<FactoryRebateEntity> factoryRebateEntities = new ArrayList<>();
       // List<IncomeVo> incomeVos = new ArrayList<>();

        int count = factoryRebateDao.count();
        System.out.println("多少条："+count);
        //年
        PageEntity  pageEntity =  PaginationUtils.pagination(Integer.parseInt(fromIndex),Integer.parseInt(pageSize),count);
        System.out.println(pageEntity);
        if ("0".equals(index)) {


            indexFundVos = factoryRebateDao.indexBytime("%Y",pageEntity.getFromIndex(),pageEntity.getPageSize());

            System.out.println(indexFundVos);
            indexPage.setIndexpages(indexFundVos);
        }
        //月
        if ("1".equals(index)) {
            indexFundVos = factoryRebateDao.indexBytime("%Y-%m", pageEntity.getFromIndex(), pageEntity.getPageSize());
//            System.out.println(indexFundVos);
            indexPage.setIndexpages(indexFundVos);
        }
        //日
        if ("2".equals(index)) {
            //获取的是income表中全部的字段
            factoryRebateEntities = factoryRebateDao.loadList( pageEntity.getFromIndex(), pageEntity.getPageSize());

            indexPage.setIndexpages(factoryRebateEntities);

        }

        indexPage.setPageEntity(pageEntity);
        return indexPage;
    }

    /**
     * 加载开销数据
     *
     * @return
     */
    public List<FixedOverheadEntity> overHead() {
        return fixedOverheadDao.loadList();
    }


    /**
     * 项目分红
     *
     * @param user_id        //分红人。项目发起人
     * @param project_id
     * @param dividendAmount
     * @return
     */
    public boolean dividendFund(String user_id, String project_id, int dividendAmount) {
        /*
         * 只有项目发起人可以分红
         * 分红计算每个人所得钱，放入每个人的钱包中，project_wallet需要改数值，project_wallet_income需要插入数据
         * 往固定开销表中插入一条支出记录fixed_overhead
         */

        try {
            CodeUtils codeUtils = new CodeUtils();
            String FO_id = "fo" + codeUtils.createRandom(false, 6);
            String time = TimeUtils.getTime();

            //向固定开销表插入分红数记录
            boolean insertDividend = fixedOverheadDao.InsertDividend(FO_id, user_id, project_id, time, dividendAmount, 1);

            //向project_wallet_income表中，插入记录（每次分红，12个股东，插入12条记录）
            //将每个人的钱包修改金额project_wallet（每次分红，12个股东，修改12个股东的钱包金额）
            String income_id = "inc" + codeUtils.createRandom(false, 7);
            //DecimalFormat df=new DecimalFormat("0.00");
            int stockholder = 12;
            int income_amount = dividendAmount / stockholder;         //每个股东分到的金额   问题一：income_amount是整型。。。。

            List<ProjectInvestEntity> projectInvestEntities = projectInvestDao.loadList(project_id);

            String income_user_id = "张三000_user_id";
            boolean delok = true;
            for (ProjectInvestEntity projectInvestEntity : projectInvestEntities) {
                income_user_id = projectInvestEntity.getUser_id();
                //找到对应项目的每个用户
                boolean projectWalletIncome = projectWalletIncomeDao.insertDivIncome(income_id, income_user_id, project_id, income_amount, time, '1', FO_id);
                boolean wallet = walletDao.updateWalletAmount(income_user_id, "", "");

                if (projectWalletIncome && wallet != true) {
                    delok = false;
                }
            }

            if (insertDividend && delok) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


//        List<ProInvetstVo> proInvetstVo = new ArrayList<>();
//        List<ProjectInvestEntity> projectInvestEntities = projectInvestDao.loadList(project_id);
//
//        for (ProjectInvestEntity projectInvestEntity : projectInvestEntities) {
//            ProInvetstVo proInvetstVo1 = new ProInvetstVo();
//
//            projectInvestEntity.getInvest_stock();
//
//
//        }

//
//        return proInvetstVo;

    }

    /**
     * 获取分红记录，每次分红的所有记录
     */
    public List<FixedOverheadEntity> loadDivList() {
        return fixedOverheadDao.loadDivList(1);
    }


    /**
     * 获取分红详情，每次分红每个人详情
     *
     * @param FO_id
     * @return
     */
    public List<ProWalletInVo> dividendDetail(String FO_id) {

        List<ProWalletInVo> proWalletInVos = new ArrayList<>();
        List<ProjectWalletIncome> projectWalletIncomes = projectWalletIncomeDao.loadIncomeList(FO_id);

        for (ProjectWalletIncome projectWalletIncome : projectWalletIncomes) {
            ProWalletInVo proWalletInVo1 = new ProWalletInVo();
            proWalletInVo1.setProjectWalletIncome(projectWalletIncome);
            proWalletInVo1.setPlatformUserEntity(platformUserDao.loadList(projectWalletIncome.getUserId()));
            proWalletInVos.add(proWalletInVo1);
        }
        return proWalletInVos;
    }

    public indexPage index(String index, String fromIndex, String pageSize, String currentPage) {
        indexPage indexPage = new indexPage();
        List<indexFundVo> indexFundVos = new ArrayList<>();
        List<IncomeEntity> incomeEntities = new ArrayList<>();
        List<IncomeVo> incomeVos = new ArrayList<>();

        int count = incomeDao.count();
        System.out.println("多少条："+count);
        //年
       PageEntity  pageEntity =  PaginationUtils.pagination(Integer.parseInt(fromIndex),Integer.parseInt(pageSize),count);
        System.out.println(pageEntity);
        if ("0".equals(index)) {


            indexFundVos = incomeDao.indexBytime("%Y",pageEntity.getFromIndex(),pageEntity.getPageSize());

            System.out.println(indexFundVos);
            indexPage.setIndexpages(indexFundVos);
        }
        //月
        if ("1".equals(index)) {
            indexFundVos = incomeDao.indexBytime("%Y-%m", pageEntity.getFromIndex(), pageEntity.getPageSize());
//            System.out.println(indexFundVos);
            indexPage.setIndexpages(indexFundVos);
        }
        //日
        if ("2".equals(index)) {
            //获取的是income表中全部的字段
            incomeEntities = incomeDao.indexByDate( pageEntity.getFromIndex(), pageEntity.getPageSize());
            for (IncomeEntity incomeEntity : incomeEntities) {
                IncomeVo incomeVo = new IncomeVo();
                incomeVo.setIncomeEntity(incomeEntity);
                incomeVo.setMall_userEntity(mall_userDao.loadById(incomeEntity.getM_id()));
                incomeVos.add(incomeVo);
            }
            indexPage.setIndexpages(incomeVos);

        }

        indexPage.setPageEntity(pageEntity);
         return indexPage;
    }
}
