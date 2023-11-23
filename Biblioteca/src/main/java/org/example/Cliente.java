package org.example;

import org.example.Classe;
import org.example.ItemPersistencia;

import java.io.Serializable;

public class Cliente extends ItemPersistencia {
    private int idCliente;
    private String cpf, nome, telefone;

    public Cliente(int oid, String cpf, String nome, String telefone) {
        super(oid);
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        classe = Classe.CLIENTE;
    }

    public int getIdCliente() {
        return idCliente;
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
