package org.binpo.study.java8;/**
 * @author 【张殷豪】
 * Date 2019/7/10 11:34
 */

import java.util.List;

/**
 * @Author yinhao.zhang
 * @Date 2019/7/10 11:34
 **/

public class RequestModule {

    String name;

    String password;

    List<Order> orderList ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private RequestModule(String name,String password){
        this.name = name;
        this.password = password;
    }

    public static RequestModule getRequest(String name,String password){
        return new RequestModule(name,password);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
