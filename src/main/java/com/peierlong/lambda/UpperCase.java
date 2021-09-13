package com.peierlong.lambda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author elong
 * @version V1.0
 * @date 2018/7/20
 */
public class UpperCase {

    public static void main(String[] args) {
        List<String> list = Stream.of("b", "a", "c").collect(Collectors.toList());
        List<String> newList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(newList);
    }

}
