package com.peierlong.design.patterns.command;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

}
