package com.github.zhangyinhao1234.study.strategy;

import java.math.BigDecimal;
/**
 * 
 * 秒杀计算策略
 *
 * @author zhang 2018年9月28日 下午9:15:45
 */
public class MiaoShaGoodsPriceStrategy implements GoodsPriceStrategy{

    @Override
    public BigDecimal count(BigDecimal price) {
        
        return BigDecimal.valueOf(1);
    }

}
