package com.peierlong.best.practices;

import java.util.HashMap;
import java.util.Map;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/11/29 下午1:08
 * 描述 : 第九条, 覆盖equals的同时要覆盖hashCode
 */
public class PhoneNumber {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumber(short areaCode, short prefix, short lineNumber) {
        rangeCheck(areaCode, 999, "areaCode");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "lineNumber");

        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }

    private void rangeCheck(int arg, int max, String name) {
        if (arg < 0 || arg > max) {
            throw new IllegalArgumentException(name + ": " + arg);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber pn = (PhoneNumber) obj;
        return pn.areaCode == areaCode && pn.prefix == prefix && pn.lineNumber == lineNumber;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + areaCode;
        result = 31 * result + prefix;
        result = 31 * result + lineNumber;
        return result;
    }

    public static void main(String[] args) {
        Map<PhoneNumber, String> map = new HashMap<>();
        map.put(new PhoneNumber((short) 66, (short) 66, (short) 666), "Tom");

        System.out.println(map.get(new PhoneNumber((short) 66, (short) 66, (short) 666)));
        //覆盖equals方法, 不覆盖hashCode方法的时候, 返回null
        //当hashCode和equals方法同时覆盖的时候, 返回Tom

    }

}
