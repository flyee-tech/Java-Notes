package com.peierlong.design.patterns.memento;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public interface Calculator {
    // Create Memento
    PreviousCalculationToCareTaker backupLastCalculation();
    //set Memento
    void restorePreviousCalculation(PreviousCalculationToCareTaker memento);

    int getCalculationResult();

    void setFirstNumber(int firstNumber);

    void setSecondNumber(int secondNumber);

}
