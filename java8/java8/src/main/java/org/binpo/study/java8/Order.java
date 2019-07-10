package org.binpo.study.java8;/**
 * @author 【张殷豪】
 * Date 2019/7/10 13:26
 */

import java.math.BigDecimal;

/**
 * @Author yinhao.zhang
 * @Date 2019/7/10 13:26
 **/
public class Order {

    public Order(String orderNo,BigDecimal amount){
        this.orderNo = orderNo;
        this.amount = amount;
    }
    String orderNo;

    BigDecimal amount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
