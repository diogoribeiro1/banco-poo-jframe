package br.banco.model;

import java.util.Date;

public class Movimentacao {
    private int idMovimentacao;
    private int idConta;
    private Date dataMovimentacao;
    private double valor;
    private char tipoConta;
    private char tipoTransacao;

    public Movimentacao() {
    }

    public Movimentacao(int idMovimentacao, int idConta, Date dataMovimentacao, double valor, char tipoConta, char tipoTransacao) {
        this.idMovimentacao = idMovimentacao;
        this.idConta = idConta;
        this.valor = valor;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoConta = tipoConta;
        this.tipoTransacao = tipoTransacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public char getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(char tipoConta) {
        this.tipoConta = tipoConta;
    }

    public char getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(char tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
