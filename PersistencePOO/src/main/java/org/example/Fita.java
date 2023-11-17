package org.example;

public class Fita extends ItemPersistencia {
    private String titulo;
    private String genero;

    public Fita(String oid) {
        super(oid);
        classe = Classe.FITA;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }
}
