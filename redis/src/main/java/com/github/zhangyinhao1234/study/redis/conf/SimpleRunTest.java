package com.github.zhangyinhao1234.study.redis.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import redis.clients.jedis.Jedis;

public class SimpleRunTest {
    private String sku1Key = "sku1";
    private String sku2Key = "sku2";
    private String sku3Key = "sku3";
    private String sku4Key = "sku4";
    private String count = "1000000";

    private Integer compV = 10;

    private String lockKey = "skutaskId";
    
    ExecutorService thread = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
//        test1();
        
        testCallAble();

    }
    
    /**
     * 
     * 多线程扣库存
     *
     */
    public static void testCallAble() {
        SimpleRunTest simpleRunTest = new SimpleRunTest();
        simpleRunTest.init();

        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Jedis jedis = RedisUtil.getJedis();
            simpleRunTest.lock(jedis);
            
//            List<Future<Boolean>> futureList = new ArrayList<Future<Boolean>>();
//            for(int j=0;j<4;j++) {
//                Future<Boolean> submit = 
//                        simpleRunTest.thread.submit(new RedisComCallBack(jedis, "sku"+(j+1), simpleRunTest.compV) );
//                futureList.add(submit);
//            }
//            for(Future<Boolean> fuaure : futureList) {
//                try {
//                    Boolean boolean1 = fuaure.get();
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//            
            
            simpleRunTest.comAllSku(jedis);
            simpleRunTest.decrAllSku(jedis);
            
            simpleRunTest.unLock(jedis);
            RedisUtil.release(jedis);
        }
        System.out.println(System.currentTimeMillis() - currentTimeMillis);

    }

    

    /**
     * 
     * 单线程
     *
     */
    public static void test1() {
        SimpleRunTest simpleRunTest = new SimpleRunTest();
        simpleRunTest.init();

        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Jedis jedis = RedisUtil.getJedis();
            simpleRunTest.lock(jedis);
            simpleRunTest.comAllSku(jedis);
            simpleRunTest.decrAllSku(jedis);
            simpleRunTest.unLock(jedis);
            RedisUtil.release(jedis);
        }
        System.out.println(System.currentTimeMillis() - currentTimeMillis);

    }

    private void lock(Jedis jedis) {
        String string = "";
        while (!doLock(jedis, string)) {
            // System.out.println("---");
        }
        return;
    }

    private boolean doLock(Jedis jedis, String uuid) {
        String set = jedis.set(lockKey, uuid, "NX", "PX", 3000);
        return "OK".equals(set);

    }

    private void unLock(Jedis jedis) {
        jedis.del(lockKey);
    }

    private void comAllSku(Jedis jedis) {
        comp(jedis, sku1Key);
        comp(jedis, sku2Key);
        comp(jedis, sku3Key);
        comp(jedis, sku4Key);
    }

    private void decrAllSku(Jedis jedis) {
        decrBy(jedis, sku1Key);
        decrBy(jedis, sku2Key);
        decrBy(jedis, sku3Key);
        decrBy(jedis, sku4Key);
    }

    private boolean comp(Jedis jedis, String skukey) {
        String val = jedis.get(skukey);
        return (Integer.valueOf(val) > this.compV) ? true : false;
    }

    private void decrBy(Jedis jedis, String skukey) {
        jedis.decrBy(skukey, Long.valueOf(compV));
    }

    private void init() {
        Jedis jedis = RedisUtil.getJedis();
        jedis.set(sku1Key, count);
        jedis.set(sku2Key, count);
        jedis.set(sku3Key, count);
        jedis.set(sku4Key, count);
    }

}

/**
 * 
 * 比较线程
 *
 * @author zhang 2018年11月4日 下午8:49:16
 */
class RedisComCallBack implements Callable<Boolean> {

    private Jedis jedis;
    private String key;
    private Integer compV;

    public RedisComCallBack(Jedis jedis, String key, Integer compV) {
        this.jedis = jedis;
        this.key = key;
        this.compV = compV;
    }

    private boolean comp() {
        String val = jedis.get(key);
        return (Integer.valueOf(val) > this.compV) ? true : false;
    }

    @Override
    public Boolean call() throws Exception {
        return comp();
    }
}

/**
 * 
 * 减库存线程
 *
 * @author zhang 2018年11月4日 下午8:52:04
 */
class RedisdecrCallBack implements Callable<Boolean> {
    private Jedis jedis;
    private String key;
    private Integer compV;

    public RedisdecrCallBack(Jedis jedis, String key, Integer compV) {
        this.jedis = jedis;
        this.key = key;
        this.compV = compV;
    }

    @Override
    public Boolean call() throws Exception {
        decrBy();
        return true;
    }

    private void decrBy() {
        jedis.decrBy(key, Long.valueOf(compV));
    }

}
