package DatuAtzipena7;

import java.time.LocalDate;

/**
 * Klase honek langilearen datuak jasotzeko eta eguneratzeko metodoak eskaintzen ditu, baita langilearen
 * egoera lanean dagoen edo ez jakiteko ere.
 */
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

    /**
     * Klaseko objektu berri bat sortzen du.
     * 
     * @param kodea Langilearen kodea.
     * @param abizena Langilearen abizena.
     * @param izena Langilearen izena.
     * @param kargua Langilearen kargua.
     * @param tratua Langilearen tratua (ohikoa, emakumea, etab.).
     * @param jaiotzeData Langilearen jaiotze data.
     * @param kontratuData Langilearen kontratu data.
     * @param helbidea Langilearen helbidea.
     * @param hiria Langilearen bizitoki hiria.
     * @param lanean Langileak lan egiten duen ala ez adierazten duen balioa (true edo false).
     */
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

}
