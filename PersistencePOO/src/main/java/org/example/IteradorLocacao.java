package org.example;

import java.util.ArrayList;

public class IteradorLocacao extends Iterador {
    public IteradorLocacao(ArrayList<ItemPersistencia> arl) {
        super(arl);
    }

    public IteradorLocacao(Iterador it) {
        colecao = it.colecao;
        indice = it.indice;
    }

    public Locacao obter() {
        return (Locacao) super.obter();
    }
}
