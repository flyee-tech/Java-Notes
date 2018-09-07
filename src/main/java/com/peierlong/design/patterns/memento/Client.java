package com.peierlong.design.patterns.memento;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class Client {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        calculator.setFirstNumber(10);
        calculator.setSecondNumber(100);
        System.out.println(calculator.getCalculationResult());

        PreviousCalculationToCareTaker memento = calculator.backupLastCalculation();

        calculator.setFirstNumber(17);
        calculator.setSecondNumber(-290);

        System.out.println(calculator.getCalculationResult());

        calculator.restorePreviousCalculation(memento);
        System.out.println(calculator.getCalculationResult());

    }
}
