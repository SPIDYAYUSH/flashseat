package com.example.flashseat.flashseat.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SeatLockService {

    private final RedisTemplate<String, String> redisTemplate;

    public SeatLockService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean lockSeat(Long eventId, Long seatId, Long userId) {

        String key = "seat:" + eventId + ":" + seatId;

        Boolean locked = redisTemplate.opsForValue()
                .setIfAbsent(
                        key,
                        userId.toString(),
                        10,
                        TimeUnit.MINUTES
                );

        return Boolean.TRUE.equals(locked);
    }

    public void unlockSeat(Long eventId, Long seatId, Long userId) {

        String key = "seat:" + eventId + ":" + seatId;

        String owner = redisTemplate.opsForValue().get(key);

        if (userId.toString().equals(owner)) {
            redisTemplate.delete(key);
        }
    }

    public boolean isSeatLocked(Long eventId, Long seatId) {

        String key = "seat:" + eventId + ":" + seatId;

        return Boolean.TRUE.equals(
                redisTemplate.hasKey(key)
        );
    }
}