package DatuAtzipena;

import java.util.Scanner;

/**
 * Menua klaseak XML fitxategiaren kudeaketa menua erakusten du eta erabiltzaileak aukeratutako funtzionalitateak exekutatzen ditu.
 * Menuak aukera ezberdinak eskaintzen ditu XML fitxategia kudeatzeko: datuak sortu, erregistroak bilatu, bistaratu eta ezabatu.
 */
public class Menua {

    // Eskaneatzaile bat erabiltzailea sartzeko datuak lortzeko
    private static final Scanner eskaneatzailea = new Scanner(System.in);

    /**
     * XML fitxategiaren kudeaketa menua erakusten du.
     * Erabiltzaileak aukera bat hautatzen du eta aukeraren arabera, dagokion funtzionalitatea exekutatzen da.
     */
    public static void erakutsiMenua() {
        int aukera;
        do {
            // Menua bistaratzen da
            System.out.println("\n--- XML Fitxategiaren Kudeaketa Menua ---");
            System.out.println("1. XML fitxategia sortu edo gainidatzi hasierako datuekin");
            System.out.println("2. Erregistro guztiak bistaratu");
            System.out.println("3. Erregistro bat bilatu kodearen arabera");
            System.out.println("4. Hitz bat daukaten erregistroak bilatu");
            System.out.println("5. Erregistro bat ezabatu kodearen arabera");
            System.out.println("0. Irten");
            System.out.print("Aukeratu aukera bat: ");
            // Erabiltzaileak aukeratzen duen aukera irakurri
            aukera = Integer.parseInt(eskaneatzailea.nextLine());

            // Aukeraren arabera funtzionalitatea exekutatzen da
            switch (aukera) {
                case 1 -> XMLKudeatzailea.hasierakoDatuakSortu();
                case 2 -> XMLKudeatzailea.erregistroGuztiakBistaratu();
                case 3 -> {
                    // Erregistro bat kodearen arabera bilatzea
                    System.out.print("Sartu bilatu nahi duzun erregistroaren kodea: ");
                    String kodea = eskaneatzailea.nextLine();
                    Metodoak.erregistroaKodearenAraberaBilatu(kodea);
                }
                case 4 -> {
                    // Hitz gakoarekin erregistroak bilatzea
                    System.out.print("Sartu bilatu nahi duzun hitz gakoa: ");
                    String hitza = eskaneatzailea.nextLine();
                    Metodoak.hitzGakoarekinErregistroakBilatu(hitza);
                }
                case 5 -> {
                    // Erregistro bat kodearen arabera ezabatzea
                    System.out.print("Sartu ezabatu nahi duzun erregistroaren kodea: ");
                    String kodea = eskaneatzailea.nextLine();
                    Metodoak.erregistroaKodearenAraberaEzabatu(kodea);
                }
                case 0 -> System.out.println("Irten egiten...");
                // Aukera baliogabea
                default -> System.out.println("Aukera baliogabea. Saiatu berriro.");
            }
        } while (aukera != 0); // 0 aukeratuz gelditzen den arte
    }
}
