package org.example;

public class Controle {
    private Persistencia p;

    public Controle() {
        p = Persistencia.obterInstancia();
    }

    public boolean inserir(ItemPersistencia ip) {
        return p.inserir(ip);
    }

    public boolean excluir(String oid, Classe classe) {
        return p.excluir(oid, classe);
    }

    public IteradorCliente getListaClientes() {
        return p.todosClientes();
    }

    public IteradorFita getListaFitas() {
        return p.todasFitas();
    }
}
