package com.github.zhangyinhao1234.study.template;

import javax.servlet.http.HttpServletRequest;

public class TemplateMain {
    static HttpServletRequest request;

    public static void main(String[] args) {
        // testAlipayPay();
        testWechatPay();
    }

    public static void testAlipayPay() {
        AlipayPayNorify alipayPayNorify = new AlipayPayNorify();
        alipayPayNorify.notify(request);
    }

    public static void testWechatPay() {
        WechatPayNorify wechatPayNorify = new WechatPayNorify();
        wechatPayNorify.notify(request);
    }
}
