/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author minh
 */
public class Gameplay extends JFrame implements ActionListener {

    public static final String LAN_GAME = "LAN";
    public static final String LOCAL_GAME = "LOCAL";
    public static final String AI_GAME = "AI";
    /**
     * Grid width of caro broad
     */
    public static final int GRID_WIDTH = 20;

    /**
     *
     * theme for button only
     */
    public static final Color buttonTheme = Color.white;
    private JPanel broad;
    private JPanel setting;
    private JMenuBar menu;
    private JButton[][] buttonGrid;
    private JMenu fileMenu;
    private JMenu optionMenu;
    private boolean isPlaying = false;
    private boolean currentPlayer = true;

    public Gameplay() throws HeadlessException {
    }

    public Gameplay(String title) throws HeadlessException {
        super(title);
        this.setLayout(new BorderLayout());
        initBroad();
        initSetting();
        initMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().length() == 1) {
            return;
        }
        String[] temp = e.getActionCommand().split(":");
        int x = Integer.parseInt(temp[0]);
        int y = Integer.parseInt(temp[1]);

        if (currentPlayer) {
            buttonGrid[x][y].setIcon(new ImageIcon("src\\Image\\cross.png"));
            buttonGrid[x][y].setActionCommand("X");
            currentPlayer = !currentPlayer;
            if (checkWin(x, y)) {
                int option = JOptionPane.showConfirmDialog(this,
                        "player X win the game, Do you want to continue playing",
                        "Game over!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("src\\Image\\cross_dialog.png"));
                if (option == 0) {

                } else {
                    isPlaying = false;
                    //broad.setEnabled(isPlaying);
                    setEnableBroad(isPlaying);
                }
                currentPlayer = true;
                clearBroad();
                
            }

        } else {
            buttonGrid[x][y].setIcon(new ImageIcon("src\\Image\\nought.png"));
            buttonGrid[x][y].setActionCommand("O");
            currentPlayer = !currentPlayer;
            if (checkWin(x, y)) {
                int option = JOptionPane.showConfirmDialog(this,
                        "player O win the game, Do you want to continue playing",
                        "Game over!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("src\\Image\\nought_dialog.png"));
                if (option == 0) {

                } else {
                    isPlaying = false;
                    //broad.setEnabled(isPlaying);
                    setEnableBroad(isPlaying);
                }
                currentPlayer = true;
                clearBroad();
            }
        }

    }

    private void initBroad() {
        broad = new JPanel(new GridLayout(GRID_WIDTH, GRID_WIDTH));
        broad.setPreferredSize(new Dimension(550, 550));
        buttonGrid = new JButton[GRID_WIDTH][GRID_WIDTH];
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                buttonGrid[i][j] = new JButton("");
                buttonGrid[i][j].setActionCommand(i + ":" + j);
                buttonGrid[i][j].setBackground(buttonTheme);
                buttonGrid[i][j].addActionListener(this);
                buttonGrid[i][j].setEnabled(isPlaying);
                broad.add(buttonGrid[i][j]);
            }
        }
        this.add(broad, BorderLayout.CENTER);
        //broad.setEnabled(false);
    }

    private void clearBroad() {
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                buttonGrid[i][j].setIcon(null);
                buttonGrid[i][j].setActionCommand(i + ":" + j);
                buttonGrid[i][j].setBackground(buttonTheme);
            }
        }
    }

    private void setEnableBroad(boolean isEnable) {
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                buttonGrid[i][j].setEnabled(isEnable);
            }
        }
    }

    private void initSetting() {
    }

    private void initMenu() {
        fileMenu = new JMenu("File");
        JMenu fileMenuNewGame = new JMenu("New game");
        JMenuItem fileMenuNewGameLocal = new JMenuItem("Local");
        fileMenuNewGameLocal.setIcon(new ImageIcon("src\\Image\\sofa.png"));
        fileMenuNewGameLocal.setActionCommand(LOCAL_GAME);
        //fileMenuNewGameLocal.addActionListener(this);
        fileMenuNewGameLocal.addActionListener((ActionEvent e) -> {
            isPlaying=true;
            setEnableBroad(isPlaying);
        });
        JMenuItem fileMenuNewGameLAN = new JMenuItem("LAN");
        fileMenuNewGameLAN.setIcon(new ImageIcon("src\\Image\\lan.png"));
        fileMenuNewGameLAN.setActionCommand(LAN_GAME);
        //fileMenuNewGameLAN.addActionListener(this);
        fileMenuNewGameLAN.addActionListener((ActionEvent e) -> {
            
        });
        JMenuItem fileMenuNewGameAI = new JMenuItem("play with AI");
        fileMenuNewGameAI.setIcon(new ImageIcon("src\\Image\\AI.png"));
        fileMenuNewGameAI.setActionCommand(AI_GAME);
        //fileMenuNewGameAI.addActionListener(this);
        fileMenuNewGameAI.addActionListener((ActionEvent e) -> {
            isPlaying=true;
        });
        fileMenuNewGameAI.setEnabled(false);
        fileMenuNewGame.add(fileMenuNewGameLocal);
        fileMenuNewGame.add(fileMenuNewGameLAN);
        fileMenuNewGame.add(fileMenuNewGameAI);
        JMenuItem fileMenuExit = new JMenuItem("Exit");
        fileMenuExit.setActionCommand("EXIT");
        fileMenuExit.addActionListener(this);

        JMenuItem resetGameMenu = new JMenuItem("Reset game");
        resetGameMenu.setActionCommand("RESET");
        //resetGameMenu.addActionListener(this);
        resetGameMenu.addActionListener((ActionEvent e) -> {
            clearBroad();
            isPlaying=false;
            setEnableBroad(isPlaying);
        });
        resetGameMenu.setEnabled(!isPlaying);
        fileMenu.add(fileMenuNewGame);
        fileMenuExit.add(resetGameMenu);
        fileMenu.add(fileMenuExit);
        optionMenu = new JMenu("Options");
        optionMenu.setEnabled(false);
        menu = new JMenuBar();
        menu.add(fileMenu);
        menu.add(optionMenu);
        this.add(menu, BorderLayout.NORTH);
    }

    public boolean checkWin(int i, int j) {
        int d = 0, k = i, h;
        // kiểm tra hàng
        while (buttonGrid[k][j].getActionCommand().equals(buttonGrid[i][j].getActionCommand())) {
            d++;
            k++;
        }
        k = i - 1;
        while (buttonGrid[k][j].getActionCommand().equals(buttonGrid[i][j].getActionCommand())) {
            d++;
            k--;
        }
        if (d > 4) {
            return true;
        }
        d = 0;
        h = j;
        // kiểm tra cột
        while (buttonGrid[i][h].getActionCommand().equals(buttonGrid[i][j].getActionCommand())) {
            d++;
            h++;
        }
        h = j - 1;
        while (buttonGrid[i][h].getActionCommand().equals(buttonGrid[i][j].getActionCommand())) {
            d++;
            h--;
        }
        if (d > 4) {
            return true;
        }
        // kiểm tra đường chéo 1
        h = i;
        k = j;
        d = 0;
        while (buttonGrid[i][j].getActionCommand().equals(buttonGrid[h][k].getActionCommand())) {
            d++;
            h++;
            k++;
        }
        h = i - 1;
        k = j - 1;
        while (buttonGrid[i][j].getActionCommand().equals(buttonGrid[h][k].getActionCommand())) {
            d++;
            h--;
            k--;
        }
        if (d > 4) {
            return true;
        }
        // kiểm tra đường chéo 2
        h = i;
        k = j;
        d = 0;
        while (buttonGrid[i][j].getActionCommand().equals(buttonGrid[h][k].getActionCommand())) {
            d++;
            h++;
            k--;
        }
        h = i - 1;
        k = j + 1;
        while (buttonGrid[i][j].getActionCommand().equals(buttonGrid[h][k].getActionCommand())) {
            d++;
            h--;
            k++;
        }
        // nếu không đương chéo nào thỏa mãn thì trả về false.
        return d > 4;
    }
}
