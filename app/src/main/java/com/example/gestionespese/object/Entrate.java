package com.example.gestionespese.object;

public class Entrate {

    private String titolo;
    private String descrizione;
    private String data;
    private String ora;
    private double importo;

    public Entrate(String titolo, String descrizione, String data, String ora, double importo) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.ora = ora;
        this.importo = importo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

}
