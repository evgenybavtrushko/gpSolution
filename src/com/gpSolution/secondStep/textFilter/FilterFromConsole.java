package com.gpSolution.secondStep.textFilter;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterFromConsole {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.print("Invalid parameters for program. ");
            System.err.println("Program must have one parameter.");
            return;
        }
        String currentLine;
        boolean flag;
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            while (!(currentLine = bufferedReader.readLine().trim()).equals("")) {
                String[] mass = currentLine.split(" ");
                flag = false;
                for (String keyWord : args) {
                    if (flag) {
                        break;
                    }
                    Pattern pattern = Pattern.compile(keyWord);
                    for (String coincidence : mass) {
                        Matcher matcher = pattern.matcher(coincidence.trim());
                        if (matcher.matches()) {
                            bufferedWriter.write(currentLine.trim() + '\n');
                            flag = true;
                            break;
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            {
            }
        }
    }
}
