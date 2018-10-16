package com.nchhr.platform.entity;

import java.io.Serializable;

public class OrderAddEntity implements Serializable {

    private String Add_record;          //补录id
    private String C_id;                //商品id
    private String number;              //补录商品数量
    private String single_people;       //补录人
    private String single_phone;        //补录人联系方式
    private String add_time;            //补录时间
    private String add_describe;        //补录描述
    private String price;               //补录金额

    public String getAdd_record() {
        return Add_record;
    }

    public void setAdd_record(String add_record) {
        Add_record = add_record;
    }

    public String getC_id() {
        return C_id;
    }

    public void setC_id(String c_id) {
        C_id = c_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSingle_people() {
        return single_people;
    }

    public void setSingle_people(String single_people) {
        this.single_people = single_people;
    }

    public String getSingle_phone() {
        return single_phone;
    }

    public void setSingle_phone(String single_phone) {
        this.single_phone = single_phone;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getAdd_describe() {
        return add_describe;
    }

    public void setAdd_describe(String add_describe) {
        this.add_describe = add_describe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderAddEntity{" +
                "Add_record='" + Add_record + '\'' +
                ", C_id='" + C_id + '\'' +
                ", number='" + number + '\'' +
                ", single_people='" + single_people + '\'' +
                ", single_phone='" + single_phone + '\'' +
                ", add_time='" + add_time + '\'' +
                ", add_describe='" + add_describe + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
