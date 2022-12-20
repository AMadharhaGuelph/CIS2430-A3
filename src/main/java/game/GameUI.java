package game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import numtictactoe.NumTicTacToeView;
import tictactoe.TicTacToeView;

/**
 * The gui ui for num tic tac toe and normal tic tac toe
 * @author Ankush Madharha
 */

public class GameUI extends JFrame {
    private final int width = 400;
    private final int height = 650;

    /**
     * The main method in which the game ui is created
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new GameUI();
    }

    /**
     * constructs a new game ui
     */
    public GameUI() {
        this.setTitle("Tic Tac Toe Games!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);

        addIcon();
        addLogo();
        addTicTacToeButton();
        addNumTicTacToeButton();

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
     * adds a logo to the frame
     */
    private void addLogo() {
        ImageIcon image = new ImageIcon("images//logo.png");
        JLabel label = new JLabel();

        label.setIcon(image);
        label.setText("test");
        label.setBounds(0,20,400,200);

        this.add(label);
    }

    /**
     * adds a tic tac toe button to the frame
     */
    private void addTicTacToeButton() {
        JButton button = new JButton();

        button.setBounds(100,300,200,85);
        button.addActionListener(e -> ticTacToe());
        button.setText("Tic Tac Toe");
        button.setFocusable(false);
        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        setHoverEffect(button);

        this.add(button);
    }

    /**
     * adds a numerical tic tac toe button to the frame
     */
    private void addNumTicTacToeButton() {
        JButton button = new JButton();

        button.setBounds(100,425,200,85);
        button.addActionListener(e -> numTicTacToe());
        button.setText("<html>Numerical<br>Tic Tac Toe</html>");
        button.setFocusable(false);
        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        setHoverEffect(button);

        this.add(button);
    }

    /**
     * adds a hover effect to a specified button
     * @param button the button the hover is being applied to
     */
    private void setHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.ORANGE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }
        });
    }

    /**
     * removes current frame and creates tic tac toe view
     */
    protected void ticTacToe() {
        this.dispose();
        new TicTacToeView();
    }

    /**
     * reomves current frame and creates num tic tac toe view
     */
    protected void numTicTacToe() {
        this.dispose();
        new NumTicTacToeView();
    }
}
