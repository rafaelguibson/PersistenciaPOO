package org.example;

public class Cliente extends ItemPersistencia {
    private String nome;
    private String endereco;

    public Cliente(String oid) {
        super(oid);
        classe = Classe.CLIENTE;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }
}
