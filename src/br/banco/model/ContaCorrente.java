package br.banco.model;

import br.banco.dao.CorrenteDao;
import br.banco.view.FrmDashboardCliente;
import br.banco.view.FrmLoginCliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ContaCorrente extends AbstractConta {

    private char tipo;
    private double limiteTotal;
    private double limiteUtilizado;

    public ContaCorrente(char tipo, double limiteTotal, double limiteUtilizado, int numero, int idConta, boolean status, int idGerente, int idCliente, double saldo) {
        super(numero, idConta, status, idGerente, idCliente, saldo);
        this.tipo = tipo;
        this.limiteTotal = limiteTotal;
        this.limiteUtilizado = limiteUtilizado;
        switch (this.tipo) {
            case 'S':
                limiteTotal = 100d;
                break;
            case 'E':
                limiteTotal = 1000d;
                break;
            default:
                limiteTotal = 0;//tipo de conta invalido
                break;
        }
    }

    public ContaCorrente(char tipo, int numero, int idGerente, int idCliente, double saldo) {
        super(numero, idGerente, idCliente, saldo);
        this.tipo = tipo;
        switch (this.tipo) {
            case 'S':
                limiteTotal = 100d;
                break;
            case 'E':
                limiteTotal = 1000d;
                break;
            default:
                limiteTotal = 0;//tipo de conta invalido
                break;
        }
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public double getLimiteTotal() {
        return limiteTotal;
    }

    public void setLimiteTotal(double limiteTotal) {
        this.limiteTotal = limiteTotal;
    }

    public double getLimiteUtilizado() {
        return limiteUtilizado;
    }

    public void setLimiteUtilizado(double limiteUtilizado) {
        this.limiteUtilizado = limiteUtilizado;
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
        try {
            CorrenteDao dao = new CorrenteDao();
            Double limiteDisponivel = limiteTotal - limiteUtilizado;
            if (saldo1 >= valor) {
                saldo1 = saldo1 - valor;
                dao.updateSaldo(saldo1, idCliente);
            } else if (saldo1 + limiteDisponivel >= valor) {
                valor -= saldo1;
                saldo1 = 0;
                limiteUtilizado += valor;
                dao.updateLimiteUtilizado(limiteUtilizado, idCliente);
                dao.updateSaldo(saldo1, idCliente);
            } else {
                JOptionPane.showMessageDialog(null, "Saque Insuficiente!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saldo1;
    }

    @Override
    public void depositar(double valor, int idCliente, double saldo1) {
        try {
            CorrenteDao dao = new CorrenteDao();
            if (limiteUtilizado > 0 && valor >= limiteUtilizado) {
                double num = valor - limiteUtilizado;
                saldo1 = saldo1 + num;
                limiteUtilizado = 0;
                dao.updateLimiteUtilizado(limiteUtilizado, idCliente);
                dao.updateSaldo(saldo1, idCliente);
            } else if (limiteUtilizado > 0 && valor < limiteUtilizado){
                limiteUtilizado = limiteUtilizado - valor;
                dao.updateLimiteUtilizado(limiteUtilizado, idCliente);
            }else{
                saldo1 = saldo1 + valor;
                dao.updateSaldo(saldo1, idCliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void extrato() {
    }

    @Override
    public void transferir(double valor, int numero, double saldo1) {
        try {
            CorrenteDao dao = new CorrenteDao();

            saldo1 = saldo1 + valor;
            dao.transferencia(saldo1, numero);

        } catch (SQLException ex) {
            Logger.getLogger(ContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
