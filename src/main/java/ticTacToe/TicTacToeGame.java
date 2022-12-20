package tictactoe;

/**
 * This class is responsible for game mechanics
 * @author Ankush Madharha
 */

public class TicTacToeGame {
    private int turnCounter;

    /**
     * constructs a tic tac toe game
     */
    public TicTacToeGame() {
        turnCounter = 0;
    }

    /**
     * sets the turn counter to a specified value
     * @param val the value of the turn counter
     */
    public void setTurnCounter(int val) {
        this.turnCounter = val;
    }

    /**
     * gets the current value of the turn counter
     * @return the value of the turn counter
     */
    public int getTurnCounter() {
        return this.turnCounter;
    }

    /**
     * increments the turn counter by 1
     */
    public void incrementTurnCounter() {
        this.turnCounter++;
    }

    /**
     * gets the current player turn 
     * @return the current player
     */
    public String getCurrentPlayerTurn() {
        if(this.turnCounter % 2 == 0) {
            return "X";
        }
        return "O";
    }

    /**
     * gets the current player turn as a character
     * @return the current player
     */
    public char getCurrentPlayerTurnAsChar() {
        if(this.turnCounter % 2 == 0) {
            return 'X';
        }
        return 'O';
    }
}
