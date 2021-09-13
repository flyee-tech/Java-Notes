package com.peierlong.concurrency;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 构建一个线程安全的DateFormatter
 *
 * @author elong
 * @version V1.0
 * @date 2018/7/20
 */
public class SafeDateFormatter {

    private static final ThreadLocal<DateFormatter> formatter =
            ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("yyyy-MM-dd")));


    public static void main(String[] args) {
        DateFormatter dateFormatter = formatter.get();
        String format = dateFormatter.getFormat().format(new Date());
        System.out.println(format);
    }
}
