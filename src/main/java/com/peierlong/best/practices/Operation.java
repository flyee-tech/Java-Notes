package com.peierlong.best.practices;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/12/3 下午4:21
 * 描述 : 利用枚举类型来实现特定运算
 */
public enum Operation {
    PLUS("+") {
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    }, MINUS("-") {
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    }, TIMES("*") {
        @Override
        double apply(double x, double y) {
            return x * y;
        }
    }, DIVIDE("/") {
        @Override
        double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    abstract double apply(double x, double y);

    public static void main(String[] args) {
        double x = 2;
        double y = 4;
        for (Operation op : values()) {
            System.out.printf("%f %s %f = %f %n", x, op, y, op.apply(x, y));
        }
    }

}
