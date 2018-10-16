package com.nchhr.platform.entity;

import java.io.Serializable;

public class OrdersEntity implements Serializable{

    private String O_id;
    private String Re_id;
    private String M_id;
    private Double price;
    private String order_time;
    private String self_lifting;
    private String status;
    private String OFid;

    public String getO_id() {
        return O_id;
    }

    public void setO_id(String o_id) {
        O_id = o_id;
    }

    public String getRe_id() {
        return Re_id;
    }

    public void setRe_id(String re_id) {
        Re_id = re_id;
    }

    public String getM_id() {
        return M_id;
    }

    public void setM_id(String m_id) {
        M_id = m_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getSelf_lifting() {
        return self_lifting;
    }

    public void setSelf_lifting(String self_lifting) {
        this.self_lifting = self_lifting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOFid() {
        return OFid;
    }

    public void setOFid(String OFid) {
        this.OFid = OFid;
    }

    @Override
    public String toString() {
        return "OrdersEntity{" +
                "O_id='" + O_id + '\'' +
                ", Re_id='" + Re_id + '\'' +
                ", M_id='" + M_id + '\'' +
                ", price=" + price +
                ", order_time='" + order_time + '\'' +
                ", self_lifting='" + self_lifting + '\'' +
                ", status='" + status + '\'' +
                ", OFid='" + OFid + '\'' +
                '}';
    }
}
