package com.company;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    private Stack<Integer> stack = new Stack<Integer>();
    private Scanner scanner = new Scanner(System.in);

    // Since there is a statement that the input will always be valid I will skip wrong input checks.

    public void run() {
        boolean flag = true;
        while (flag) {
            String command = scanner.nextLine().toLowerCase().trim();
            if (command.contains("push")) {
                StringBuilder stringBuilder = new StringBuilder(command);
                if (stringBuilder.length() > 4) {
                    stringBuilder.delete(0, 4);
                    String input = stringBuilder.toString().trim();
                    int unsignedInt = Integer.parseUnsignedInt(input, 10);
                    push(unsignedInt);
                    System.out.println(stackString());
                }
                continue;
            }
            if (command.equals("pop")) {
                pop();
                System.out.println(stackString());
                continue;
            }
            if (command.equals("add")) {
                add();
                System.out.println(stackString());
                continue;
            }
            if (command.equals("sub")) {
                sub();
                System.out.println(stackString());
            }
            if (command.equals("quit")) {
                flag = false;
            }

            //Following command is just for the fun of it...
            if (command.equals("funk")) {
                openWebPage();
            }
        }
    }

    private String stackString() {
        if (stack.isEmpty()) {
            return "(stack is EMPTY)";
        }
        String stackString = getStack().toString();
        StringBuilder stringBuilder = new StringBuilder(stackString);
        stringBuilder.deleteCharAt(0);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return "(stack is " + stringBuilder.toString() + ")";
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    private void push(int value) {
        if (stack.size() < 5) {
            int int10bit = valueConversion(value);
            stack.push(int10bit);
        } else {
            System.out.println("Error: Stack size is at maximum capacity");
        }
    }

    /*Since there is a requirement for only 10 bit unsigned integers to be stored in stack:
            10 bit unsigned integer capacity is 2^10 = 1024;
            Min 10 bit unsigned integer value = 0;
            Max 10 bit unsigned integer value is 2^10-1 = 1023;
      Personal note: Using short for max value and byte for min value so the calculator would take less resources.*/
    private int valueConversion(int value) {
        int currentValue = value;
        byte min = 0;
        short max = 1023;
        while (currentValue < min) {
            currentValue = Math.abs(max + currentValue);
        }
        while (currentValue > max) {
            currentValue = Math.abs(max - currentValue);
        }
        return currentValue;
    }

    private void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    private void add() {

        if (stack.size() >= 2) {
            int n1 = stack.pop();
            int n2 = stack.pop();
            int sum = n1 + n2;
            push(sum);
            System.out.print(">> " + getStack().lastElement() + " ");
        } else {
            System.out.println("Error: Stack size is lower than 2");
        }
    }

    private void sub() {
        if (stack.size() >= 2) {
            int n1 = stack.pop();
            int n2 = stack.pop();
            int sub = n1 - n2;
            push(sub);
            System.out.print(">> " + getStack().lastElement() + " ");
        } else {
            System.out.println("Error: Stack size is lower than 2");
        }
    }

    // Following methods are just for the MAXIMUM FUNK!
    private boolean openWebPage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean openWebPage(URL url) {
        try {
            return openWebPage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void openWebPage() {
        try {
            URL url = new URL("https://youtu.be/YgGzAKP_HuM?t=177");
            if (openWebPage(url)) {
                System.out.println("Achievement unlocked: MAXIMUM FUNK!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
