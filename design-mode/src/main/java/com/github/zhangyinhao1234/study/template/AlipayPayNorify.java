package com.github.zhangyinhao1234.study.template;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 支付宝支付回调
 *
 * @author zhang 2018年10月5日 下午1:30:24
 */
public class AlipayPayNorify extends AbstractPayNotifyBusiness {
    
    
    
    
    @Override
    public boolean checkNotifyParams(HttpServletRequest request) {
        System.out.println("。。。支付宝签名验证。。。。");
        return true;
    }

    @Override
    public OrderRequest parseRequest2Order(HttpServletRequest request) {
        System.out.println("。。。将支付宝的请求参数处理成订单对象。。。。");
        return new OrderRequest();
    }

    @Override
    public void saveOrderPayInfo(OrderRequest order) {
        System.out.println("。。。保存支付宝支付回调信息。。。。");
    }

    @Override
    public void closeTrade(OrderRequest order) {
        System.out.println("。。。关闭支付宝交易。。。。");

    }

}
