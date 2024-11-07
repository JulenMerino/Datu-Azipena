package DatuAtzipena;

import java.io.File;

public class Main {
	 /**
     * Programa honen sarrera metodoa.
     * Fitxategia irakurri eta fitxategiaren edukia erabiltzeko menu kudeatzailea aktibatzen du.
     * 
     * @param args Komando lerroko argumentuak.
     */
    public static void main(String[] args) {
        // Fitxategia irakurri.
        File fitxategia = FitxategiaIrakurri.irakurriFitxategia();
        
        // Fitxategia null bada, ez du ezer egiten.
        if (fitxategia == null) {
            return;
        }
        
        // Menuaren instantzia sortu eta fitxategia kudeatzeko menua aktibatu.
        Menua menu = new Menua();
        menu.manageMenu(fitxategia);
    }
}
