package com.gpSolution.test670;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
        String string = bufferedReader.readLine();
        int x = Integer.parseInt(string);
        List<Integer> list = new ArrayList<>();
        int count;
        for (int i = 0; list.size() <= x ; i++) {
            boolean flag = false;
            int y = i;
            boolean[] mass = new boolean[10];

            while (y > 0) {
                count = y % 10;
                if (mass[count]) {
                    flag = true;
                    break;
                } else {
                    mass[count] = true;
                }
                y = y / 10;
                }
            if (!flag) {
                list.add(i);
            }
        }
        bufferedWriter.write(list.get(x)
                + "");
        bufferedWriter.close();
        bufferedReader.close();
    }
}
