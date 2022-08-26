package br.banco.dao;

import br.banco.model.Cliente;
import br.banco.model.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerenteDao {
    Connection conn = new Conexao().conectarDAO();
    PreparedStatement pstm;

    public void cadastrarGerente(String nome) throws SQLException {
        String comandoSQL = "insert into gerente(nome) values (?)";
        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setString(1, nome);
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GerenteDao " + e);
        }
         pstm.close();
    }

    public ArrayList<Gerente> selectAll() throws SQLException {
        String sql = "select * from gerente";
        try {
            pstm = conn.prepareStatement(sql);
            ArrayList<Gerente> listaGerente = pesquisa(pstm);
            return listaGerente;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GerenteDao " + e);
        }
        pstm.close();
        conn.close();
        return null;
    }

    public Gerente selectForId(int idd) throws SQLException {
        String sql = "select * from gerente WHERE idgerente = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idd);
            pstm.execute();
            ResultSet resultSet = pstm.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("idgerente");
                String nome = resultSet.getString("nome");
                Gerente gerente = new Gerente(id, nome);
                return gerente;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GerenteDao " + e);
        }
        return null;
    }
    public Gerente selectForName (String nome) throws SQLException {
        String sql = "select * from gerente WHERE nome = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome);
            pstm.execute();
            ResultSet resultSet = pstm.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("idgerente");
                String nomee = resultSet.getString("nome");
                Gerente gerente = new Gerente(id, nomee);
                return gerente;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GerenteDao " + e);
        }
        return null;
    }

    private ArrayList<Gerente> pesquisa(PreparedStatement pstm) throws SQLException {
        ArrayList<Gerente> listaGerente = new ArrayList<>();
        pstm.execute();
        ResultSet resultSet = pstm.getResultSet();
        while (resultSet.next()) {
            int id = resultSet.getInt("idgerente");
            String nome = resultSet.getString("nome");
            Gerente gerente = new Gerente(id, nome);
            listaGerente.add(gerente);
        }
        return listaGerente;
    }

    public void update(Gerente gerente) throws SQLException {
        String sql = "update gerente set nome = ? where idgerente = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, gerente.getNome());
            pstm.setInt(2, gerente.getId());
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GerenteDao " + e);
        }
        pstm.close();
        conn.close();
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from gerente where idgerente = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "GerenteDao " + e);
        }
        pstm.close();
        conn.close();
    }
}
