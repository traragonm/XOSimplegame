/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Game.Gameplay;
import javax.swing.JFrame;

/**
 *
 * @author minh
 */
public class Start {
    public static void main(String[] args) {
        Gameplay game = new Gameplay("tic toc toe 20x20");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);
        game.pack();
        game.setLocationRelativeTo(null);
        game.setVisible(true);
                
    }
}
