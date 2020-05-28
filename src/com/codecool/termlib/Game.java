package com.codecool.termlib;

import java.util.Arrays;
import java.util.Scanner;

public class Game implements GameInterface {

    private int[][] board;
    private int nRows;
    private int nCols;
    private Terminal terminal;
    private int player;
    private int moveCounter;
    private boolean userQuit;
    private int[] move; // move[0] = row; move[1] = col

    /**
     * Build new board with nRows x nCols dimensions
     *
     * @param nRows rows number
     * @param nCols cols number
     */
    public Game(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.board = new int[nRows][nCols];
        player = 1;
        moveCounter = 0;
        terminal = new Terminal();
        userQuit = false;

        for (int row = 0; row < nRows; ++row) {
            for (int col = 0; col < nCols; ++col) {
                this.board[row][col] = 0;
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

    /**
     * Asks for user input and returns the coordinates of a valid move on board.
     *
     * @param player number of player (1 or 2)
     * @return array of 2 int [row, col]
     */
    public int[] getMove(int player) {
        move = new int[2]; // move[0] = row; move[1] = col
        move[0] = -1;
        move[1] = -1;
        boolean wrongUserInput = true;
        Scanner scan = new Scanner(System.in);

        while (wrongUserInput) {
            System.out.printf("Player %d choice: ", player);
            String userInput = scan.nextLine().toUpperCase();

            if (userInput.equals("QUIT")) {
                userQuit = true;
                break;
            }

            wrongUserInput = false;

            //get valid row
            try {
                char rowChar = userInput.charAt(0);
                if (rowChar >= 'A' && rowChar <= 'Z') {
                    move[0] = rowChar - 'A';
                } else {
                    wrongUserInput = true;
                }
            } catch (Exception e) {
                wrongUserInput = true;
            }

            //get valid col
            try {
                move[1] = Integer.parseInt(userInput.substring(1)) - 1;
            } catch (Exception e) {
                wrongUserInput = true;
            }

            //check if move on board
            if (move[0] < 0 || move[0] > nRows - 1 || move[1] < 0 || move[1] > nCols - 1) {
                wrongUserInput = true;
            }

            //check if position free
            if (!wrongUserInput && board[move[0]][move[1]] != 0) {
                wrongUserInput = true;
            }
            if (wrongUserInput) {
                System.out.println("Wrong coordinates! Please try again!");
            }
        }

        return move;
    }

    public int[] getAiMove(int player) {
        return null;
    }

    /**
     * Writes the value of player (a 1 or 2) into the row & col element of the board
     *
     * @param player number of player (1 or 2)
     * @param row    line number
     * @param col    column number
     */
    public void mark(int player, int row, int col) {
        if (row >= 0 && row < nRows && col >= 0 && col < nCols && board[row][col] == 0) {
            board[row][col] = player;
            moveCounter++;
        }
    }

    /**
     * Returns true if player (of value 1 or 2) has howMany marks in a row on the board
     *
     * @param player  (X or O)
     * @param howMany default is 5
     * @return boolean
     */
    public boolean hasWon(int player, int howMany) {
        int row = move[0];
        int col = move[1];

        int winCounter = 1;

        // check victory for rows
        for (int i = col + 1; i < nCols; i++) {
            if (board[row][i] == player) {
                winCounter++;
            }
        }
        for (int i = col - 1; i >= 0; i--) {
            if (board[row][i] == player) {
                winCounter++;
            }
        }
        if (winCounter >= howMany) {
            return true;
        }

        // check victory for cols
        winCounter = 1;
        for (int i = row + 1; i < nRows; i++) {
            if (board[i][col] == player) {
                winCounter++;
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == player) {
                winCounter++;
            }
        }
        if (winCounter >= howMany) {
            return true;
        }

        // check victory for main diagonal
        winCounter = 1;
        for (int i = row + 1, j = col + 1; i < nRows && j < nCols; i++, j++) {
            if (board[i][j] == player) {
                winCounter++;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == player) {
                winCounter++;
            }
        }
        if (winCounter >= howMany) {
            return true;
        }

        // check victory for secondary diagonal
        winCounter = 1;
        for (int i = row + 1, j = col - 1; i < nRows && j >= 0; i++, j--) {
            if (board[i][j] == player) {
                winCounter++;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < nCols; i--, j++) {
            if (board[i][j] == player) {
                winCounter++;
            }
        }
        if (winCounter >= howMany) {
            return true;
        }

        return false;
    }

    /**
     * Returns true if the board is full.
     *
     * @return boolean
     */
    public boolean isFull() {
        return moveCounter == nRows * nCols;
    }

    /**
     * Prints the board on the screen
     */
    public void printBoard() {
        // clears old screen
        this.terminal.clearScreen();
        this.terminal.moveTo(0, 0);

        // prints cols numbers
        for (int col = 0; col < this.nCols; ++col) {
            System.out.printf("%3d", col + 1);
        }

        // prints table
        for (int row = 0; row < this.nRows; ++row) {
            System.out.printf("\n%c", 'A' + row);

            for (int col = 0; col < this.nCols; ++col) {
                if (this.board[row][col] == 0) {
                    System.out.print(" . ");
                } else if (this.board[row][col] == 1) {
                    terminal.setColor(Color.BLUE);
                    System.out.print(" X ");
                    terminal.resetStyle();
                } else {
                    terminal.setColor(Color.YELLOW);
                    System.out.print(" O ");
                    terminal.resetStyle();
                }
            }
        }

        System.out.println("\n");
    }

    /**
     * Displays the result of the game.
     *
     * @param player winner
     */
    public void printResult(int player) {
        if (!userQuit) {
            if (isFull()) {
                System.out.println("It's a tie!");
            } else if (player == 1) {
                System.out.println("X won!");
            } else {
                System.out.println("O won!");
            }
        }
    }

    public void enableAi(int player) {
    }

    /**
     * Runs a whole 2-players game. Parameter howMany sets the win condition of the game
     *
     * @param howMany elements in a winning line
     */
    public void play(int howMany) {
        int winner = 0;

        // game loop
        while (!isFull()) {
            printBoard();

            // get player next move
            move = getMove(player);
            if (userQuit) {
                break;
            }
            mark(player, move[0], move[1]);

            // check winning
            if (hasWon(player, howMany)) {
                printBoard();
                winner = player;

                printResult(winner);

                Scanner scan = new Scanner(System.in);
                System.out.print("Press any key to return to main menu: ");
                boolean userInput = scan.hasNext();
                if (userInput) {
                    break;
                }
            }

            // change player
            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }
        }


    }
}
