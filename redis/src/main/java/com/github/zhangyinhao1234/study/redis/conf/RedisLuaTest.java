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
        test1decr();
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
}
