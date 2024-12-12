package DatuAtzipena7;


import java.util.List;


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

    // Constructor vacío necesario para JAXB
    public Enpresa() {}

    // Constructor con todos los campos
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
