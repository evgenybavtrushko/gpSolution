package com.gpSolution.firstStep.test578;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] argv) throws IOException {
        int negativeNumber = 0;
        int positiveNumber = 0;
        int countN = 0;
        int countP = 0;
        Scanner scanner = new Scanner(new File("input.txt"));
        int size = scanner.nextInt();
        int[] negative = new int[size];
        int[] positive = new int[size];
        for (int i = 0; i < size; i++) {
            int x = scanner.nextInt();
            if (x < 0) {
                negativeNumber += x;
                negative[countN] = 1 + i;
                countN++;
            } else {
                positiveNumber += x;
                positive[countP] = 1 + i;
                countP++;
            }
        }
        if (Math.abs(negativeNumber) > positiveNumber) {
            System.out.println(countN);
            for (int x : negative) {
                if (x != 0) {
                    System.out.print((x) + " ");
                }
            }
        } else {
            System.out.println(countP);
            for (int x : positive) {
                if (x != 0) {
                    System.out.print((x) + " ");
                }
            }
        }
    }
}