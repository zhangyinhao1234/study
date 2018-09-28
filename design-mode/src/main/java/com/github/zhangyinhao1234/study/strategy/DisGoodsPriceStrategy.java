package com.github.zhangyinhao1234.study.strategy;

import java.math.BigDecimal;

/**
 * 
 * 优惠策略
 *
 * @author zhang 2018年9月28日 下午9:20:29
 */
public class DisGoodsPriceStrategy implements GoodsPriceStrategy {

    @Override
    public BigDecimal count(BigDecimal price) {

        return price.multiply(new BigDecimal("0.8"));
    }

}
