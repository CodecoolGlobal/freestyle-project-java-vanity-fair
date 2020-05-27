package com.codecool.termlib;

public class Game implements GameInterface {

    private int[][] board;
    private int nRows, nCols;
    private Terminal terminal;

    /**
     * Build new board with nRows x nCols dimensions
     *
     * @param nRows rows number
     * @param nCols cols number
     */
    public Game(int nRows, int nCols) {
        terminal = new Terminal();
        this.nRows = nRows;
        this.nCols = nCols;
        board = new int[nRows][nCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Board getter
     *
     * @return game board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Board setter
     *
     * @param board // game board
     */
    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getMove(int player) {
        return null;
    }

    public int[] getAiMove(int player) {
        return null;
    }

    public void mark(int player, int row, int col) {
    }

    public boolean hasWon(int player, int howMany) {
        return false;
    }

    public boolean isFull() {
        return false;
    }

    /**
     * Prints the board on the screen
     */
    public void printBoard() {
        //clear old screen
        terminal.clearScreen();
        terminal.moveTo(0, 0);

        //print cols numbers
        for (int j = 0; j < nCols; j++) {
            System.out.printf("%3d", j + 1);
        }

        //print board rows
        for (int i = 0; i < nRows; i++) {
            System.out.printf("\n%c", 'A' + i);
            for (int j = 0; j < nCols; j++) {
                if (board[i][j] == 0) {
                    System.out.print(" . ");
                } else if (board[i][j] == 1) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" O ");
                }
            }
        }
        System.out.println("\n");
    }

    public void printResult(int player) {
    }

    public void enableAi(int player) {
    }

    public void play(int howMany) {

    }
}
