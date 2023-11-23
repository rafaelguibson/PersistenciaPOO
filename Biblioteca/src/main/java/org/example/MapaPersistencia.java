package org.example;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

abstract class MapaPersistencia<T extends ItemPersistencia> {
    protected ArrayList<T> colecaoObjetos;
    protected int indice;

    protected boolean haItemPersistido(int id) { // Alteração: substituir oid por id
        boolean resposta = false;
        for (int i = 0; i < colecaoObjetos.size(); i++) {
            if (colecaoObjetos.get(i).getId() == id) { // Alteração: substituir getOID por getId
                resposta = true;
                indice = i;
            }
        }
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
        if (!haItemPersistido(itemPersistencia.getId())) { // Alteração: substituir getOID por getId
            inserirItemNoArmazenamento((T) itemPersistencia, connection);
            resposta = true;
        }
        return resposta;
    }

    public boolean excluir(int id, Connection connection) { // Alteração: substituir oid por id
        boolean resposta = false;
        if (haItemPersistido(id)) {
            excluirItemNoArmazenamento(connection);
            resposta = true;
        }
        return resposta;
    }

    public Iterator<T> obterTodos(Connection connection) {
        return colecaoObjetos.iterator();
    }

    public abstract Object obter(Connection connection, int id); // Alteração: substituir String por int
}
