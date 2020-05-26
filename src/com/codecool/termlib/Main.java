package com.codecool.termlib;

import java.util.Scanner;

public class Main {
    private static String MENU = "Project Menu:\n" +
            "a : Set font color\n" +
            "b : Set background color\n" +
            "c : Reset all settings\n" +
            "d : Set italics\n" +
            "z : Exit program\n";

    private static String COLOR_SUBMENU = "Color Submenu:\n" +
            "1 : red\n" +
            "2 : green\n" +
            "3 : yellow\n" +
            "4 : blue\n" +
            "5 : magenta\n" +
            "6 : cyan\n" +
            "7 : white\n" +
            "8 : black\n";

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        boolean runProgram = true;

        while (runProgram) {
            Terminal terminal = new Terminal();

            System.out.println(MENU);

            String userInput = scan.nextLine();

            if (userInput.equals("a")) {
                System.out.println(COLOR_SUBMENU);

                userInput = scan.nextLine();
                if (userInput.equals("1")) {
                    terminal.setColor(Color.RED);
                } else if (userInput.equals("2")) {
                    terminal.setColor(Color.GREEN);
                } else if (userInput.equals("2")) {
                    terminal.setColor(Color.GREEN);
                } else if (userInput.equals("3")) {
                    terminal.setColor(Color.YELLOW);
                } else if (userInput.equals("4")) {
                    terminal.setColor(Color.BLUE);
                } else if (userInput.equals("5")) {
                    terminal.setColor(Color.MAGENTA);
                } else if (userInput.equals("6")) {
                    terminal.setColor(Color.CYAN);
                } else if (userInput.equals("7")) {
                    terminal.setColor(Color.WHITE);
                } else {
                    terminal.setColor(Color.BLACK);
                }
                System.out.println(MENU);
            } else if (userInput.equals("b")) {
                System.out.println(COLOR_SUBMENU);

                userInput = scan.nextLine();
                if (userInput.equals("1")) {
                    terminal.setBgColor(Color.RED);
                } else if (userInput.equals("2")) {
                    terminal.setBgColor(Color.GREEN);
                } else if (userInput.equals("3")) {
                    terminal.setBgColor(Color.YELLOW);
                } else if (userInput.equals("4")) {
                    terminal.setBgColor(Color.BLUE);
                } else if (userInput.equals("5")) {
                    terminal.setBgColor(Color.MAGENTA);
                } else if (userInput.equals("6")) {
                    terminal.setBgColor(Color.CYAN);
                } else if (userInput.equals("7")) {
                    terminal.setBgColor(Color.WHITE);
                } else {
                    terminal.setBgColor(Color.BLACK);
                }
                System.out.println(MENU);
            } else if (userInput.equals("c")) {
                terminal.resetStyle(Attribute.RESET);
            } else if (userInput.equals("d")) {
                terminal.resetStyle(Attribute.UNDERSCORE);
            } else if (userInput.equals("z")) {
                runProgram = false;
            }

        }
        scan.close();
    }
}
