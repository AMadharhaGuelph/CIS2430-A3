package numtictactoe;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import exceptions.InvalidSaveFileException;
import game.GameUI;

/**
 * This class is responsible for the numerical tic tac toe view.
 * @author Ankush Madharha
 */

public class NumTicTacToeView extends JFrame {
    private final int width = 400;
    private final int height = 850;
    private NumTicTacToeGrid grid;
    private JComboBox<String> comboBox;

    /**
     * constructs a new numerical tic tac toe view
     */
    public NumTicTacToeView() {
        comboBox = new JComboBox<>();
        grid = new NumTicTacToeGrid(this, comboBox);

        this.setTitle("Numerical Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        
        addIcon();
        addGrid();
        addOptionLabel();
        addBackButton();
        addResetButton();
        addSaveButton();
        addLoadButton();
        addOptionList();

        this.setVisible(true);
    }

    /**
     * adds a icon to the frame
     */
    private void addIcon() {
        ImageIcon image = new ImageIcon("images//icon.png");
        this.setIconImage(image.getImage());
    }

    /**
     * adds a grid to the frame
     */
    private void addGrid() {
        this.add(grid);
    }

    /**
     * adds a back button to the frame
     */
    private void addBackButton() {
        JButton button = new JButton();

        button.setBounds(50,600,140,90);
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

        button.setBounds(210,600,140,90);
        button.setText("Reset");
        button.addActionListener(e -> {
            grid.clearBoard(comboBox);
        });
        setEffects(button);

        this.add(button);
    }

    /**
     * adds a save button to the frame
     */
    private void addSaveButton() {
        JButton button = new JButton();

        button.setBounds(50,700,140,90);
        button.setText("Save");
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(".//assets//NTTT"));

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

        button.setBounds(210,700,140,90);
        button.setText("Load");
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(".//assets//NTTT"));

            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    grid.loadBoardFromFile(file, this, comboBox);
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
     * sets a specified button's effects
     * @param button the button the effects are being applied to
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

    /**
     * adds a combo box to the view
     */
    private void addOptionList() {
        comboBox.setBounds(50,400,300,50);
        comboBox.addItem("1");
        comboBox.addItem("3");
        comboBox.addItem("5");
        comboBox.addItem("7");
        comboBox.addItem("9");
        this.add(comboBox);
    }

    /**
     * adds the option label to the view
     */
    private void addOptionLabel() {
        JLabel label = new JLabel();
        label.setText("Choose your number:");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBounds(100, 350, 300,50);
        this.add(label);
    }
}
