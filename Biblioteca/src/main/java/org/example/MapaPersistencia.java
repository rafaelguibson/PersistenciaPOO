package org.example;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

abstract class MapaPersistencia<T extends ItemPersistencia> {
    protected ArrayList<T> colecaoObjetos;
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

    protected abstract void inserirItemNoArmazenamento(T itemPersistencia);

    protected abstract void excluirItemNoArmazenamento();

    public MapaPersistencia() {
        colecaoObjetos = new ArrayList<>();
        colecaoObjetos.clear();
        indice = -1;
    }

    public boolean inserir(ItemPersistencia itemPersistencia, Connection connection) {
        boolean resposta = false;
        if (!haItemPersistido(itemPersistencia.getOID())) {
            inserirItemNoArmazenamento((T) itemPersistencia);
            resposta = true;
        }
        return resposta;
    }

    public boolean excluir(String oid, Connection connection) {
        boolean resposta = false;
        if (haItemPersistido(oid)) {
            excluirItemNoArmazenamento();
            resposta = true;
        }
        return resposta;
    }

    public Iterator<T> obterTodos(Connection connection) {
        return colecaoObjetos.iterator();
    }
}
