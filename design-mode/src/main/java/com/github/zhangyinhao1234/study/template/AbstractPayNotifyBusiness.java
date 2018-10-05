package com.github.zhangyinhao1234.study.template;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 支付回调公共的业务处理（和支付方式没有关系）
 *
 * @author zhang 2018年10月5日 下午1:24:14
 */
public abstract class AbstractPayNotifyBusiness extends AbstractPayNotify {

    @Override
    public void updateOrderAfterPay(OrderRequest order) {
        System.out.println("。。。更新订单状态等操作。。。。");

    }

    @Override
    public void saveOrderLogsAfterPaySuccess(OrderRequest order) {
        System.out.println("。。。保存支付完成的订单日志。。。。");

    }

    @Override
    public void handlerAfterPay(OrderRequest order) {
        System.out.println("。。。订单处理完成消息推送等。。。。");

    }

    @Override
    public boolean riskCheck(OrderRequest order) {
        System.out.println("。。。订单状态更新前的各项检查。。。。");
        System.out.println("。。。风险控制检查。。。。");
        return true;
    }

    @Override
    public boolean checkTradeStatus(OrderRequest order) {
        System.out.println("。。。检查订单交易状态。。。。");
        return true;
    }

}
