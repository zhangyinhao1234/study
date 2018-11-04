package com.github.zhangyinhao1234.study.redis.conf;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtil {

    public static ThreadLocal<JedisPool> poolLocal = new ThreadLocal<JedisPool>();

    public static Jedis getJedis() {
        JedisPool pool = RedisConfig.getPool();
        poolLocal.set(pool);
        return pool.getResource();
    }

    public static void release(Jedis jedis) {
        JedisPool jedisPool = poolLocal.get();
        jedisPool.returnResourceObject(jedis);
    }
}
