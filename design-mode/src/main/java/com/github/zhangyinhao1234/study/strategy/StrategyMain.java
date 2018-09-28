package com.github.zhangyinhao1234.study.strategy;

import java.math.BigDecimal;

public class StrategyMain {

    private GoodsPriceStrategy goodsPriceStrategy;

    public void setGoodsPriceStrategy(GoodsPriceStrategy goodsPriceStrategy) {
        this.goodsPriceStrategy = goodsPriceStrategy;
    }

    public static void main(String[] args) {
        BigDecimal amount = BigDecimal.valueOf(10);
        StrategyMain strategyMain = new StrategyMain();
        strategyMain.setGoodsPriceStrategy(new DisGoodsPriceStrategy());

        System.out.println(strategyMain.count(amount));

        strategyMain.setGoodsPriceStrategy(new MiaoShaGoodsPriceStrategy());

        System.out.println(strategyMain.count(amount));

    }

    public BigDecimal count(BigDecimal price) {
        return goodsPriceStrategy.count(price);
    }

}
