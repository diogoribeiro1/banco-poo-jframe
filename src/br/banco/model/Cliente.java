package br.banco.model;

public class Cliente {
    private int idCLiente;
    private int cpf;
    private String nome;

    public Cliente() {
    }
    
     public Cliente(int cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public Cliente(int idCLiente, int cpf, String nome) {
        this.idCLiente = idCLiente;
        this.cpf = cpf;
        this.nome = nome;
    }
    
    public int getIdCLiente() {
        return idCLiente;
    }

    public void setIdCLiente(int idCLiente) {
        this.idCLiente = idCLiente;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
