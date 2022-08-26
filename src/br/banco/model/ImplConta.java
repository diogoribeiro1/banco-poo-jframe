package br.banco.model;

public interface ImplConta {
    double sacar(double valor, int idCliente, double saldo);
    void depositar(double valor, int idCliente, double saldo);
    void extrato();
    void transferir(double valor, int idCliente, double saldo1);
}
