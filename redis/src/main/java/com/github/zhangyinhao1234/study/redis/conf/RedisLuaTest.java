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
        testLuaFor2();
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


    /**
     * 减库存lua脚本，支持多sku
     */
    public static void testLuaFor2(){
        Jedis jedis = RedisUtil.getJedis();
        String key1 = "test2";
        String key2 = "test3";
        String decyCount = "20";
        jedis.set(key1,"10");
        jedis.set(key2,"100");

        String script = "local b = 1 " +
                " local n = table.getn(KEYS) " +
                " for i=1,n,1 do " +
                " if redis.call('get',KEYS[i]) >= ARGV[i] then  else b=0 break end " +
                " end " +
                " if(b>0) then " +
                " for i=1,n,1 do " +
                " redis.call('decrby',KEYS[i],ARGV[i]) " +
                " end" +
                " end " +
                " return b ";

        Object eval = jedis.eval(script,Arrays.asList(key1,key2), Arrays.asList(decyCount,decyCount));
        System.out.println(eval);
    }
}
