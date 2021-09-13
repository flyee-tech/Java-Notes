package com.peierlong.best.practices;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/12/3 下午1:45
 * 描述 : 类型安全的异构容器的实现
 */
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    private <T> void putFavorite(Class<T> type, T instance) {
        if (type == null) {
            throw new NullPointerException("type is null");
        }
        favorites.put(type, instance);
    }

    private <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
    }

    @Test
    public void testTypeSafeHeterogeneousContainer(){
        Favorites favorites = new Favorites();
        favorites.putFavorite(String.class, "hello world");
        favorites.putFavorite(Integer.class, 123);
        favorites.putFavorite(Class.class, Favorites.class);

        System.out.println(favorites.getFavorite(String.class));
        System.out.println(favorites.getFavorite(Integer.class));
        System.out.println(favorites.getFavorite(Class.class));

    }

}
