package org.example;

public class Locacao extends ItemPersistencia {
    private String oidCliente;
    private String oidFita;
    private String data;

    public Locacao(String oid, String oidCliente, String oidFita, String data) {
        super(oid);
        this.oidCliente = oidCliente;
        this.oidFita = oidFita;
        this.data = data;
        classe = Classe.LOCACAO;
    }

    public String getOidCliente() {
        return oidCliente;
    }

    public String getOidFita() {
        return oidFita;
    }

    public String getData() {
        return data;
    }
}
