package com.gpSolution.secondStep.calculator;

import java.io.*;

public class Runner {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out))) {
            System.out.println("Enter an expression");
            String expression = bufferedReader.readLine();
            String result = Calculator.calculate(expression);
            if(null != result) {
                bufferedWriter.write(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
