package br.banco.model;

import br.banco.dao.ClienteDao;
import br.banco.dao.CorrenteDao;
import br.banco.dao.PoupancaDao;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Gerente {
    private int id;
    private String nome;
    
    public Gerente() {
    }

    public Gerente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void alterarLimite(double limite, int num) throws SQLException{
        CorrenteDao dao = new CorrenteDao();
        if (dao.selectForNumero(num) == null) {
            JOptionPane.showMessageDialog(null, "Conta não Existe!");
        }else{
            dao.updateLimiteTotal(limite, num);
            JOptionPane.showMessageDialog(null, "Limite Alterado!");
        }
    }
    public void alterarStatus(boolean status, int num) throws SQLException{
        CorrenteDao correnteDao = new CorrenteDao();
        PoupancaDao poupancaDao = new PoupancaDao();
        if (correnteDao.selectForNumero(num) != null) {
            correnteDao.updateStatus(status, num);
            JOptionPane.showMessageDialog(null, "Status Alterado!");
        }else if (poupancaDao.selectForNumero(num) != null) {
            poupancaDao.updateStatus(status, num);
            JOptionPane.showMessageDialog(null, "Status Alterado!");
        }else{
            JOptionPane.showMessageDialog(null, "Conta não Existe!");
        }
    }
    public void cadastraContaPoupanca(ContaPoupanca conta) throws SQLException{
        PoupancaDao dao = new PoupancaDao();
        dao.cadastrarContaPoupanca(conta);
    }
    public void cadastraContaCorrente(ContaCorrente conta) throws SQLException{
        CorrenteDao dao = new CorrenteDao();
        dao.cadastrarContaCorrente(conta);
    }
    public void cadastraCliente(Cliente cli) throws SQLException{
        ClienteDao dao = new ClienteDao();
        dao.cadastrarCliente(cli);
    }
  
}
