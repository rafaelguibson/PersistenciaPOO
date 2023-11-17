package org.example;

import java.io.Serializable;

public class MPLocacao extends MapaPersistencia implements Serializable {
    private static final long serialVersionUID = 1L;

    protected void inserirItemNoArmazenamento(ItemPersistencia ip) {
        colecaoObjetos.add(ip);
    }

    protected void excluirItemNoArmazenamento() {
        colecaoObjetos.remove(indice);
    }

    public MPLocacao() {
        super();
    }
}

