package com.peierlong.best.practices;

import java.util.Date;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/12/7 上午11:27
 * 描述 : 引用了可变实例后存在安全隐患(第三十九条: 必要时进行保护性拷贝)
 */
public class Period {
    private final Date start;
    private final Date end;

    /**
     * @param start start
     * @param end   end
     * @throws NullPointerException     the start or end is null
     * @throws IllegalArgumentException if the start after the end
     */
    public Period(Date start, Date end) {
        //解决方案, 使用保护性拷贝, 不持有可变实例的引用
//        this.start = new Date(start.getTime());
//        this.end = new Date(end.getTime());

        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
        this.start = start;
        this.end = end;
    }

    public Date start() {
        //解决方案
//        return new Date(start.getTime());
        return start;
    }

    public Date end() {
//        return new Date(end.getTime());
        return end;
    }

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period period = new Period(start, end);

        System.out.println(period.start() + " " + period.end());
        end.setTime(1481082242000L);
        System.out.println(period.start() + " " + period.end());

    }
}
