package br.banco.model;

import br.banco.dao.PoupancaDao;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaPoupanca extends AbstractConta {
    
    private Date dataRendimento;

    public ContaPoupanca(Date dataRendimento, int numero, int idConta, boolean status, int idGerente, int idCliente, double saldo) {
        super(numero, idConta, status, idGerente, idCliente, saldo);
        this.dataRendimento = dataRendimento;
    }
    
    public ContaPoupanca(int numero, int idConta, boolean status, int idGerente, int idCliente, double saldo) {
        super(numero, idConta, status, idGerente, idCliente, saldo);
    }

    public ContaPoupanca(Date dataFormat, int numero, int idGerente, int idCliente, double saldo) {
        super(numero, idGerente, idCliente, saldo);
        this.dataRendimento = dataFormat;
    }
    
    public Date getDataRendimento() {
        return dataRendimento;
    }

    public void setDataRendimento(Date dataRendimento) {
        this.dataRendimento = dataRendimento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public double sacar(double valor, int idCliente, double saldo1) {
        saldo1 = saldo1 - valor;
        PoupancaDao dao = new PoupancaDao();
        try {
            dao.updateSaldo(saldo1, idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(ContaPoupanca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saldo;
    }

    @Override
    public void depositar(double valor, int idCliente, double saldo1) {
        saldo1 = valor + saldo1;
        PoupancaDao dao = new PoupancaDao();
        try {
            dao.updateSaldo(saldo1, idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(ContaPoupanca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void extrato() {
    }

    @Override
    public void transferir(double valor, int numero, double saldo1) {
        saldo1 = saldo1 + valor;
        PoupancaDao dao = new PoupancaDao();
        try {
            dao.transferencia(saldo1, numero);
        } catch (SQLException ex) {
            Logger.getLogger(ContaPoupanca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
