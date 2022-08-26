package br.banco.model;

public abstract class AbstractConta implements ImplConta{
    int numero;
    int idConta;
    boolean status;
    int idGerente;
    int idCliente;
    double saldo;

    public AbstractConta(int numero, int idConta, boolean status, int idGerente, int idCliente, double saldo) {
        this.numero = numero;
        this.idConta = idConta;
        this.status = status;
        this.idGerente = idGerente;
        this.idCliente = idCliente;
        this.saldo = saldo;
    }

    public AbstractConta(int numero, int idGerente, int idCliente, double saldo) {
        this.numero = numero;
        this.idGerente = idGerente;
        this.idCliente = idCliente;
        this.saldo = saldo;
    }
}
