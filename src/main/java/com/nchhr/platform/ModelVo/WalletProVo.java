package com.nchhr.platform.ModelVo;

public class WalletProVo {
    //wallet
    String wallet_id;
    String user_id;
    String project_id;
    String wallet_amount;

    public WalletProVo(String wallet_id, String user_id, String project_id, String wallet_amount, String name, String funding, String discount_lowest, String discount_highest) {
        this.wallet_id = wallet_id;
        user_id = user_id;
        this.project_id = project_id;
        this.wallet_amount = wallet_amount;
        this.name = name;
        this.funding = funding;
        this.discount_lowest = discount_lowest;
        this.discount_highest = discount_highest;
    }

    @Override
    public String toString() {
        return "WalletProVo{" +
                "wallet_id='" + wallet_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", wallet_amount='" + wallet_amount + '\'' +
                ", name='" + name + '\'' +
                ", funding='" + funding + '\'' +
                ", discount_lowest='" + discount_lowest + '\'' +
                ", discount_highest='" + discount_highest + '\'' +
                '}';
    }

    //project
    String name;
    String funding;
    String discount_lowest;
    String discount_highest;

    public WalletProVo() {
    }

    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }

    public String getuser_id() {
        return user_id;
    }

    public void setuser_id(String user_id) {
        user_id = user_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getWallet_amount() {
        return wallet_amount;
    }

    public void setWallet_amount(String wallet_amount) {
        this.wallet_amount = wallet_amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public String getDiscount_lowest() {
        return discount_lowest;
    }

    public void setDiscount_lowest(String discount_lowest) {
        this.discount_lowest = discount_lowest;
    }

    public String getDiscount_highest() {
        return discount_highest;
    }

    public void setDiscount_highest(String discount_highest) {
        this.discount_highest = discount_highest;
    }
}
