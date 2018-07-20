package com.peierlong.lambda;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 利用reduce方法进行求和
 *
 * @author elong
 * @version V1.0
 * @date 2018/7/20
 */
public class SumDemo {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 5, 6, 7, 8, 9);
        Integer value = list.stream().reduce((sum, item) -> sum + item).get();
        System.out.println(value);
    }

}
