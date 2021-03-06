package com.codecool.termlib;

import java.util.Scanner;

public class Main {
    private static String MENU = "Project Menu:\n" +
            "a : Set font color\n" +
            "b : Set background color\n" +
            "c : Reset all settings\n" +
            "d : Set italics\n" +
            "e : Set underscore\n" +
            "f : Play Five in a Row Game\n" +
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
        Terminal terminal = new Terminal();

        while (runProgram) {
            terminal.clearScreen();
            terminal.moveTo(0, 0);
            System.out.println(MENU);

            System.out.print("User choice: ");
            String userInput = scan.nextLine();

            switch (userInput) {
                case "a":
                    System.out.println(COLOR_SUBMENU);

                    System.out.print("User choice: ");
                    userInput = scan.nextLine();
                    switch (userInput) {
                        case "1":
                            terminal.setColor(Color.RED);
                            break;
                        case "2":
                            terminal.setColor(Color.GREEN);
                            break;
                        case "3":
                            terminal.setColor(Color.YELLOW);
                            break;
                        case "4":
                            terminal.setColor(Color.BLUE);
                            break;
                        case "5":
                            terminal.setColor(Color.MAGENTA);
                            break;
                        case "6":
                            terminal.setColor(Color.CYAN);
                            break;
                        case "7":
                            terminal.setColor(Color.WHITE);
                            break;
                        default:
                            terminal.setColor(Color.BLACK);
                            break;
                    }
                    System.out.println(MENU);
                    break;
                case "b":
                    System.out.println(COLOR_SUBMENU);

                    System.out.print("User choice: ");
                    userInput = scan.nextLine();
                    switch (userInput) {
                        case "1":
                            terminal.setBgColor(Color.RED);
                            break;
                        case "2":
                            terminal.setBgColor(Color.GREEN);
                            break;
                        case "3":
                            terminal.setBgColor(Color.YELLOW);
                            break;
                        case "4":
                            terminal.setBgColor(Color.BLUE);
                            break;
                        case "5":
                            terminal.setBgColor(Color.MAGENTA);
                            break;
                        case "6":
                            terminal.setBgColor(Color.CYAN);
                            break;
                        case "7":
                            terminal.setBgColor(Color.WHITE);
                            break;
                        default:
                            terminal.setBgColor(Color.BLACK);
                            break;
                    }
                    System.out.println(MENU);
                    break;
                case "c":
                    terminal.resetStyle();
                    break;
                case "d":
                    terminal.setStyle(Attribute.ITALIC);
                    break;
                case "e":
                    terminal.setStyle(Attribute.UNDERSCORE);
                    break;
                case "f":
                    Game game = new Game(11, 11);

                    game.play(5);

                    break;
                case "z":
                    runProgram = false;
                    break;
            }

        }
        scan.close();
    }
}
