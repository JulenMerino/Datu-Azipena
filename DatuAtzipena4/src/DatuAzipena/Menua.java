package DatuAzipena;

import java.io.File;
import java.util.Scanner;

/**
 * Menua kudeatzeko klasea. Erabiltzaileari aukera ezberdinak eskaintzen dizkio
 * fitxategiarekin hainbat eragiketa egiteko.
 */
public class Menua {

    /**
     * Menuaren kudeatzailea. Erabiltzaileari aukerak erakutsi eta fitxategiarekin
     * hainbat eragiketa exekutatzen ditu.
     *
     * @param fitxategia Erabiliko den fitxategia.
     */
    public void manageMenu(File fitxategia) {
        int aukera;
        // Aukera 0 ez den bitartean menua erakutsi.
        do {
            aukera = displayMenu();
            // Aukeraren arabera dagokion metodoa exekutatu.
            switch (aukera) {
                case 1:
                    Metodoak.fitxategiaBete(fitxategia);
                    break;
                case 2:
                    Metodoak.fitxategiaHutsikUtzi(fitxategia);
                    break;
                case 3:
                    Metodoak.erregistroKopurua(fitxategia);
                    break;
                case 4:
                    Metodoak.bilatuErregistroa(fitxategia);
                    break;
                case 5:
                    Metodoak.bilatuErregistroakTestuEremuarekin(fitxategia);
                    break;
                case 6:
                    Metodoak.erregistroaGehitu(fitxategia);
                    break;
                case 7:
                    Metodoak.erregistroaEzabatu(fitxategia);
                    break;
                case 8:
                    Metodoak.fitxategiarenKopia(fitxategia);
                    break;
                case 9:
                    Metodoak.erregistroaAldatu(fitxategia);
                    break;
                case 0:
                    System.out.println("Agur!");
                    break;
                default:
                    System.out.println("Aukera okerra.");
            }
        } while (aukera != 0);
    }

    /**
     * Menua pantailan erakusten du eta erabiltzaileari aukera bat sartzeko eskatzen
     * dio.
     * 
     * @return Erabiltzaileak aukeratutako zenbakia.
     */
    private int displayMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----- MENU -----");
        System.out.println("1. Fitxategia bete");
        System.out.println("2. Fitxategia hutsik utzi");
        System.out.println("3. Erregistro kopurua zenbatu");
        System.out.println("4. Erregistroa bilatu kodearekin");
        System.out.println("5. Bilatu hitz batekin");
        System.out.println("6. Erregistro bat gehitu");
        System.out.println("7. Erregistro bat ezabatu");
        System.out.println("8. Fitxategiaren kopia sortu");
        System.out.println("9. Erregistro bat aldatu");
        System.out.println("0. Irten");
        System.out.print("Aukeratu: ");
        return sc.nextInt();
    }
}
