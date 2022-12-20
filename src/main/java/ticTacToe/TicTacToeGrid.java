package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import exceptions.InvalidSaveFileException;
import game.GameUI;

/**
 * The TicTacToeGrid class is resonsible for creating a gui grid and a text grid.
 * @author Ankush Madharha
 */

public class TicTacToeGrid extends JLabel {

    private TicTacToeGame game;
    private JButton[][] buttonGrid;
    private char[][] displayBoard = {{' ','|',' ','|',' '},
                                     {'-','+','-','+','-'},
                                     {' ','|',' ','|',' '},
                                     {'-','+','-','+','-'},
                                     {' ','|',' ','|',' '}};
    private char[][] logicBoard = new char[3][3];

    /**
     * Constructs a gui grid 
     * @param frame the frame the grid is being added to
     */
    public TicTacToeGrid(JFrame frame) {
        game = new TicTacToeGame();
        buttonGrid = new JButton[3][3];

        this.setBounds(50,50,300,300);
        this.setLayout(new GridLayout(3,3));
        
        addButtons(frame);
    }

    /**
     * adds the buttons to the grid
     * @param frame the frame the grid is being added to
     */
    private void addButtons(JFrame frame) {
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                buttonGrid[y][x] = new JButton("");
                buttonGrid[y][x].addActionListener(e -> {
                    JButton clicked = ((JButton)(e.getSource()));
                    if(clicked.getText().equals("")) {  
                        clicked.setText(game.getCurrentPlayerTurn());
                        checkWinOrTie(frame);
                        game.incrementTurnCounter();
                    }  
                });
                setButtonProperties(buttonGrid[y][x]);
                this.add(buttonGrid[y][x]);
            }
        }
    }

    /**
     * checks the grid for a win or a tie condition
     * @param frame the frame the grid is on
     */
    private void checkWinOrTie(JFrame frame) {
        if(checkForWin(game.getCurrentPlayerTurn())) {
            displayWin(game.getCurrentPlayerTurn(), frame);
        } else if(checkForFullBoard()) {
            displayTie(frame);
        }
    }

    /**
     * display a win pane 
     * @param currentPlayerTurn The player who has won
     * @param frame The frame that the grid is on
     */
    private void displayWin(String currentPlayerTurn, JFrame frame) {
        int selection;
        selection = JOptionPane.showConfirmDialog(frame,
        currentPlayerTurn + " Wins! Would you like to play again?", 
        "Play Again?", 
        JOptionPane.YES_NO_OPTION);
        
        if(selection == JOptionPane.NO_OPTION) {
            frame.dispose();
            new GameUI();
        } else {
            frame.dispose();
            new TicTacToeView();
        }
    }

    /**
     * Display a tie pane
     * @param frame the frame the grid is on
     */
    private void displayTie(JFrame frame) {
        int selection;
        selection = JOptionPane.showConfirmDialog(frame,
        "Tie Game! Would you like to play again?", 
        "Play Again?", 
        JOptionPane.YES_NO_OPTION);
        
        if(selection == JOptionPane.NO_OPTION) {
            frame.dispose();
            new GameUI();
        } else {
            frame.dispose();
            new TicTacToeView();
        }
    }

    /**
     * Sets a specified buttons properties
     * @param positionAwareButton button properties are being applied to
     */
    private void setButtonProperties(JButton positionAwareButton) {
        positionAwareButton.setFocusable(false);
        setHoverEffect(positionAwareButton);
        positionAwareButton.setFont(new Font("Arial", Font.BOLD, 20));
        positionAwareButton.setBackground(Color.WHITE);
        positionAwareButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }

    /**
     * sets a hover effect on a specified button
     * @param button the button the hoever effect is being applied to
     */
    private void setHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });
    }

    /**
     * Checks the grid for a win
     * @param player checks this player for a win on the grid
     * @return true is there is a win, false otherwise
     */
    private boolean checkForWin(String player) {
        for(int i = 0; i < 3; i++) {
            if(buttonGrid[i][0].getText().equals(player)  
               && buttonGrid[i][1].getText().equals(player) 
               && buttonGrid[i][2].getText().equals(player)) {
                return true;
            }
            if(buttonGrid[0][i].getText().equals(player) 
               && buttonGrid[1][i].getText().equals(player) 
               && buttonGrid[2][i].getText().equals(player)) {
                return true;
            }
        }
        if(buttonGrid[0][0].getText().equals(player) 
           && buttonGrid[1][1].getText().equals(player) 
           && buttonGrid[2][2].getText().equals(player)) {
            return true;
        }
        if(buttonGrid[0][2].getText().equals(player) 
           && buttonGrid[1][1].getText().equals(player)
           && buttonGrid[2][0].getText().equals(player)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the grid is full
     * @return true if the board is full, false otherwise
     */
    private boolean checkForFullBoard() {
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                if(buttonGrid[r][c].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Clears the grid
     */
    public void clearBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttonGrid[i][j].setText("");
            }
        }
        game.setTurnCounter(0);
    }

    /**
     * saves the current state of the board to a file
     * @param file the file that the grid is being saved to
     */
    public void saveBoardToFile(File file) {
        BufferedWriter bw = null;

        try {
            file.createNewFile();
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(game.getCurrentPlayerTurn() + "\n");
            for(int r = 0; r < 3; r++) {
                for(int c = 0; c < 3; c++) {
                    if(c == 2) {
                        bw.write(buttonGrid[r][c].getText());
                    } else {
                        bw.write(buttonGrid[r][c].getText() + ",");
                    }
                }
                bw.write("\n");
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * loads a grid from a specified file
     * @param file the file that the grid is being loaded from
     * @param frame the frame that the grid is on
     */
    public void loadBoardFromFile(File file, JFrame frame) {
        int row = 0;
        clearBoard();
        BufferedReader br = null;
        try {
            String st;
            br = new BufferedReader(new FileReader(file));
            st = br.readLine();
            setTurnCounter(st);
            while((st = br.readLine()) != null) {
                putIntoRow(st,row);
                row++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        checkWinOrTie(frame);
    }

    /**
     * puts a row into the grid
     * @param st the row contents
     * @param row the row number
     */
    private void putIntoRow(String st, int row) {
        if(!st.isBlank()) {      
            try {
                loadRow(st, row);
            } catch(InvalidSaveFileException isfe) {
                JOptionPane.showMessageDialog(null, 
                "This save file is incorrectly formatted or has some type of error!");
                clearBoard();
            }
        }
    }

    /**
     * sets the turn counter based on a passed string
     * @param st the string that tht turn counter is based on
     */
    private void setTurnCounter(String st) {
        if(st.equals("X")) {
            game.setTurnCounter(0);
        } else if(st.equals("O")) {
            game.setTurnCounter(1);
        } else {
            throw new InvalidSaveFileException();
        }
    }

    /**
     * loads a row into the grid
     * @param st the row contents
     * @param row the row number
     */
    private void loadRow(String st, int row) {
        String[] rowElements = st.split(",");
        
        if(rowElements.length < 1 || rowElements.length > 3) {
            throw new InvalidSaveFileException();
        }

        if(row > 2) {
            throw new InvalidSaveFileException();
        }

        for(int col = 0; col < rowElements.length; col++) {
            if(rowElements[col].equals("X")) {
                buttonGrid[row][col].setText("X");
            } else if(rowElements[col].equals("O")) {
                buttonGrid[row][col].setText("O");
            } else if(rowElements[col].isBlank()) {
                buttonGrid[row][col].setText("");
            }
        }
    }

    /**
     * constructs a tictactoe text grid
     */
    public TicTacToeGrid() {
        for(int i = 0; i < 9; i++) {
            setTile(Character.forDigit(i,10), i);
        }
    }

    /**
     * sets a tile on the board to a specified character
     * @param tile sets the tile on the board to this tile
     * @param position the position to be set
     */
    public void setTile(char tile, int position) {
        checkBeginPosition(tile, position);
        checkEndPosition(tile, position);
        
    } 

    /**
     * set end positions
     * @param tile sets position to this tile
     * @param position sets tile to this position 
     */
    private void checkEndPosition(char tile, int position) {
        switch(position) {
            case 5:
                logicBoard[1][2] = tile;
                displayBoard[2][4] = tile;
                break;
            case 6:
                logicBoard[2][0] = tile;
                displayBoard[4][0] = tile;
                break;
            case 7:
                logicBoard[2][1] = tile;
                displayBoard[4][2] = tile;
                break;
            case 8:
                logicBoard[2][2] = tile;
                displayBoard[4][4] = tile;
                break;
            default:
        }  
    }

    /**
     * set begin positions
     * @param tile sets position to this tile
     * @param position sets tile to this position 
     */
    private void checkBeginPosition(char tile, int position) {
        switch(position) {
            case 0:
                logicBoard[0][0] = tile;
                displayBoard[0][0] = tile;
                break;
            case 1:
                logicBoard[0][1] = tile;
                displayBoard[0][2] = tile;
                break;
            case 2:
                logicBoard[0][2] = tile;
                displayBoard[0][4] = tile;
                break;
            case 3:
                logicBoard[1][0] = tile;
                displayBoard[2][0] = tile;
                break;
            case 4:
                logicBoard[1][1] = tile;
                displayBoard[2][2] = tile;
                break;
            default:
        }
    }

    /**
     * check text ui grid for win
     * @param tile checks for a win for this specific tile
     * @return true if board has a win, false otherwise
     */
    public boolean checkBoardForWin(char tile) {
        for(int i = 0; i < 3; i++) {
            if(logicBoard[i][0] == logicBoard[i][1] && logicBoard[i][1] == logicBoard[i][2]) {
                return true;
            }
            if(logicBoard[0][i] == logicBoard[1][i] && logicBoard[1][i] == logicBoard[2][i]) {
                return true;
            }
        }
        if(logicBoard[0][0] == logicBoard[1][1] && logicBoard[1][1] == logicBoard[2][2]) {
            return true;
        }
        if(logicBoard[0][2] == logicBoard[1][1] && logicBoard[1][1] == logicBoard[2][0]) {
            return true;
        }

        return false;
    }

    /**
     * displays the board
     */
    public void displayBoard() {
        for(int row = 0; row < displayBoard.length; row++) {
            for(int col = 0; col < displayBoard[row].length; col++) {
                System.out.print(displayBoard[row][col]);
            }
            System.out.print("\n");
        }
    }

    /**
     * checks if the board is full
     * @return true if the board is full, false otherwise
     */
    public boolean isFull() {
        for(int row = 0; row < logicBoard.length; row++) {
            for(int col = 0; col < logicBoard[row].length; col++) {
                if(logicBoard[row][col] != 'X' && logicBoard[row][col] != 'O') {
                    return false;
                } 
            }
        }
        return true;
    }

}
