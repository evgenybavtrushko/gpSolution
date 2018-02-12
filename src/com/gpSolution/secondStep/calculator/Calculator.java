package com.gpSolution.secondStep.calculator;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Calculator {
    public static String calculate(String expression) {
        String result = null;
        try {
            expressionIsValid(expression);
            String reversePolishNotation = convertToReversePolishNotation(expression);
            result = executeReversePolishNotation(reversePolishNotation);

        } catch (ExpressionInvalidException e) {
            System.err.println(e);
        } catch (EmptyStackException e) {
            System.err.println("Parse error occurred. Check the correctness of the expression.");
        } catch (NumberFormatException | NoSuchElementException e) {
            System.err.println("Check the correctness of the expression.");
        } catch (ArithmeticException e) {
            System.err.println("Arithmetic error. Division by zero");
        }
        return result;
    }

    private static boolean expressionIsValid(String expression) throws ExpressionInvalidException {
        Pattern pattern = Pattern.compile("(\\-*\\(*\\-*\\d+([\\.|\\,]\\d+)?\\)*\\s*[\\+\\-\\*\\/\\^]\\s*)+(\\-*\\d+([\\.|\\,]\\d+)?\\)*)$");
        Matcher matcher = pattern.matcher(expression);
        if (!matcher.matches()) {
            Pattern pattern2 = Pattern.compile(".*\\d+([\\.|\\,]\\d+)?\\s+\\d+([\\.|\\,]\\d+)?.*");
            Matcher matcher2 = pattern2.matcher(expression);
            if (matcher2.matches()) {
                throw new ExpressionInvalidException("There must be no spaces between the numbers");
            }else {
                throw new ExpressionInvalidException("Check the correctness of the expression.");
            }
        }
        return true;
    }

    private static String convertToReversePolishNotation(String expression) throws EmptyStackException {
        StringBuilder outExpression = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        Character checkedCharacter;
        Character tmpCharacter;
        boolean x = true;
        for (int i = 0; i < expression.length(); i++) {
            checkedCharacter = expression.charAt(i);
            if (isOperator(checkedCharacter)) {
                if (x) {
                    outExpression.append(" ").append(0).append(" ");
                    stack.push(checkedCharacter);
                } else {
                    while (!(stack.empty())) {
                        tmpCharacter = stack.peek();
                        if (isOperator(tmpCharacter) && getOperatorPriority(checkedCharacter) <= getOperatorPriority(tmpCharacter)) {
                            outExpression.append(" ").append(stack.pop());
                        } else {
                            outExpression.append(" ");
                            break;
                        }
                    }
                    x = true;
                    outExpression.append(" ");
                    stack.push(checkedCharacter);
                }
            } else if ('(' == checkedCharacter) {
                x = false;
                stack.push(checkedCharacter);
                outExpression.append(" ");
            } else if (')' == checkedCharacter) {
                tmpCharacter = stack.peek();
                while ('(' != tmpCharacter) {
                    outExpression.append(" ").append(stack.pop());
                    tmpCharacter = stack.peek();
                }
                stack.pop();
            } else if (' ' != checkedCharacter) {
                x = false;
                outExpression.append(checkedCharacter);
            }
        }
        while (!(stack.empty())) {
            outExpression.append(" ").append(stack.pop());
        }
        return outExpression.toString().replaceAll(",", ".");
    }


    private static String executeReversePolishNotation(String reversePolishNotation) throws NumberFormatException, NoSuchElementException, ArithmeticException {
        System.out.println(reversePolishNotation);
        BigDecimal bA;
        BigDecimal bB;
        String sTmp;
        Deque<BigDecimal> stack = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(reversePolishNotation);
        while (st.hasMoreTokens()) {
            sTmp = st.nextToken().trim();
            if (1 == sTmp.length() && isOperator(sTmp.charAt(0))) {
                bB = stack.pop();
                bA = stack.pop();
                switch (sTmp.charAt(0)) {
                    case '+':
                        bA = bA.add(bB);
                        break;
                    case '-':
                        bA = bA.subtract(bB);
                        break;
                    case '/':
                        bA = bA.divide(bB, 10, 2);
                        break;
                    case '*':
                        bA = bA.multiply(bB);
                        break;
                    case '^':
                        if (bB.doubleValue() < 0) {
                            bA = BigDecimal.valueOf(Math.pow(bA.doubleValue(), bB.doubleValue()));
                            break;
                        } else {
                            bA = bA.pow(bB.intValue());
                            break;
                        }
                }
                stack.push(bA);
            } else {
                bA = new BigDecimal(sTmp);
                stack.push(bA);
            }
        }
        return stack.pop().toString();
    }


    private static boolean isOperator(Character checkedCharacter) {
        switch (checkedCharacter) {
            case '-':
            case '+':
            case '*':
            case '/':
            case '^':
                return true;
        }
        return false;
    }

    private static byte getOperatorPriority(Character checkedCharacter) {
        switch (checkedCharacter) {
            case '^':
                return 2;
            case '*':
            case '/':
                return 1;
        }
        return 0;
    }
}
