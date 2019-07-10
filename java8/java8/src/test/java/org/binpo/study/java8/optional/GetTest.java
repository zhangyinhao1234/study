package org.binpo.study.java8.optional;/**
 * @author 【张殷豪】
 * Date 2019/7/10 11:33
 */

import org.binpo.study.java8.Order;
import org.binpo.study.java8.RequestModule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author yinhao.zhang
 * @Date 2019/7/10 11:33
 **/
public class GetTest {


    @Test
    public void testget() {
        RequestModule zhagsan = RequestModule.getRequest("zhagsan", null);
        RequestModule zhagsan2 = null;
        Optional.ofNullable(zhagsan2).ifPresent(RequestModule::getName);


        String aDefault = Optional.ofNullable(zhagsan2).orElse(RequestModule.getRequest("default", null)).getName();
        System.out.println("default:" + aDefault);

        RequestModule aDefault1 = Optional.ofNullable(zhagsan2).orElseGet(() -> RequestModule.getRequest("default", "123456"));
        System.out.println("aDefault1:" + aDefault1.getPassword() + "   " + aDefault1.getName());

    }


    @Test
    public void mapTest() {
        RequestModule zhagsan = RequestModule.getRequest("zhagsan", null);
        List<Order> orders = Optional.of(zhagsan).map(u -> u.getOrderList()).orElse(new ArrayList<>());

        //
        String pw = Optional.of(zhagsan).map(u -> u.getPassword()).map(p -> p.toLowerCase()).orElse("123456");
        System.out.println("pw:" + pw);

        zhagsan = null;
        pw = Optional.ofNullable(zhagsan).map(u -> u.getPassword()).map(p -> p.toLowerCase()).orElse("5678");
        System.out.println("pw:" + pw);
    }


    @Test
    public void filterTest() {
        RequestModule zhagsan = RequestModule.getRequest("zhagsan", null);
        Optional<RequestModule> hello = Optional.of(zhagsan).filter(v -> v.getName().equals("hello"));
        if (!hello.isPresent()) {
            System.out.println("hello is null");
        }

        Optional<RequestModule> requestModule = Optional.of(zhagsan).filter(v -> v.getName() != null && v.getOrderList() == null);
        if (!requestModule.isPresent()) {
            System.out.println("requestModule is null");
        }

    }


    @Test
    public void flatMapTest() {
        RequestModule zhagsan = RequestModule.getRequest("zhagsan", null);

    }


    public List<Order> test1() {
        //DB query
        RequestModule user = RequestModule.getRequest("zhagsan", null);
        if (user == null) {
            return new ArrayList<>();
        }
        return user.getOrderList() == null ? new ArrayList<>() : user.getOrderList();
    }

    public List<Order> test11() {
        //DB query
        RequestModule user = RequestModule.getRequest("zhagsan", null);
        return user == null || user.getOrderList() == null ? new ArrayList<>() : user.getOrderList();
    }

    public List<Order> test2() {
        //DB query
        RequestModule user = RequestModule.getRequest("zhagsan", null);
        user.setOrderList(Stream.of(new Order("123", new BigDecimal("23.00"))).collect(Collectors.toList()));
        return Optional.ofNullable(user).map(u -> u.getOrderList()).orElse(new ArrayList<>());
    }

    @Test
    public void test3() {
        List<Order> orders = test11();
        if (orders.isEmpty()) {
            System.out.println("orders is empty");
        } else {
            System.out.println("orders size : " + orders.size());
        }
    }


}
