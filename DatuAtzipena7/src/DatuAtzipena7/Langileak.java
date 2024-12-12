package DatuAtzipena7;

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
    public String getKodea() {
        return kodea;
    }

    public void setKodea(String kodea) {
        this.kodea = kodea;
    }

    public String getAbizena() {
        return abizena;
    }

    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getKargua() {
        return kargua;
    }

    public void setKargua(String kargua) {
        this.kargua = kargua;
    }

    public String getTratua() {
        return tratua;
    }

    public void setTratua(String tratua) {
        this.tratua = tratua;
    }

    public LocalDate getJaiotzeData() {
        return jaiotzeData;
    }

    public void setJaiotzeData(LocalDate jaiotzeData) {
        this.jaiotzeData = jaiotzeData;
    }

    public LocalDate getKontratuData() {
        return kontratuData;
    }

    public void setKontratuData(LocalDate kontratuData) {
        this.kontratuData = kontratuData;
    }

    public String getHelbidea() {
        return helbidea;
    }

    public void setHelbidea(String helbidea) {
        this.helbidea = helbidea;
    }

    public String getHiria() {
        return hiria;
    }

    public void setHiria(String hiria) {
        this.hiria = hiria;
    }

    public boolean isLanean() {
        return lanean;
    }

    public void setLanean(boolean lanean) {
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
