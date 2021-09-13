package com.peierlong.best.practices;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/11/30 下午3:14
 * 描述 : 使可变性最小化
 */
public final class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double realPart() {
        return re;
    }

    public double imaginaryPart() {
        return im;
    }

    public Complex add(Complex complex) {
        return new Complex(re + complex.re, im + complex.im);
    }

    public Complex subtract(Complex complex) {
        return new Complex(re - complex.re, im - complex.im);
    }

    public Complex multiply(Complex complex) {
        return new Complex(re * complex.re - im * complex.im,
                re * complex.re + im * complex.im);
    }

    public Complex divide(Complex complex) {
        double tem = complex.re * complex.re + complex.im * complex.im;
        return new Complex((re * complex.re + im * complex.im) / tem,
                (re * complex.re - im * complex.im) / tem);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Complex)) {
            return false;
        }
        Complex c = (Complex) obj;
        return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
    }

    @Override
    public int hashCode() {
        int result = 17 + hashDouble(re);
        result = 32 * result + hashDouble(im);
        return result;
    }

    private int hashDouble(double d) {
        long longBits = Double.doubleToLongBits(d);
        return (int) (longBits ^ (longBits >>> 32));
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + ")";
    }

}
