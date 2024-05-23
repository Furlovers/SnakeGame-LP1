package app.screens.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;

public class User {
    private String name;
    private int highScore;

    public User(String name, int highScore) {
        this.name = name;
        this.highScore = highScore;
    }

    public String getName() {
        return name;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        if(highScore > this.highScore) {
            this.highScore = highScore;
        }
    }

    // creates a user in the database
    public void createUser(Connection conn) {
        String sqlInsert = "INSERT INTO USER (name, highScore) VALUES (?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, this.name);
            stmt.setInt(2, this.highScore);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // updates a user in the database with the new high score
    public void updateUser(Connection conn) {
        String sqlUpdate = "UPDATE USER SET highScore = ? WHERE name = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlUpdate);
            stmt.setInt(1, this.highScore);
            stmt.setString(2, this.name);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // reads a user from the database
    public void readUser(Connection conn) {
        String sqlSelect = "SELECT * FROM USER WHERE name = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, this.name);
            try (ResultSet rs = stmt.executeQuery();){
                if(rs.next()) {
                    this.setHighScore(rs.getInt(2));
                } else {
                    throw new Error();
                }
            }
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getMaxHighScore(Connection conn) {
        String sqlSelect = "SELECT MAX(highScore), NOME FROM USER";
        try (PreparedStatement stmt = conn.prepareStatement(sqlSelect);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.out.println(rs.getInt(1));
                return rs.getInt(1);
            } else {
                throw new RuntimeException("No high score found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

}
