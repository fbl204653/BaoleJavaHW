package org.example;

import java.util.Arrays;

public class AdvancedMathUtility {
    private int maxHistorySize;
    private String[] calculationHistory;
    private boolean suppressHistory = false;

    public void setCalculationHistory(String[] calculationHistory) {
        this.calculationHistory = calculationHistory;
    }

    public void setMaxHistorySize(int maxHistorySize) {
        if(maxHistorySize < 10 || maxHistorySize >100){
            //报错
            throw new RuntimeException("maxHistorySize has to be within [10, 100]");
        }
        this.maxHistorySize = maxHistorySize;
    }

    public String[] getCalculationHistory() {
        return calculationHistory;
    }

    public int getMaxHistorySize() {
        return maxHistorySize;
    }

    private void addToHistory(String cal){
        if(calculationHistory.length >= maxHistorySize){
            clearHistory();
        }
        String[] tempArr = new String[calculationHistory.length+1];
        for(int i = 0; i < calculationHistory.length; i++){
            tempArr[i] = calculationHistory[i];
        }
        tempArr[calculationHistory.length] = cal;
        calculationHistory = tempArr;
    }

    public void clearHistory(){
        calculationHistory = new String[0];
    }

    public double add(double a, double b){
        double tempSum = a + b;
        if(!suppressHistory){
            String cal = "add(double " + a +", double "+ b +") = " + tempSum;
            addToHistory(cal);
        }
        return tempSum;
    }

    public double add(double[] numbers){
        double tempSum = 0;
        for (double number : numbers) {
            tempSum += number;
        }
        if(!suppressHistory){
            String cal = "add(double[] " + Arrays.toString(numbers) + ")= " + tempSum;
            addToHistory(cal);
        }
        return tempSum;
    }

    public double multiply(double a, double b){
        double tempMul = a * b;
        if(!suppressHistory){
            String cal = "multiply(double " + a +", double "+ b +") = " + tempMul;
            addToHistory(cal);
        }
        return tempMul;
    }

    public double multiply(double[] numbers){
        double tempMul = 1;
        for (double number : numbers){
            tempMul *= number;
        }
        if(!suppressHistory){
            String cal = "multiply(double[] " + Arrays.toString(numbers) + ")= " + tempMul;
            addToHistory(cal);
        }
        return tempMul;
    }

    public double power(double base, int exponent) {
        double tempPow = 1;
        if (exponent < 0) {
            throw new RuntimeException("exponent can't be negative");
        } else if (exponent == 0) {
            tempPow = 1;
        } else {
            for (int i = 0; i < exponent; i++) {
                tempPow *= base;
            }
        }
        if(!suppressHistory){
            String cal = "power(double " + base +", int "+ exponent +") = " + tempPow;
            addToHistory(cal);
        }
        return tempPow;
    }

    public double power(double base){
        double tempPow = base * base;
        if(!suppressHistory){
            String cal = "power(double " + base + ") = " + tempPow;
            addToHistory(cal);
        }
        return tempPow;
    }

    public double average(double a, double b){
        double tempAvg = (a + b)/2;
        if(!suppressHistory){
            String cal = "average(double " + a +", double "+ b +") = " + tempAvg;
            addToHistory(cal);
        }
        return tempAvg;
    }

    public double average(double[] numbers){
        suppressHistory = true;
        double tempAvg = add(numbers)/numbers.length;
        suppressHistory = false;
        if(!suppressHistory){
            String cal = "average(double[] " +  Arrays.toString(numbers) + ") = " + tempAvg;
            addToHistory(cal);
        }
        return tempAvg;
    }

    public double variance(double[] population){
        suppressHistory = true;
        double[] popSquare = new double[population.length];
        for (int i = 0; i < population.length; i++){
            popSquare[i] = power(population[i]);
        }
        double tempVar = (add(popSquare) - population.length * power(average(population))) / (population.length - 1);
        suppressHistory = false;
        if(!suppressHistory){
            String cal = "variance(double[] " +  Arrays.toString(population) + ") = " + tempVar;
            addToHistory(cal);
        }
        return tempVar;
    }

    public static void main(String[] args){
        AdvancedMathUtility calculation = new AdvancedMathUtility();
        calculation.setMaxHistorySize(11);
        calculation.setCalculationHistory(new String[0]);
        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
        System.out.println(calculation.add(new double[]{6,7,8,9,10}));
        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
        System.out.println(calculation.add(10,12));
        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
        System.out.println(calculation.variance(new double[]{1,2,3,4}));
        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
//        calculation.clearHistory();
//        System.out.println(calculation.power(6));
//        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
//        System.out.println(calculation.power(8));
//        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
//        System.out.println(calculation.power(0));
//        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
//        System.out.println(calculation.power(2,1));
//        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
//        System.out.println(calculation.power(2,0));
//        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
//        System.out.println(calculation.power(-6,3));
//        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
//        System.out.println(calculation.power(2,-2));
//        System.out.println(calculation.variance(new double[]{1,2,3,4}));
//        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
//        System.out.println(calculation.variance(new double[]{1}));
//        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
//        System.out.println(calculation.variance(new double[]{10,100}));
//        System.out.println(Arrays.toString(calculation.getCalculationHistory()));
    }
}
