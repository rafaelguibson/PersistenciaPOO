package org.example;

import java.util.Hashtable;

public class Persistencia {
    private Hashtable<Classe, MapaPersistencia> mapeadores;
    private static Persistencia persistencia;

    static {
        persistencia = null;
    }

    private Persistencia() {
        mapeadores = new Hashtable<Classe, MapaPersistencia>();
        mapeadores.put(Classe.CLIENTE, new MPCliente());
        mapeadores.put(Classe.FITA, new MPFita());
        mapeadores.put(Classe.LOCACAO, new MPLocacao());
    }

    public static Persistencia obterInstancia() {
        if (persistencia == null) {
            persistencia = new Persistencia();
        }

        return persistencia;
    }

    public boolean inserir(ItemPersistencia ip) {
        return mapeadores.get(ip.getClasse()).inserir(ip);
    }

    public boolean excluir(String oid, Classe classe) {
        return mapeadores.get(classe).excluir(oid);
    }

    public IteradorCliente todosClientes() {
        return new IteradorCliente(mapeadores.get(Classe.CLIENTE).obterTodos());
    }

    public IteradorFita todasFitas() {
        return new IteradorFita(mapeadores.get(Classe.FITA).obterTodos());
    }
}

