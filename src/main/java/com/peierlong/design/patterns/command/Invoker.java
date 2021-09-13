package com.peierlong.design.patterns.command;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class Invoker {
    private Command[] onCommand;
    private Command[] offCommand;
    private final int slotNum = 7;

    public Invoker() {
        this.onCommand = new Command[slotNum];
        this.offCommand = new Command[slotNum];
    }

    public void setOnCommand(Command onCommand, int slot) {
        this.onCommand[slot] = onCommand;
    }

    public void setOffCommand(Command offCommand, int slot) {
        this.offCommand[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommand[slot].execute();
    }

    public void offButtonWasPushed(int slot) {
        offCommand[slot].execute();
    }

}
