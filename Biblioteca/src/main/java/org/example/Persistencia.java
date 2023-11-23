package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;

class Persistencia {
    private Hashtable<Classe, MapaPersistencia<?>> mapeadores;
    private static Persistencia persistencia;

    static {
        persistencia = null;
    }

    private Persistencia() {
        mapeadores = new Hashtable<>();
        mapeadores.put(Classe.CLIENTE, new MPCliente());
        mapeadores.put(Classe.EMPRESTIMO, new MPEmprestimo());
        mapeadores.put(Classe.LIVRO, new MPLivro());
    }

    public static Persistencia obterInstancia() {
        if (persistencia == null) {
            persistencia = new Persistencia();
        }
        return persistencia;
    }

    public <T extends ItemPersistencia> boolean inserir(ItemPersistencia itemPersistencia) {
        try (Connection connection = ConexaoDB.obterConexao()) {
            return mapeadores.get(itemPersistencia.getClasse()).inserir(itemPersistencia, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int oid, Classe classe) {
        try (Connection connection = ConexaoDB.obterConexao()) {
            System.out.println("entrou pers\n");
            return mapeadores.get(classe).excluir(oid, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public <T extends ItemPersistencia> Iterator<T> obterTodos(Classe classe) {
        try (Connection connection = ConexaoDB.obterConexao()) {
            return ((MapaPersistencia<T>) mapeadores.get(classe)).obterTodos(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Object obter(Classe classe, int item) {
        try (Connection connection = ConexaoDB.obterConexao()) {
            return mapeadores.get(classe).obter(connection, item);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}

