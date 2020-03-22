/*
 * Copyright (c) 2002, 2011, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 */

package source.java.io;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 时效性缓存
 */
class ExpiringCache {
    // 毫秒有效时间
    private long millisUntilExpiration;
    // 数据存储map
    private Map<String,Entry> map;
    // Clear out old entries every few queries
    // 每隔几个查询清除旧的条目
    private int queryCount;
    private int queryOverflow = 300;
    private int MAX_ENTRIES = 200;

    /**
     * 静态类对象
     */
    static class Entry {
        private long   timestamp;
        private String val;

        Entry(long timestamp, String val) {
            this.timestamp = timestamp;
            this.val = val;
        }

        long   timestamp()                  { return timestamp;           }
        void   setTimestamp(long timestamp) { this.timestamp = timestamp; }

        String val()                        { return val;                 }
        void   setVal(String val)           { this.val = val;             }
    }

    /**
     * 无参构造，默认超时30000毫秒
     */
    ExpiringCache() {
        this(30000);
    }

    @SuppressWarnings("serial")
    ExpiringCache(long millisUntilExpiration) {
        this.millisUntilExpiration = millisUntilExpiration;
        // 创建hash链表，判断是否超过最大容量Entry对象
        map = new LinkedHashMap<String,Entry>() {
            protected boolean removeEldestEntry(Map.Entry<String,Entry> eldest) {
              return size() > MAX_ENTRIES;
            }
          };
    }

    /**
     * 线程安全同步 获取值
     * @param key
     * @return
     */
    synchronized String get(String key) {
        if (++queryCount >= queryOverflow) {
            cleanup();
        }
        Entry entry = entryFor(key);
        if (entry != null) {
            return entry.val();
        }
        return null;
    }

    /**
     * 线程安全同步 存入值
     * @param key
     * @param val
     */
    synchronized void put(String key, String val) {
        if (++queryCount >= queryOverflow) {
            cleanup();
        }
        Entry entry = entryFor(key);
        if (entry != null) {
            entry.setTimestamp(System.currentTimeMillis());
            entry.setVal(val);
        } else {
            map.put(key, new Entry(System.currentTimeMillis(), val));
        }
    }

    /**
     * 线程安全同步 清除map存储对象
     */
    synchronized void clear() {
        map.clear();
    }

    /**
     * 根据key获取对象
     * @param key
     * @return
     */
    private Entry entryFor(String key) {
        Entry entry = map.get(key);
        if (entry != null) {
            // 系统时间减去存入时间
            long delta = System.currentTimeMillis() - entry.timestamp();
            // 判断是否过期移除，同时需要考虑小于0情况也需要移除
            if (delta < 0 || delta >= millisUntilExpiration) {
                map.remove(key);
                entry = null;
            }
        }
        return entry;
    }

    /**
     * 存储超过定义的溢出范围，需要清空所有map
     */
    private void cleanup() {
        Set<String> keySet = map.keySet();
        // Avoid ConcurrentModificationExceptions
        String[] keys = new String[keySet.size()];
        int i = 0;
        for (String key: keySet) {
            keys[i++] = key;
        }
        for (int j = 0; j < keys.length; j++) {
            entryFor(keys[j]);
        }
        queryCount = 0;
    }
}
