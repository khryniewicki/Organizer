package com.khryniewicki.organizer.main_content.DTO;

import java.util.HashMap;

public enum LogHashMap {
    LOG_HASH_MAP;

    private    HashMap<Long,HashMap<Long,Boolean>> hashmap=new HashMap<>();

    public   HashMap<Long, HashMap<Long, Boolean>> getHashmap() {
        return hashmap;
    }
}
