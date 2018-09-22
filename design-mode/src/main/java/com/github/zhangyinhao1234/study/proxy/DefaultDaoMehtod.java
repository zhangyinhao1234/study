package com.github.zhangyinhao1234.study.proxy;

import com.alibaba.fastjson.JSON;

public class DefaultDaoMehtod {

    public Object execute(Object[] args) {
        return "hello" + JSON.toJSONString(args);
    }
}
