package com.github.zhangyinhao1234.study.proxy;
/**
 * 
 * 代理模式，mybatis就是使用代理模式对接口映射到xml文件进行的sql处理
 *
 * @author zhang 2018年9月22日 下午10:32:52
 */
public class ProxyMain {

    public static void main(String[] args) {
        DaoProxyFactory<IHelloService> daoProxyFactory = new DaoProxyFactory<IHelloService>(
                IHelloService.class);
        IHelloService newInstance = daoProxyFactory.newInstance();
        String hello = newInstance.hello("zhangsan");
        System.out.println(hello);
    }
}
