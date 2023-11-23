package org.example;

import java.io.Serializable;

public class ItemPersistencia implements Serializable {
    protected Classe classe;
    protected int id; // Alteração: substituir oid por id

    public ItemPersistencia(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Classe getClasse() {
        return classe;
    }
}
