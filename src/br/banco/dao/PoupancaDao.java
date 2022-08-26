package br.banco.dao;

import br.banco.model.Cliente;
import br.banco.model.ContaPoupanca;
import br.banco.model.Movimentacao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PoupancaDao {

    Connection conn = new Conexao().conectarDAO();
    PreparedStatement pstm;

    public void cadastrarContaPoupanca(ContaPoupanca conta) throws SQLException {
        String comandoSQL = "insert into conta_poupanca(idgerente, idcliente, numero, dtrendimento) values (?,?,?,?)";
        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setInt(1, conta.getIdGerente());
            pstm.setInt(2, conta.getIdCliente());
            pstm.setInt(3, conta.getNumero());
            pstm.setDate(4, (Date) conta.getDataRendimento());
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "PoupancaDao " + e);
        }
        pstm.close();
    }
    public void updateSaldo(double valor, int id) throws SQLException {
        String sql = "update conta_poupanca set saldo = ? where idcliente = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setDouble(1, valor);
            pstm.setInt(2, id);
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "PoupancaDao " + e);
        }
        pstm.close();
        conn.close();
    }
    
    public void updateStatus(boolean ativo, int numero) throws SQLException {
        String sql = "update conta_poupanca set ativo = ? where numero = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setBoolean(1, ativo);
            pstm.setInt(2, numero);
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "PoupancaDao " + e);
        }
    }
    
    public void transferencia(double valor, int num) throws SQLException {
        String sql = "update conta_poupanca set saldo = ? where numero = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setDouble(1, valor);
            pstm.setInt(2, num);
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "PoupancaDao " + e);
        }
        pstm.close();
        conn.close();
    }
    
    public ArrayList<Movimentacao> selectAllExtrato(int idConta) throws SQLException {
        String sql = "select * from movimentacao where idconta = ? ";
        ArrayList<Movimentacao> listaExtrato = new ArrayList<>();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idConta);
            pstm.execute();
            ResultSet resultSet = pstm.getResultSet();
            while (resultSet.next()) {
                int idmovimentacao = resultSet.getInt("idmovimentacao");
                int idconta = resultSet.getInt("idconta");
                Date datamovimentacao = resultSet.getDate("datamovimentacao");
                double valor = resultSet.getDouble("valor");
                char tipoconta = resultSet.getString("tipoconta").charAt(0);
                char tipotransacao = resultSet.getString("tipotransacao").charAt(0);
                Movimentacao mov = new Movimentacao(idmovimentacao, idconta, datamovimentacao,valor,tipoconta, tipotransacao);
                listaExtrato.add(mov);
            }
            return listaExtrato;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "PoupancaDao " + e);
        }
        return null;
    }
    
    public ArrayList<Cliente> selectAll() throws SQLException {
        String sql = "select * from cliente";
        try {
            pstm = conn.prepareStatement(sql);
            ArrayList<Cliente> listaCliente = pesquisa(pstm);
            return listaCliente;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "PoupancaDao " + e);
        }
        pstm.close();
        conn.close();
        return null;
    }
    
    public ContaPoupanca selectForNumero(int num) throws SQLException {
        String sql = "select * from banco.conta_poupanca WHERE conta_poupanca.numero = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, num);
            pstm.execute();
            ResultSet resultSet = pstm.getResultSet();
            while (resultSet.next()) {
                int idpoupanca = resultSet.getInt("idpoupanca");
                int idgerente = resultSet.getInt("idgerente");
                int idcliente = resultSet.getInt("idcliente");
                int numero = resultSet.getInt("numero");
                double saldo = resultSet.getDouble("saldo");
                boolean ativo = resultSet.getBoolean("ativo");
                Date data = resultSet.getDate("dtrendimento");
                ContaPoupanca conta = new ContaPoupanca(data, numero, idpoupanca, ativo, idgerente, idcliente, saldo);
                return conta;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "PoupancaDao " + e);
        }
        return null;
    }

    public ContaPoupanca selectForIdCliente(int idCliente) throws SQLException {
        String sql = "select * from banco.conta_poupanca WHERE conta_poupanca.idcliente = ? ";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idCliente);
            pstm.execute();
            ResultSet resultSet = pstm.getResultSet();
            while (resultSet.next()) {
                int idpoupanca = resultSet.getInt("idpoupanca");
                int idgerente = resultSet.getInt("idgerente");
                int idcliente = resultSet.getInt("idcliente");
                int numero = resultSet.getInt("numero");
                double saldo = resultSet.getDouble("saldo");
                boolean ativo = resultSet.getBoolean("ativo");
                Date data = resultSet.getDate("dtrendimento");
                ContaPoupanca conta = new ContaPoupanca(data, numero, idpoupanca, ativo, idgerente, idcliente, saldo);
                return conta;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "PoupancaDao " + e);
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
            JOptionPane.showMessageDialog(null, "PoupancaDao " + e);
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
            JOptionPane.showMessageDialog(null, "PoupancaDao " + e);
        }
        pstm.close();
        conn.close();
    }
}
