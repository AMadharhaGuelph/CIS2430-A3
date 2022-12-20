package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import exceptions.InvalidSaveFileException;
import game.GameUI;

/**
 * The TicTacToeView class is responsible for the display of the tictactoe frame and components.
 * It creates a frame with buttons and the grid.
 * @author Ankush Madharha
 */
public class TicTacToeView extends JFrame {
    private final int width = 400;
    private final int height = 650;
    private TicTacToeGrid grid;

    /**
     * Constructs a tictactoe view
     */
    public TicTacToeView() {
        grid = new TicTacToeGrid(this);

        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        
        addIcon();
        addGrid();
        addBackButton();
        addResetButton();
        addSaveButton();
        addLoadButton();

        this.setVisible(true);
    }

    /**
     * adds an icon to the frame
     */
    private void addIcon() {
        ImageIcon image = new ImageIcon("images//icon.png");
        this.setIconImage(image.getImage());
    }

    /**
     * adds the grid to the frame
     */
    private void addGrid() {
        this.add(grid);
    }

    /**
     * adds a back button to the frame
     */
    private void addBackButton() {
        JButton button = new JButton();

        button.setBounds(50,400,140,90);
        button.setText("Back");
        button.addActionListener(e -> {
            this.dispose();
            new GameUI();
        });
        setEffects(button);

        this.add(button);
    }

    /**
     * adds a reset button to the frame
     */
    private void addResetButton() {
        JButton button = new JButton();

        button.setBounds(210,400,140,90);
        button.setText("Reset");
        button.addActionListener(e -> {
            grid.clearBoard();
        });
        setEffects(button);

        this.add(button);
    }

    /**
     * adds a save button to the frame
     */
    private void addSaveButton() {
        JButton button = new JButton();

        button.setBounds(50,510,140,90);
        button.setText("Save");
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(".//assets//TTT"));

            int response = fileChooser.showSaveDialog(null);  

            if(response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                grid.saveBoardToFile(file);
            }
        });
        setEffects(button);

        this.add(button);
    }

    /**
     * adds a load button to the frame
     */
    private void addLoadButton() {
        JButton button = new JButton();

        button.setBounds(210,510,140,90);
        button.setText("Load");
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(".//assets//TTT"));

            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    grid.loadBoardFromFile(file, this);
                } catch(InvalidSaveFileException isfe) {
                    JOptionPane.showMessageDialog(null, 
                    "This save file is incorrectly formatted or has some type of error!");
                }
                
            }
        }); 
        setEffects(button);

        this.add(button);
    }

    /**
     * sets effects to a specified button
     * @param button the button the effects are applied to
     */
    private void setEffects(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.ORANGE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }
        });
        button.setFocusable(false);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }
}
