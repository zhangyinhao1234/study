package com.github.zhangyinhao1234.study.strategy;
/**
 * 
 * 价格计划
 *
 * @author zhang 2018年9月28日 下午9:12:22
 */

import java.math.BigDecimal;

public interface GoodsPriceStrategy {

    /**
     * 
     * 计算价格
     * 
     * @param price
     * @return
     */
    public BigDecimal count(BigDecimal price);

}
