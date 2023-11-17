package org.example;

import java.io.Serializable;

public abstract class ItemPersistencia implements Serializable {
    protected Classe classe;
    protected String oid;

    public ItemPersistencia(String oid) {
        this.oid = oid;
    }

    public Classe getClasse() {
        return classe;
    }

    public String getOID() {
        return oid;
    }
}

