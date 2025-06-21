package com.cicd.clutch.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisQueuePublisher {

    private static final String QUEUE_NAME = "jobsQueue";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void publish(String message) {
    	 System.out.println("Publishing to Redis queue: " + message);
    	    redisTemplate.opsForList().leftPush("jobsQueue", message);
//        redisTemplate.opsForList().leftPush(QUEUE_NAME, message);
    }
}
