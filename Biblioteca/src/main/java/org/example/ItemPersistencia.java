package org.example;

import java.io.Serializable;



public class ItemPersistencia implements Serializable {
    protected Classe classe;
    protected int oid;

    public ItemPersistencia(int oid) {
        this.oid = oid;
    }

    Classe getClasse() {
        return classe;
    }

    int getOID() {
        return oid;
    }
}
