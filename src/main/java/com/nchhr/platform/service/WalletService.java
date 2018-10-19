package com.nchhr.platform.service;

import com.nchhr.platform.dao.WalletDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class WalletService {
    @Resource
    WalletDao walletDao;

    private final static int MIN_WITHDRAW_AMOUNT = 10;

    //获取项目钱包金额
    public int getWalletAmount(String userId, String projectId) {

        return walletDao.getWalletAmount(userId, projectId);
    }
    /*
        添加一条钱包提现申请记录
        @author JC
        @version 1.0
        @return
            0：尚有申请未处理
            1：提现申请成功
            2：申请提现金额低于最低额度
            3：申请提现金额高于最高额度
            4：提现金额格式出错
     */
    public String addWalletApplyRecord(String userId, String projectId, String applyName, String withdrawAmount) {
        // TODO 判断验证码
//        if (true)
//            return "5";
        // 是否有提现申请正在处理
        if (isApplying(userId, projectId))
            return "0";
        // 检查提现金额
        if (null != withdrawAmount) {
            Pattern pattern = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");
            if (pattern.matcher(withdrawAmount).matches()) {
                if (Double.parseDouble(withdrawAmount) < MIN_WITHDRAW_AMOUNT)
                    return "2";
                if (Double.parseDouble(withdrawAmount) > getWalletAmount(userId, projectId))
                    return "3";
                String withdrawId = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
                Timestamp applyTime = new Timestamp(new Date().getTime());
                int withdrawStatus = 0;
                walletDao.addWalletApplyRecord(withdrawId, userId, projectId, withdrawAmount, applyTime, withdrawStatus);
                return "1";
            }
        }
        return "4";
    }

    //查看是否还有提现申请未处理
    public boolean isApplying(String userId, String projectId) {
        if (walletDao.getWalletStatus(userId, projectId) == null)
            return false;
        if (walletDao.getWalletStatus(userId, projectId) != 0)
            return false;
        return false;//true
    }
}
