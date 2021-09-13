package com.peierlong.concurrency.cache;

import java.math.BigInteger;

/**
 * 包名: com.elong.concurrency.custom.cache
 * 创建人 : Elong
 * 时间: 2016/12/29 下午3:59
 * 描述 :
 */
public class ComputableImpl implements Computable<String, BigInteger> {

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //耗时计算……
        return new BigInteger("0");
    }

}
