package br.banco.dao;

import br.banco.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDao {

    Connection conn = new Conexao().conectarDAO();
    PreparedStatement pstm;

    public void cadastrarCliente(Cliente cli) throws SQLException {
        String comandoSQL = "insert into cliente(nome,cpf) values (?,?)";
        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setString(1, cli.getNome());
            pstm.setInt(2, cli.getCpf());
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ClienteDao " + e);
        }
        pstm.close();
    }
    
    public ArrayList<Cliente> selectAll() throws SQLException {
        String sql = "select * from cliente";
        try {
            pstm = conn.prepareStatement(sql);
            ArrayList<Cliente> listaCliente = pesquisa(pstm);
            return listaCliente;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ClienteDao " + e);
        }
        pstm.close();
        conn.close();
        return null;
    }

    public Cliente selectForId(int idd) throws SQLException {
        String sql = "select * from cliente WHERE cliente.idcliente = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idd);
            pstm.execute();
            ResultSet resultSet = pstm.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("idcliente");
                String nome = resultSet.getString("nome");
                int cpf = resultSet.getInt("cpf");
                Cliente cli = new Cliente(id, cpf, nome);
                return cli;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ClienteDao " + e);
        }
        pstm.close();
        conn.close();
        return null;
    }
    public Cliente selectForNomeCpf(String nomee, int cpff) throws SQLException {
        String sql = "select * from cliente WHERE cliente.nome = ? and cliente.cpf = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, nomee);
            pstm.setInt(2, cpff);
            pstm.execute();
            ResultSet resultSet = pstm.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("idcliente");
                String nome = resultSet.getString("nome");
                int cpf = resultSet.getInt("cpf");
                Cliente cli = new Cliente(id, cpf, nome);
                return cli;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ClienteDao " + e);
        }
        return null;
    }

    private ArrayList<Cliente> pesquisa(PreparedStatement pstm) throws SQLException {
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        pstm.execute();
        ResultSet resultSet = pstm.getResultSet();
        while (resultSet.next()) {
            int id = resultSet.getInt("idcliente");
            String nome = resultSet.getString("nome");
            int cpf = resultSet.getInt("cpf");
            Cliente cli = new Cliente(id, cpf, nome);
            listaCliente.add(cli);
        }
        return listaCliente;
    }

    public void update(Cliente cli) throws SQLException {
        String sql = "update cliente set nome = ?, cpf = ? where idcliente = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cli.getNome());
            pstm.setInt(2, cli.getCpf());
            pstm.setInt(3, cli.getIdCLiente());
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ClienteDao " + e);
        }
        pstm.close();
        conn.close();
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from cliente where idcliente = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ClienteDao " + e);
        }
        pstm.close();
        conn.close();
    }

}
