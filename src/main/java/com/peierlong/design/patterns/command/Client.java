package com.peierlong.design.patterns.command;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class Client {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Light light = new Light();

        LightOnComand lightOnComand = new LightOnComand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        invoker.setOnCommand(lightOnComand, 0);
        invoker.setOffCommand(lightOffCommand, 0);

        invoker.onButtonWasPushed(0);
        invoker.offButtonWasPushed(0);
    }
}
