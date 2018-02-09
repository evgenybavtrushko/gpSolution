package com.gpSolution.firstStep.test278;

import java.io.*;

public class Main {
    public static void main(String[] argv) throws IOException {
        File file = new File("input.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
        String s = bufferedReader.readLine();
        String t = bufferedReader.readLine();
        int j = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j<t.length()){
                if (s.charAt(i) == t.charAt(j)) {
                    count++;
                    j++;
                    break;
                }
                j++;
            }
        }
        if (s.length()!=0 && count == s.length()) {
            bufferedWriter.write("YES");
        } else {
            bufferedWriter.write("NO");
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}