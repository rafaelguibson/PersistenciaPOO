package org.example;

import java.io.Serializable;



public class ItemPersistencia implements Serializable {
    protected Classe classe;
    protected String oid;

    public ItemPersistencia(String oid) {
        this.oid = oid;
    }

    Classe getClasse() {
        return classe;
    }

    String getOID() {
        return oid;
    }
}
