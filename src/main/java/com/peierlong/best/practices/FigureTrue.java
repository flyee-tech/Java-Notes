package com.peierlong.best.practices;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/12/1 下午2:35
 * 描述 : 类层次优于标签类
 */
public abstract class FigureTrue {
    abstract double area();
}

class Circle extends FigureTrue {

    final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius * radius);
    }

}

class Rectangle extends FigureTrue {

    final double length;
    final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }
}