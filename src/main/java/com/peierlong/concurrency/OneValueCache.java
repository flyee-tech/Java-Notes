package com.peierlong.concurrency;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 包名: com.elong.concurrency
 * 创建人 : Elong
 * 时间: 2016/12/21 下午5:53
 * 描述 : 对数值及其因数分解结果进行缓存的不可变容器类
 */
public class OneValueCache {

    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
        this.lastNumber = lastNumber;
        this.lastFactors = Arrays.copyOf(lastFactors, lastFactors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }

}
