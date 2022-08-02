package com.example.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisTestService {
    private final RedissonClient redissonClient;

    public String getLock() {
        RLock lock = redissonClient.getLock("sampleLock");

        try {
            boolean isLocked = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (!isLocked) {
                log.error("=====Lock acquisition failed=====");
                return "Lock failed";
            }
        } catch (Exception e) {
            log.error("redis lock failed");
        }

        return "Lock success";
    }

}
