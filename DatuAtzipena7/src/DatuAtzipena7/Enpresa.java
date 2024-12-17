package DatuAtzipena7;

import java.util.List;

/*
 * Klase honek enpresaren datuak jasotzeko eta eguneratzeko metodoak eskaintzen ditu.
 */
public class Enpresa {

    private int kodea;
    private String izena;
    private String sektorea;
    private int pertsonakop;
    private String telefonoa;
    private String emaila;
    private String helbidea;
    private String hiria;
    private List<Langileak> langileak;


    public Enpresa() {}

    /**
     * Klaseko objektu berri bat sortzen du.
     * 
     * @param kodea Enpresaren kodea.
     * @param izena Enpresaren izena.
     * @param sektorea Enpresaren sektorea (adibidez, teknologia, osasuna, etab.).
     * @param pertsonakop Enpresako pertsonen kopurua.
     * @param telefonoa Enpresaren telefono zenbakia.
     * @param emaila Enpresaren email helbidea.
     * @param helbidea Enpresaren helbidea.
     * @param hiria Enpresaren kokapen hiria.
     * @param langileak Enpresako langileen zerrenda.
     */
    public Enpresa(int kodea, String izena, String sektorea, int pertsonakop, String telefonoa,
                   String emaila, String helbidea, String hiria, List<Langileak> langileak) {
        this.kodea = kodea;
        this.izena = izena;
        this.sektorea = sektorea;
        this.pertsonakop = pertsonakop;
        this.telefonoa = telefonoa;
        this.emaila = emaila;
        this.helbidea = helbidea;
        this.hiria = hiria;
        this.langileak = langileak;
    }

    public int getKodea() {
        return kodea;
    }

    public String getIzena() {
        return izena;
    }

    public String getSektorea() {
        return sektorea;
    }

    public int getPertsonakop() {
        return pertsonakop;
    }

    public String getTelefonoa() {
        return telefonoa;
    }

    public String getEmaila() {
        return emaila;
    }

    public String getHelbidea() {
        return helbidea;
    }

    public String getHiria() {
        return hiria;
    }

    public List<Langileak> getLangileak() {
        return langileak;
    }
}
