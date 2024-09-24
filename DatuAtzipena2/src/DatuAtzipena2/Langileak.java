package DatuAtzipena2;

import java.time.LocalDate;

public class Langileak {
    private String kodea;
    private String abizena;
    private String izena;
    private String kargua;
    private String tratua;
    private LocalDate jaiotzeData;
    private LocalDate kontratuData;
    private String helbidea;
    private String hiria;
    private boolean lanean; 

    // Constructor
    public Langileak(String kodea, String abizena, String izena, String kargua, String tratua,
                      LocalDate jaiotzeData, LocalDate kontratuData, String helbidea, String hiria, boolean lanean) {
        this.kodea = kodea;
        this.abizena = abizena;
        this.izena = izena;
        this.kargua = kargua;
        this.tratua = tratua;
        this.jaiotzeData = jaiotzeData;
        this.kontratuData = kontratuData;
        this.helbidea = helbidea;
        this.hiria = hiria;
        this.lanean = lanean;
    }

    // Getters y Setters
    public String getkodea() {
        return kodea;
    }

    public void setkodea(String kodea) {
        this.kodea = kodea;
    }

    public String getabizena() {
        return abizena;
    }

    public void setabizena(String abizena) {
        this.abizena = abizena;
    }

    public String getizena() {
        return izena;
    }

    public void setizena(String izena) {
        this.izena = izena;
    }

    public String getkargua() {
        return kargua;
    }

    public void setkargua(String kargua) {
        this.kargua = kargua;
    }

    public String gettratua() {
        return tratua;
    }

    public void settratua(String tratua) {
        this.tratua = tratua;
    }

    public LocalDate getjaiotzeData() {
        return jaiotzeData;
    }

    public void setjaiotzeData(LocalDate jaiotzeData) {
        this.jaiotzeData = jaiotzeData;
    }

    public LocalDate getkontratuData() {
        return kontratuData;
    }

    public void setkontratuData(LocalDate kontratuData) {
        this.kontratuData = kontratuData;
    }

    public String gethelbidea() {
        return helbidea;
    }

    public void sethelbidea(String helbidea) {
        this.helbidea = helbidea;
    }

    public String gethiria() {
        return hiria;
    }

    public void sethiria(String hiria) {
        this.hiria = hiria;
    }

    public boolean islanean() {
        return lanean;
    }

    public void setlanean(boolean lanean) {
        this.lanean = lanean;
    }

    // Método toString
    @Override
    public String toString() {
        return String.format("kodea: %s, izena: %s %s, kargua: %s, tratua: %s, Fecha Nacimiento: %s, Fecha Contratacion: %s, Dirección: %s, hiria: %s, lanean: %s",
                kodea, izena, abizena, kargua, tratua, jaiotzeData, kontratuData, helbidea, hiria, lanean ? "Sí" : "No");
    }

    // lanean dagoen edo ez jakiteko metodoa
    
    public String estadolanean() {
        return lanean ? "Langilea lanean" : "Langilea langabetua";
    }
}
