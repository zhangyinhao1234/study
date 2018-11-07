package com.github.zhangyinhao1234.study.redis.conf;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConfig {
    private static JedisPool jedisPool;
    
    private static String hi="";

    private static String password;

    private static String host = "127.0.0.1";

    private static int port = 6379;

    public static JedisPool getPool() {
        initJedisPool();
        return jedisPool;
    }

    private static void initJedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(2000);
        poolConfig.setMaxTotal(2000);
        synchronized (hi) {
            if (jedisPool != null) {
                return;
            }
            if ("".equals(RedisConfig.password) || RedisConfig.password == null) {
                jedisPool = new JedisPool(poolConfig, RedisConfig.host, RedisConfig.port);
            } else {
                jedisPool = new JedisPool(poolConfig, RedisConfig.host, RedisConfig.port, 2000,
                        RedisConfig.password);
            }
        }
    }
}
