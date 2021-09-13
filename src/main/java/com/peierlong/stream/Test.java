package com.peierlong.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Peiel
 * @version V1.0
 * @date 2020/4/8
 */
public class Test {

    /**
     * 流的常见构造方式
     */
    private void test1() {
        // 1. Individual values
        Stream<String> s1 = Stream.of("a", "b", "c");
        // 2. Arrays
        String[] sArray = {"a", "b", "c"};
        Stream<String> s2 = Stream.of(sArray);
        Stream<String> s3 = Arrays.stream(sArray);
        // 3. Collections
        List<String> list = Arrays.asList(sArray);
        Stream<String> s4 = list.stream();
        Stream<String> s5 = list.parallelStream();
    }

    /**
     * 数值流的构造
     */
    private static void test2() {
        IntStream.of(1, 2, 3).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }

    /**
     * 转换为其他的数据结构
     * 注：一个stream只能使用一次，以下代码为了简洁使用了多次
     */
    private static void test3() {
        Stream<String> stream = Stream.empty();
        // 1. Array
        String[] strArray = stream.toArray(String[]::new);
        // 2. Collection
        List<String> list1 = stream.collect(Collectors.toList());
        List<String> arrayList2 = stream.collect(Collectors.toCollection(ArrayList::new));
        // 3. String
        String str = stream.collect(Collectors.joining()).toString();
    }


    public static void main(String[] args) {
        test2();
    }

}
