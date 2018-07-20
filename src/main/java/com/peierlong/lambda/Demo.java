package com.peierlong.lambda;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

/**
 * @author elong
 * @version V1.0
 * @date 2018/7/20
 */
public class Demo {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);

        int sum = list.stream()
                //过滤空字段
                .filter(Objects::nonNull)
                //去重
                .distinct()
                //所有字段*2
                .mapToInt(num -> num * 2)
                //跳过前两个字段
                .skip(2)
                //只取4条
                .limit(4)
                .peek(System.out::println)
                .sum();
        System.out.println(sum);

    }

}
