package app.screens.LeaderboardScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import app.db.ConnFactory;
import app.db.User;

public class LeaderboardFrame extends JFrame implements ActionListener{

    private Connection conn = null;
    private JButton menuBtn;
    private JFrame container;
    private JTable leaderboardTable;
    private DefaultTableModel tableModel;

    public LeaderboardFrame() {
        super("Snake Game");

        // leaderboard container
        container = new JFrame();
        container.setLayout(null);
        container.setSize(500,550);
        container.setLocationRelativeTo(null);
        container.setResizable(false);
        container.getContentPane().setBackground(Color.BLACK);

        // leaderboard title
        JLabel title = new JLabel("Leaderboard");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.GREEN); 
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0, 10, 500, 50);
        container.add(title);

        // leaderboard table header and model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Rank");
        tableModel.addColumn("Name");
        tableModel.addColumn("HighScore");

        // leaderboard table
        leaderboardTable = new JTable(tableModel);
        leaderboardTable.setRowHeight(30);
        leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 20));
        leaderboardTable.setBackground(Color.BLACK);
        leaderboardTable.setForeground(Color.GREEN);
        leaderboardTable.setGridColor(Color.GREEN);
        leaderboardTable.getTableHeader().setBackground(Color.GREEN);
        leaderboardTable.setFillsViewportHeight(true);

        // center aligns the table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < leaderboardTable.getColumnCount(); i++) {
            leaderboardTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // table panel
        JScrollPane tablePanel = new JScrollPane(leaderboardTable);
        tablePanel.setBackground(Color.BLACK);
        tablePanel.setBounds(50, 70, 400, 325);

        // adds the players to the table
        try {
            ConnFactory bd = new ConnFactory();
            conn = bd.getConnection();

            int rank = 1;
            for (User user : User.getTopTenUsers(conn)) {
                tableModel.addRow(new Object[] {rank, user.getName(), user.getHighScore()});
                rank++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // back to menu button
        menuBtn = new JButton("Menu");
        menuBtn.setBackground(Color.GREEN);
        menuBtn.setPreferredSize(new Dimension(200, 50));
        menuBtn.addActionListener(this);
        menuBtn.setBorderPainted(false);
        menuBtn.setFocusPainted(false);
        menuBtn.setFont(new Font("Arial", Font.BOLD, 25));

        JPanel menuButton = new JPanel();
        menuButton.add(menuBtn);
        menuButton.setBackground(Color.BLACK);
        menuButton.setBounds(150, 420, 200, 50);

        container.add(menuButton);

        container.add(tablePanel);

        container.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuBtn) {
            container.dispose();
        } 
    }
}



