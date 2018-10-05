package com.github.zhangyinhao1234.study.template;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * 微信支付的支付回调
 *
 * @author zhang 2018年10月5日 下午1:35:28
 */
public class WechatPayNorify extends AbstractPayNotifyBusiness{

    @Override
    public boolean checkNotifyParams(HttpServletRequest request) {
        System.out.println("。。。微信支付签名验证。。。。");
        return true;
    }

    @Override
    public OrderRequest parseRequest2Order(HttpServletRequest request) {
        System.out.println("。。。将微信支付的请求参数处理成订单对象。。。。");
        return new OrderRequest();
    }

    @Override
    public void saveOrderPayInfo(OrderRequest order) {
        System.out.println("。。。保存微信支付支付回调信息。。。。");
    }

    @Override
    public void closeTrade(OrderRequest order) {
        System.out.println("。。。关闭微信支付交易。。。。");

    }

}
