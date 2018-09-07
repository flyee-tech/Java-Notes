package com.peierlong.design.patterns.memento;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class PreviousCalculationImpl implements PreviousCalculationToCareTaker, PreviousCalculationToOriginator {
    private Integer firstNumber;
    private Integer lastNumber;

    public PreviousCalculationImpl(Integer firstNumber, Integer lastNumber) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
    }

    @Override
    public int getFirstNumber() {
        return this.firstNumber;
    }

    @Override
    public int getSecondNumber() {
        return this.lastNumber;
    }
}
