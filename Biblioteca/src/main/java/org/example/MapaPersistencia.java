package org.example;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

abstract class MapaPersistencia<T extends ItemPersistencia> {
    protected ArrayList<T> colecaoObjetos;
    protected int indice;

    protected boolean haItemPersistido(int oid) {
        System.out.println("entrou ha");
        boolean resposta = false;
        for (int i = 0; i < colecaoObjetos.size(); i++) {
            System.out.println("Entrou no for\n");
            System.out.println(colecaoObjetos.get(i).getOID() + " = " + oid + "?\n");
            if (colecaoObjetos.get(i).getOID() == oid) {
                resposta = true;
                indice = i;
            }
        }
        System.out.println(resposta);
        return resposta;
    }

    protected abstract void inserirItemNoArmazenamento(T itemPersistencia, Connection conexao);

    protected abstract void excluirItemNoArmazenamento(Connection conexao);

    public MapaPersistencia() {
        colecaoObjetos = new ArrayList<>();
        colecaoObjetos.clear();
        indice = -1;
    }

    public boolean inserir(ItemPersistencia itemPersistencia, Connection connection) {
        boolean resposta = false;
        if (!haItemPersistido(itemPersistencia.getOID())) {
            inserirItemNoArmazenamento((T) itemPersistencia, connection);
            resposta = true;
        }
        return resposta;
    }

    public boolean excluir(int oid, Connection connection) {
        boolean resposta = false;
        if (haItemPersistido(oid)) {
            System.out.println("entrou mapa\n");
            excluirItemNoArmazenamento(connection);
            resposta = true;
        }
        return resposta;
    }

    public Iterator<T> obterTodos(Connection connection) {

        return colecaoObjetos.iterator();
    }

    public abstract Object obter(Connection connection, int item);
}
