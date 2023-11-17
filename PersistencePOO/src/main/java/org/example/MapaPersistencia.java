package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class MapaPersistencia implements Serializable {
    protected ArrayList<ItemPersistencia> colecaoObjetos;
    protected int indice;

    protected boolean haItemPersistido(String oid) {
        boolean resposta = false;
        int i;
        for (i = 0; i < colecaoObjetos.size(); i++) {
            if (colecaoObjetos.get(i).getOID().equalsIgnoreCase(oid)) {
                resposta = true;
                indice = i;
            }
        }
        return resposta;
    }

    protected abstract void inserirItemNoArmazenamento(ItemPersistencia ip);

    protected abstract void excluirItemNoArmazenamento();

    public MapaPersistencia() {
        colecaoObjetos = new ArrayList<ItemPersistencia>();
        colecaoObjetos.clear();
        indice = -1;
    }

    public boolean inserir(ItemPersistencia ip) {
        boolean resposta = false;
        if (!haItemPersistido(ip.getOID())) {
            inserirItemNoArmazenamento(ip);
            resposta = true;
        }
        return resposta;
    }

    public boolean excluir(String oid) {
        boolean resposta = false;
        if (haItemPersistido(oid)) {
            excluirItemNoArmazenamento();
            resposta = true;
        }
        return resposta;
    }

    public Iterador obterTodos() {
        return new Iterador(colecaoObjetos);
    }
}

