package com.peierlong.test;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 包名: com.peierlong.test
 * 创建人 : Elong
 * 时间: 11/04/2017 3:39 PM
 * 描述 : 链表实现
 */
public class Test2 {

    public static void main(String[] arg) {
        System.out.println("\n最终结果: " + getLastNumber(5, 3));
        System.out.println("\n最终结果: " + getLastNumber(9, 3));
        System.out.println("\n最终结果: " + getLastNumber(100, 11));
    }

    public static int getLastNumber(int m, int n) {
        //初始化数据集合 和 结果排序集合
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> resultList = new LinkedList<>();
        for (int i = 1; i <= m; i++) {
            list.add(i);
        }
        //不断迭代，对符合去除条件的元素依次插入resultList
        int i = 1;
        while (list.size() > 0) {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int countNum = it.next();
                if (i == n) {
                    resultList.add(countNum);
                    it.remove();
                    i = 0;
                }
                i++;
            }
        }
        //遍历排序后的集合，并取出最后一个元素返回
        int result = 0;
        System.out.print("元素的跳出顺序: ");
        for (Integer aResultList : resultList) {
            System.out.print(" " + aResultList);
            result = aResultList;
        }
        return result;
    }

}
