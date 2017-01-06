package com.peierlong.best.practices;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/12/1 下午2:17
 * 描述 : 一个标签类示例 (有缺点)
 */
public class Figure {
    enum Shape {RECTANGLE, CIRCLE}

    final Shape shape;

    double length;
    double width;

    double radius;

    public Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    public Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area() {
        switch (shape) {
            case CIRCLE:
                return Math.PI * radius * radius;
            case RECTANGLE:
                return length * width;
        }
        return 0;
    }
}


