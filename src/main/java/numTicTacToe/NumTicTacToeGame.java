package numtictactoe;

/**
 * this class is reponsible for the numerical tic tac toe mechanics
 * @author Ankush Madharha
 */

public class NumTicTacToeGame {
    private int turnCounter;

    /**
     * constructs a numerical tic tac toe game
     */
    public NumTicTacToeGame() {
        turnCounter = 0;
    }

    /**
     * sets the turn counter to a specific value
     * @param val the value of the turn counter
     */
    public void setTurnCounter(int val) {
        this.turnCounter = val;
    }

    /**
     * get the current value of the turn counter
     * @return get the turn counter 
     */
    public int getTurnCounter() {
        return this.turnCounter;
    }

    /**
     * increment the turn counter by 1
     */
    public void incrementTurnCounter() {
        this.turnCounter++;
    }

    /**
     * get the current player turn 
     * @return the current player
     */
    public String getCurrentPlayerTurn() {
        if(this.turnCounter % 2 == 0) {
            return "O";
        }
        return "E";
    }

    /**
     * get the current player turn as a word
     * @return the current player turn 
     */
    public String getCurrentPlayerTurnAsWord() {
        if(this.turnCounter % 2 == 0) {
            return "Odd";
        }
        return "Even";
    }
}
