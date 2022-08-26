package br.banco.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
    public Connection conectarDAO(){
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/banco?user=root&password=123";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return conn;
    }
}
