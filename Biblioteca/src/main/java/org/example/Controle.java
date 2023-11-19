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

    public boolean excluir(String oid, Classe classe) {
        return p.excluir(oid, classe);
    }
    public <T extends ItemPersistencia> Iterator<T> getLista(Classe classe) {
        return p.obterTodos(classe);
    }

}

