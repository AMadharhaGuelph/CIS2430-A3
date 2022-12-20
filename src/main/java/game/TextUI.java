package game;

import java.util.Scanner;

import tictactoe.TicTacToeGame;
import tictactoe.TicTacToeGrid;

/**
 * This class is responsible for the UI for tic tac toe in text
 * @author Ankush Madharha
 */

public class TextUI {

    private static Scanner sc = new Scanner(System.in);
    
    /**
     * the main method in which the main game is ran
     * @param args command line args
     */
    public static void main(String[] args) {
        TicTacToeGrid grid = new TicTacToeGrid();
        TicTacToeGame game = new TicTacToeGame();
        int position;

        while(true) {
            grid.displayBoard();

            System.out.println("\nTurn: " + game.getCurrentPlayerTurn());

            System.out.println("Enter Position between 0 and 8:");
            do {
                position = getValidInput();
            } while(position == -1);
            grid.setTile(game.getCurrentPlayerTurnAsChar(), position);
            if(grid.checkBoardForWin(game.getCurrentPlayerTurnAsChar())) {
                grid.displayBoard();
                System.out.println("\nThe winner is " + game.getCurrentPlayerTurnAsChar() + "!");
                break;
            } else if(grid.isFull()) {
                grid.displayBoard();
                System.out.println("\nThe Game was a Tie!");
                break;
            }

            game.incrementTurnCounter();
        }
        sc.close();
    }
    
    /**
     * gets a valid input for the position
     * @return
     */
    private static int getValidInput() {
        int position = 0;

        try{
            position = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.err.println("Enter a valid INTEGER (0 - 8)!");
            return -1;
        }

        if(position < 0 || position > 8) {
            System.err.println("Out of Bounds (0 - 8)!");
            return -1;
        }

        return position;
    }

    
}
