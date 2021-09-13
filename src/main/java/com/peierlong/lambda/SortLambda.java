package com.peierlong.lambda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 使用Lambda表达式进行排序操作
 *
 * @author elong
 * @version V1.0
 * @date 2018/7/20
 */
public class SortLambda {
    public static void main(String[] args) {
        List<String> list = Stream.of("b", "a", "c").collect(Collectors.toList());
        list.sort(String::compareTo);
        System.out.println(list);
    }
}
