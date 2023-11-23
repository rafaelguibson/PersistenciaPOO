package org.example;

import java.sql.Date;

public class Livro extends ItemPersistencia {
    private int idLivro;
    private String titulo, autor, editora;
    private Date anoLancamento;

    public Livro(int oid, String titulo, String autor, String editora, Date anoLancamento) {
        super(oid);
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoLancamento = anoLancamento;
        classe = Classe.LIVRO;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public Date getAnoLancamento() {
        return anoLancamento;
    }
}
