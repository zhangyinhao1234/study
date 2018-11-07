package com.github.zhangyinhao1234.study.redis.conf;

import redis.clients.jedis.Jedis;

import java.util.Arrays;

/**
 * @author 【张殷豪】
 * Date 2018/11/6 15:06
 */
public class RedisLuaTest {

    private static Long count = 1000L;

    private static Long decrCount = 10L;
    public static void main(String[] args) {
//        test1del();
//        test1decr();
//        testLuaFor();
//        testLuaFor2();
        testLuaFor3();

    }

    public static void test1del(){
        Jedis jedis = RedisUtil.getJedis();

        String key = "test1";
        String val = "hello";
        jedis.set(key,val);
        String luaScript = " if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end ";
        Object eval = jedis.eval(luaScript, Arrays.asList(key), Arrays.asList(val));
        System.out.println(eval);
    }

    /**
     * Lua
     * 减少库存
     */
    public static void test1decr(){
        Jedis jedis = RedisUtil.getJedis();
        String key = "test2";
        String decyCount = "10";
        jedis.set(key,"100");

        String luaScript = " if redis.call('get',KEYS[1]) >= ARGV[1] then return redis.call('decrby',KEYS[1],ARGV[1]) else return -1 end ";
        Object eval = jedis.eval(luaScript, Arrays.asList(key), Arrays.asList(decyCount));
        System.out.println(eval);
    }

    public static void testLuaFor(){
        String script = "local b = 1 for i=1,4,1 do b = b+1 end return b";
        Jedis jedis = RedisUtil.getJedis();
        Object eval = jedis.eval(script);
        System.out.println(eval);
    }

    static String  script = "local b = 1 " +
            " local n = table.getn(KEYS) " +
            " for i=1,n,1 do " +
            " if redis.call('get',KEYS[i]) >= ARGV[i] then b=1 else b=0 break  end " +
            " end " +
            " if(b>0) then " +
            " for i=1,n,1 do " +
            " redis.call('decrby',KEYS[i],ARGV[i]) " +
            " end return 1 else return 0 " +
            " end ";




    /**
     * 减库存lua脚本，支持多sku
     */
    public static void testLuaFor2(){
        Jedis jedis = RedisUtil.getJedis();
        String key1 = "test2";
        String key2 = "test3";
        String decyCount = "10";
        jedis.set(key1,"10");
        jedis.set(key2,"100");



        Object eval = jedis.eval(script,Arrays.asList(key1,key2), Arrays.asList(decyCount,decyCount));
        System.out.println(eval);
    }
    static private String sku1Key = "sku_1";
    static private String sku2Key = "sku_2";
    static private String sku3Key = "sku_3";
    static private String sku4Key = "sku_4";
    static private String c="10";

    /**
     * 支持多sku的库存扣减
     */
    public static void testLuaFor3(){
        Jedis jedis = RedisUtil.getJedis();
        Integer count1 = 90000;
        Integer count2 = 9000;

        //初始化库存
//        jedis.del(sku1Key,sku2Key,sku3Key,sku4Key);
//        jedis.incrBy(sku1Key,count2);
//        jedis.incrBy(sku2Key,count2);
//        jedis.incrBy(sku3Key,count1);
//        jedis.incrBy(sku4Key,count1);

        //扣库存 1成功，0某个商品存不足
        Object eval = jedis.eval(script, Arrays.asList(sku1Key, sku2Key,sku3Key,sku4Key), Arrays.asList(c, c,"100","2000"));
        RedisUtil.release(jedis);
        System.out.println(eval.toString());
    }
}
