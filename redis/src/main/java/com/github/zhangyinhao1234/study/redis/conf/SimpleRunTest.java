package com.github.zhangyinhao1234.study.redis.conf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import redis.clients.jedis.Jedis;

class ThreadMain{
    public static ExecutorService thread = Executors.newFixedThreadPool(100);
}

public class SimpleRunTest {

    private String sku1Key = "sku1";
    private String sku2Key = "sku2";
    private String sku3Key = "sku3";
    private String sku4Key = "sku4";
    private String count = "1000000";

    private Integer compV = 10;

    private String lockKey = "skutaskId";
    String luaScript = " if redis.call('get',KEYS[1]) >= ARGV[1] then return redis.call('decrby',KEYS[1],ARGV[1]) else return -1 end ";



    public static void main(String[] args) {
//        test1();

//        testCallAble();

        //多线程并发减库存
        testRunAble();
    }

    /**
     * 多线程扣库存
     */
    public static void testCallAble() {
        SimpleRunTest simpleRunTest = getSimpleRunTest();
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
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
     * 单线程
     */
    public static void test1() {
        SimpleRunTest simpleRunTest = getSimpleRunTest();
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


    public static void testRunAble() {
        getSimpleRunTest();//init
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ThreadMain.thread.execute(new RedisRunAble(new SimpleRunTest()));
        }

        try {
//            ThreadMain.thread.shutdown();
            System.out.println(System.currentTimeMillis() - currentTimeMillis);
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SimpleRunTest getSimpleRunTest() {
        SimpleRunTest simpleRunTest = new SimpleRunTest();
        simpleRunTest.init();
        return simpleRunTest;
    }

    public void lock(Jedis jedis) {
        String string = "";
        while (!doLock(jedis, string)) {
//             System.out.println("wait...");
        }
        return;
    }

    public boolean doLock(Jedis jedis, String uuid) {
        String set = jedis.set(lockKey, uuid, "NX", "PX", 3000);
        return "OK".equals(set);

    }

    public void unLock(Jedis jedis) {
        jedis.del(lockKey);
    }

    public void comAllSku(Jedis jedis) {
        comp(jedis, sku1Key);
        comp(jedis, sku2Key);
        comp(jedis, sku3Key);
        comp(jedis, sku4Key);
    }

    public void decrAllSku(Jedis jedis) {
        decrBy(jedis, sku1Key);
        decrBy(jedis, sku2Key);
        decrBy(jedis, sku3Key);
        decrBy(jedis, sku4Key);
    }

    public boolean comp(Jedis jedis, String skukey) {
        String val = jedis.get(skukey);
        return (Integer.valueOf(val) > this.compV) ? true : false;
    }


    public void decrAllSkuUseLua(Jedis jedis) {
        decrbyCountUseLua(jedis, sku1Key);
        decrbyCountUseLua(jedis, sku2Key);
        decrbyCountUseLua(jedis, sku3Key);
        decrbyCountUseLua(jedis, sku4Key);
    }

    /**
     * Lua脚本进行CAS原子性操作
     * @param jedis
     * @param skukey
     * @return
     */
    public boolean decrbyCountUseLua(Jedis jedis, String skukey) {
        Object eval = jedis.eval(luaScript, Arrays.asList(skukey), Arrays.asList(this.compV.toString()));
        return (Integer.valueOf(eval.toString()) > 0);
    }

    public void decrBy(Jedis jedis, String skukey) {
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

/**
 * 线程处理数据
 */
class RedisRunAble extends Thread {
    private SimpleRunTest simpleRunTest;
    public RedisRunAble(SimpleRunTest simpleRunTest) {
        this.simpleRunTest = simpleRunTest;
    }
    @Override
    public void run() {
        Jedis jedis = RedisUtil.getJedis();
        simpleRunTest.decrAllSkuUseLua(jedis);
        RedisUtil.release(jedis);
    }
}