package com.supergo.page1.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 全局锁
 */
@Component
public class GoddsLock {

    //线程安全hashmap、单例
    private ConcurrentHashMap<Long, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    //获取锁
    public ReentrantLock getLock(Long goodsId) {
        return lockMap.getOrDefault(goodsId, new ReentrantLock());
    }

    //移除锁
    public void removeLock(Long goodsId) {
        lockMap.remove(goodsId);
    }
}
