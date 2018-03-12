package com.baomidou.springboot.entity.enums;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.concurrent.ConcurrentMap;

/**
 * MPAdb使用，通过枚举创建单例
 */
public enum  MapDbEnum {
    MAPDB;
    private DB db ;
    MapDbEnum(){
        db = DBMaker.memoryDB().make();
    }
    public boolean put(String key,Object value){
        try {
            ConcurrentMap map = db.hashMap("map").createOrOpen();
            map.put(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public Object get(String key){
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        Object o = map.get(key);
        return o;
    }

    public boolean remove(String key){
        try {
            ConcurrentMap map = db.hashMap("map").createOrOpen();
            map.remove(key);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

}
