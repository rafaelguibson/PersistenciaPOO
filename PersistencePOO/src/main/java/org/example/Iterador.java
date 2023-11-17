package org.example;

import java.util.ArrayList;

public class Iterador {
    protected ArrayList<ItemPersistencia> colecao;
    protected int indice;

    public Iterador() {
        colecao = new ArrayList<ItemPersistencia>();
        indice = 0;
    }

    public Iterador(ArrayList<ItemPersistencia> arl) {
        colecao = arl;
        indice = 0;
    }

    public boolean vazio() {
        return colecao.isEmpty();
    }

    public void primeiro() {
        indice = 0;
    }

    public void fim() {
        indice = colecao.size() - 1;
    }

    public boolean anterior() {
        if (indice > 0) {
            indice--;
            return true;
        }
        return false;
    }

    public boolean proximo() {
        if (indice < (colecao.size() - 1)) {
            indice++;
            return true;
        }
        return false;
    }

    public ItemPersistencia obter() {
        return colecao.get(indice);
    }
}

