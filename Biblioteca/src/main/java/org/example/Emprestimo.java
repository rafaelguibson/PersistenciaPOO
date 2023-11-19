package org.example;

import java.sql.Date;

public class Emprestimo extends ItemPersistencia {
    private int idEmprestimo, idCliente, idLivro;
    private Date dataEmprestimo;

    public Emprestimo(String oid, int idEmprestimo, int idCliente, int idLivro, Date dataEmprestimo) {
        super(oid);
        this.idEmprestimo = idEmprestimo;
        this.idCliente = idCliente;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        classe = Classe.EMPRESTIMO;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }
}
