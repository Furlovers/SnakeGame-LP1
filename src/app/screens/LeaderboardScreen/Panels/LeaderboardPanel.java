package app.screens.LeaderboardScreen.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.screens.db.ConnFactory;
import app.screens.db.LevelManager;
import app.screens.db.User;

public class LeaderboardPanel extends JPanel{
    private ArrayList<String> UserNames;
    private ArrayList<Integer> UserHighScores;

    public LeaderboardPanel(){
        
    }
}
