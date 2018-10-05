package com.github.zhangyinhao1234.study.template;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 支付回调的抽象类
 *
 * @author zhang 2018年10月5日 下午12:34:52
 */
public abstract class AbstractPayNotify {

    /**
     * 
     * 支付回调的业务处理入口
     * 
     * @param request
     * @return
     */
    public Map<String, Object> notify(HttpServletRequest request) {
        boolean check = checkNotifyParams(request);
        if (!check) {
            return fail("签名验证失败");
        }
        OrderRequest order = parseRequest2Order(request);
        boolean tranStatus = checkTradeStatus(order);
        if (!tranStatus) {
            return fail("交易状态不正确");
        }
        boolean wait = waitBuyerPay(order);
        if (!wait) {
            return fail("交易被关闭");
        }
        tradeSuccess(order);
        return success();
    }

    /**
     * 
     * 进行订单的交易处理
     * 
     * @param order
     */
    private void tradeSuccess(OrderRequest order) {
        updateOrderAfterPay(order);
        saveOrderPayInfo(order);
        saveOrderLogsAfterPaySuccess(order);
        try {
            // 支付完成后其他业务处理
            handlerAfterPay(order);
        } catch (Exception e) {
            // ..日志记录
        }
    }

    /**
     * 
     * 支付完成后更新订单数据
     * 
     * @param order
     */
    public abstract void updateOrderAfterPay(OrderRequest order);

    /**
     * 
     * 支付完成后保存支付回调信息
     * 
     * @param order
     */
    public abstract void saveOrderPayInfo(OrderRequest order);

    /**
     * 
     * 保存支付完成的日志
     * 
     * @param order
     */
    public abstract void saveOrderLogsAfterPaySuccess(OrderRequest order);

    /**
     * 
     * 支付业务结束后非重要操作。例如消息推送
     * 
     * @param order
     */
    public abstract void handlerAfterPay(OrderRequest order);

    /**
     * 
     * 订单状态更新前的一些检查
     * 
     * @param order
     * @return
     */
    private boolean waitBuyerPay(OrderRequest order) {
        boolean riskcheck = riskCheck(order);
        if (!riskcheck) {
            closeTrade(order);
            return riskcheck;
        }
        // .. 风控检查只是其中一种，实际的业务要复杂的多。
        return true;
    }

    /**
     * 
     * 关闭交易
     * 
     * @param order
     */
    public abstract void closeTrade(OrderRequest order);

    /**
     * 
     * 风控检查
     * 
     * @param order
     * @return
     */
    public abstract boolean riskCheck(OrderRequest order);

    /**
     * 
     * 检查交易状态
     * 
     * @param order
     * @return
     */
    public abstract boolean checkTradeStatus(OrderRequest order);

    /**
     * 
     * 请求参数转为订单对象
     * 
     * @param request
     * @return
     */
    public abstract OrderRequest parseRequest2Order(HttpServletRequest request);

    /**
     * 
     * 检查支付回调的签名
     * 
     * @param request
     * @return
     */
    public abstract boolean checkNotifyParams(HttpServletRequest request);

    private Map<String, Object> fail(String info) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", "false");
        result.put("info", info);
        return result;
    }

    private Map<String, Object> success() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", "true");
        return result;
    }
}
