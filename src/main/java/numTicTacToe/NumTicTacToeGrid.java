package numtictactoe;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import exceptions.InvalidSaveFileException;
import game.GameUI;

/**
 * This class is responsible for the num tic tac toe grid
 * @author Ankush Madharha
 */

public class NumTicTacToeGrid extends JLabel {

    private NumTicTacToeGame game;
    private JButton[][] buttonGrid;

    /**
     * constructs a num tic tac toe grid
     * @param frame the frame that the grid is on
     * @param cbox the combo box on the frame
     */
    public NumTicTacToeGrid(JFrame frame, JComboBox<String> cbox) {
        game = new NumTicTacToeGame();
        buttonGrid = new JButton[3][3];

        this.setBounds(50,50,300,300);
        this.setLayout(new GridLayout(3,3));
        
        addButtons(frame, cbox);
    }

    /**
     * add the buttons to the grid
     * @param frame the frame that the grid is on
     * @param cbox the combobox on the frame 
     */
    private void addButtons(JFrame frame, JComboBox<String> cbox) {
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                buttonGrid[y][x] = new JButton("");
                buttonGrid[y][x].addActionListener(e -> {
                    JButton clicked = ((JButton)(e.getSource()));
                    if(clicked.getText().equals("")) {  
                        clicked.setText(cbox.getSelectedItem().toString());
                        checkWinOrTie(frame, cbox);
                        game.incrementTurnCounter();
                        changeOptions(cbox);
                    }  
                });
                setButtonProperties(buttonGrid[y][x]);
                this.add(buttonGrid[y][x]);
            }
        }
    }

    /**
     * check the grid for a win or tie
     * @param frame the frame that the grid is on
     * @param cbox the combobox on the frame
     */
    private void checkWinOrTie(JFrame frame, JComboBox<String> cbox) {
        if(checkForWin()) {
            displayWin(game.getCurrentPlayerTurnAsWord(), frame, cbox);
        } else if(checkForFullBoard()) {
            displayTie(game.getCurrentPlayerTurnAsWord(), frame, cbox);
        }
    }

    /**
     * changed the combo box options 
     * @param cbox the combobox on the frame
     */
    private void changeOptions(JComboBox<String> cbox) {
        if(game.getTurnCounter()%2 == 0) {
            fillOdd(cbox);
            for(int i = 1; i < 10; i += 2) {
                for(int r = 0; r < 3; r++) {
                    for(int c = 0; c < 3; c++) {
                        if(buttonGrid[r][c].getText().
                        equals(Integer.toString(i))) {
                            cbox.removeItem(Integer.toString(i));
                        }
                    }
                }
            }
        } else {
            fillEven(cbox);
            for(int i = 0; i < 10; i += 2) {
                for(int r = 0; r < 3; r++) {
                    for(int c = 0; c < 3; c++) {
                        if(buttonGrid[r][c].getText().equals(Integer.toString(i))) {
                            cbox.removeItem(Integer.toString(i));
                        }
                    }
                }
            }
        }

    }

    /**
     * fills the combobox with odds
     * @param cbox combobox on the frame
     */
    private void fillOdd(JComboBox<String> cbox) {
        cbox.removeAllItems();
        cbox.addItem("1");
        cbox.addItem("3");
        cbox.addItem("5");
        cbox.addItem("7");
        cbox.addItem("9");
    }

    /**
     * fills the combobox with evens
     * @param cbox combobox on the frame
     */
    private void fillEven(JComboBox<String> cbox) {
        cbox.removeAllItems();
        cbox.addItem("0");
        cbox.addItem("2");
        cbox.addItem("4");
        cbox.addItem("6");
        cbox.addItem("8");
    }

    /**
     * displays win on a pane
     * @param currentPlayerTurn player who won
     * @param frame frame that grid is on
     * @param cbox combobox on frame
     */
    private void displayWin(String currentPlayerTurn, JFrame frame, JComboBox<String> cbox) {
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
            new NumTicTacToeView();
        }
    }

    /**
     * displays tie on a pane
     * @param currentPlayerTurn current player
     * @param frame frame that the grid is on
     * @param cbox combobox on the frame
     */
    private void displayTie(String currentPlayerTurn, JFrame frame, JComboBox<String> cbox) {
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
            new NumTicTacToeView();
        }
    }

    /**
     * sets a specified buttons properties 
     * @param positionAwareButton the button the properties are being applied to
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
     * @param button the button that the hover effect is being applied to
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
     * checks the board for a win
     * @return true if there is a win, false otherwise
     */
    private boolean checkForWin() {
        if(checkRowWin()) {
            return true;
        }
        if(checkColWin()) {
            return true;
        }
        if(checkFirstDiagWin()) {
            return true;
        }
        if(checkSecondDiagWin()) {
            return true;
        }

        return false;
    }

    /**
     * checks the rows for win
     * @return returns true of there is a win within the row, false otherwise
     */
    private boolean checkRowWin() {
        for(int i = 0; i < 3; i++) {
            if(buttonGrid[i][0].getText().equals("") 
               || buttonGrid[i][1].getText().equals("")
               || buttonGrid[i][2].getText().equals("")) {
                continue;
            }
            if(Integer.parseInt(buttonGrid[i][0].getText()) 
               + Integer.parseInt(buttonGrid[i][1].getText())
               + Integer.parseInt(buttonGrid[i][2].getText()) == 15) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks the columns for a win
     * @return true if there is a win within the columns, false otherwise
     */
    private boolean checkColWin() {
        for(int i = 0; i < 3; i++) {
            if(buttonGrid[0][i].getText().equals("")  
               || buttonGrid[1][i].getText().equals("") 
               || buttonGrid[2][i].getText().equals("")) {
                continue;
            }
            if(Integer.parseInt(buttonGrid[0][i].getText())  
               + Integer.parseInt(buttonGrid[1][i].getText()) 
               + Integer.parseInt(buttonGrid[2][i].getText()) == 15) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks on diagonal for a win
     * @return true if there is a win on this diagonal, false otherwise
     */
    private boolean checkFirstDiagWin() {
        if(buttonGrid[0][0].getText().equals("")  
           || buttonGrid[1][1].getText().equals("") 
           || buttonGrid[2][2].getText().equals("")) {
            return false;
        }
        if(Integer.parseInt(buttonGrid[0][0].getText()) 
           + Integer.parseInt(buttonGrid[1][1].getText()) 
           + Integer.parseInt(buttonGrid[2][2].getText()) == 15) {
            return true;
        }
        return false;
    }

    /**
     * checks a diagonal for a win
     * @return true if there is a win on this diagonal, false otherwise
     */
    private boolean checkSecondDiagWin() {
        if(buttonGrid[0][2].getText().equals("") 
           || buttonGrid[1][1].getText().equals("") 
           || buttonGrid[2][0].getText().equals("")) {
            return false;
        }
        if(Integer.parseInt(buttonGrid[0][2].getText()) 
           + Integer.parseInt(buttonGrid[1][1].getText()) 
           + Integer.parseInt(buttonGrid[2][0].getText()) == 15) {
            return true;
        }
        return false;
    }

    /**
     * checks if the board is full
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
     * clears the board
     * @param cbox the combobox on the frame
     */
    public void clearBoard(JComboBox<String> cbox) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttonGrid[i][j].setText("");
            }
        }
        game.setTurnCounter(0);
        changeOptions(cbox);
    }

    /**
     * saves the board to a file
     * @param file the file the board is being saved to
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
     * loads a board from a file
     * @param file the file being loaded from
     * @param frame the frame that the grid is on
     * @param cbox the combobox on the frame
     */
    public void loadBoardFromFile(File file, JFrame frame, JComboBox<String> cbox) {
        int row = 0;
        clearBoard(cbox);
        BufferedReader br = null;
        try {
            String st;
            br = new BufferedReader(new FileReader(file));
            st = br.readLine();
            setTurnCounter(st, cbox);
            while((st = br.readLine()) != null) {
                putIntoRow(st, row, cbox);
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
        checkWinOrTie(frame, cbox);
    }

    /**
     * puts a string (row) into the board
     * @param st the row content
     * @param row the row number
     * @param cbox the combobox on the frame
     */
    private void putIntoRow(String st, int row, JComboBox<String> cbox) {
        if(!st.isBlank()) {
            try {
                loadRow(st, row);
            } catch(InvalidSaveFileException isfe) {
                JOptionPane.showMessageDialog(null,
                 "This save file is incorrectly formatted or has some type of error!");
                clearBoard(cbox);
            }
        }
    }

    /**
     * set the turn counter to a value based on a string
     * @param st the string that the turn counter is based off
     * @param cbox the combobox on the frame
     */
    private void setTurnCounter(String st, JComboBox<String> cbox) {
        if(st.contains("O")) {
            game.setTurnCounter(0);
            fillOdd(cbox);
        } else if(st.contains("E")) {
            game.setTurnCounter(1);
            fillEven(cbox);
        } else {
            throw new InvalidSaveFileException();
        }
    }

    /**
     * loads a row into the grid
     * @param st the rows content 
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
            if(rowElements[col].matches("[0-9]")) {
                buttonGrid[row][col].setText(rowElements[col]);
            } else if(rowElements[col].isBlank()) {
                buttonGrid[row][col].setText("");
            }
        }
    }
}
