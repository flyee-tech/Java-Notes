package com.peierlong.design.patterns;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/11/25 上午11:54
 * 描述 : Builder模式Demo
 * 适用场景, 实例化类时参数过多
 */
class Student {
    private final String name;
    private final Integer age;
    private final String address;
    private final char sex;

    private Student(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
        this.sex = builder.sex;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public char getSex() {
        return sex;
    }

    private static class Builder {
        private final String name;
        private final Integer age;

        private String address;
        private char sex;

        public Builder(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        private Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        private Builder setSex(char sex) {
            this.sex = sex;
            return this;
        }

        private Student build() {
            return new Student(this);
        }
    }

    public static void main(String[] args) {
        Student student = new Student.Builder("张三", 3).setSex('男').setAddress("北京").build();
        System.out.println(student.getName());
    }
}
