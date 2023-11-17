package org.example;

import java.util.ArrayList;

public class IteradorCliente extends Iterador {
    public IteradorCliente(ArrayList<ItemPersistencia> arl) {
        super(arl);
    }

    public IteradorCliente(Iterador it) {
        colecao = it.colecao;
        indice = it.indice;
    }

    public Cliente obter() {
        return (Cliente) super.obter();
    }
}


