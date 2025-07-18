package io.github.yajanth.clutch.queue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {
    private static final String HOST = "localhost";
    private static final int PORT = 6379;

    private static final JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST, PORT);

    public static Jedis getConnection() {
        return pool.getResource();
    }

    public static void shutdown() {
        pool.close();
    }
}
