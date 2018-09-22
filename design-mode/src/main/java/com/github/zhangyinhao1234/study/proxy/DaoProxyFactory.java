package com.github.zhangyinhao1234.study.proxy;

import java.lang.reflect.Proxy;

public class DaoProxyFactory<T> {

    private final Class<T> interfaceClass;

    public DaoProxyFactory(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public T newInstance(DaoProxy<T> proxy) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
                proxy);
    }

    public T newInstance() {
        DaoProxy<T> daoProxy = new DaoProxy<T>();
        return newInstance(daoProxy);
    }

}
