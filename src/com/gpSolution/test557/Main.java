package com.gpSolution.test557;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String x = bufferedReader.readLine();
        String y = bufferedReader.readLine();
        String z = bufferedReader.readLine();
        String[] numberAndSize = x.split(" ");
        int m = Integer.parseInt(numberAndSize[0]);
        int n = Integer.parseInt(numberAndSize[1]);
        String[] DesiredMatrixElement = y.split(" ");
        int line = Integer.parseInt(DesiredMatrixElement[0]);
        int column = Integer.parseInt(DesiredMatrixElement[1]);
        int p = Integer.parseInt(z);

        int[][][] allMatrix = new int[m][n][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String o = bufferedReader.readLine();
                if (o.isEmpty()) {
                    o = bufferedReader.readLine();
                }
                String[] elements = o.split(" ");
                for (int k = 0; k < n; k++) {
                    allMatrix[i][j][k] = Integer.parseInt(elements[k]);
                }
            }
        }
        int[] resultLine = allMatrix[0][line - 1];
        for (int i = 1; i < m; i++) {
            resultLine = mult(resultLine, allMatrix[i], p, n);
        }
        System.out.println(resultLine[column - 1]);
    }

    private static int[] mult(int[] x, int[][] y, int p, int n) {
        int result[] = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += x[j] * y[j][i];
            }
            if (result[i] > p) {
                result[i] = result[i] % p;
            }
        }
        return result;
    }
}
