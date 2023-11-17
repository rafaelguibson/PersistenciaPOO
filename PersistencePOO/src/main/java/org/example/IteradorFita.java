package org.example;

import java.util.ArrayList;

public class IteradorFita extends Iterador {
    public IteradorFita(ArrayList<ItemPersistencia> arl) {
        super(arl);
    }

    public IteradorFita(Iterador it) {
        colecao = it.colecao;
        indice = it.indice;
    }

    public Fita obter() {
        return (Fita) super.obter();
    }
}
