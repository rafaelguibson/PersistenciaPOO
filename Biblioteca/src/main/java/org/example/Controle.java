package org.example;

import java.util.Hashtable;
import java.util.Iterator;

class Controle {
    private Persistencia p;

    public Controle() {
        p = Persistencia.obterInstancia();
    }

    public <T extends ItemPersistencia> boolean inserir(T itemPersistencia) {
        return p.inserir(itemPersistencia);
    }

    public boolean excluir(int id, Classe classe) {
        System.out.println("Entrou excluir\n");
        return p.excluir(id, classe);
    }
    public <T extends ItemPersistencia> Iterator<T> getLista(Classe classe) {
        return p.obterTodos(classe);
    }

    public Object getItem(String item, Classe classe) {

        return p.obter(classe, item);
    }
}

