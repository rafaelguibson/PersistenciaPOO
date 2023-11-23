package org.example;

public class Cliente extends ItemPersistencia {
    private String cpf, nome, telefone;

    public Cliente(int id, String cpf, String nome, String telefone) {
        super(id);
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        classe = Classe.CLIENTE;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
}
