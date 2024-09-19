package DatuAtzipena;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class DatuAtzipena {

	
    private static final int Tamaina = 9; // Ajusta a la cantidad de datos en los arrays

    // Datuen Array-a
    static int kodea[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    static String izena[] = { "Nancy", "Andrew", "Janet", "Margaret", "Steven",
                               "Michel", "Robert", "Laura", "Anne" };
    static String abizena[] = { "Davolio", "Fuller", "Leverling", "Peacock", "Buchanan",
                                "Suyama", "King", "Callahan", "Dodsworth" };
    static String kargua[] = { "Representante de ventas", "Vicepresidente comercial", "Representante de ventas",
                               "Representante de ventas", "Gerente de ventas", "Representante de ventas",
                               "Representante de ventas", "Coordinador ventas internacionales", "Representante de ventas" };
    static String tratua[] = { "Stra.", "Dr.", "Stra.", "Sra.", "Sr.", "Sr.", "Sr.", "Stra.", "Stra." };
    static LocalDate jaiotzeData[] = { LocalDate.of(1968, 12, 8), LocalDate.of(1952, 2, 19), LocalDate.of(1963, 8, 30),
                                       LocalDate.of(1958, 9, 19), LocalDate.of(1955, 3, 4), LocalDate.of(1963, 7, 2),
                                       LocalDate.of(1960, 5, 29), LocalDate.of(1958, 1, 9), LocalDate.of(1969, 7, 2) };
    static int adina[] = new int[Tamaina]; // Adina arraya hasieratu beharrean
    static LocalDate kontratuData[] = { LocalDate.of(1992, 5, 1), LocalDate.of(1992, 8, 14), LocalDate.of(1992, 4, 1),
                                        LocalDate.of(1993, 5, 3), LocalDate.of(1993, 10, 17), LocalDate.of(1993, 10, 17),
                                        LocalDate.of(1994, 1, 2), LocalDate.of(1994, 3, 5), LocalDate.of(1994, 11, 2) };
    static String helbidea[] = { "507-20th Ave.E.", "908 W. Capital Way", "722 Moss Bay Blvd.",
                                 "4110 Old Redmond Rd.", "14 Garrett Hill", "Coventry House",
                                 "Edgeham Hollow", "4726-11th Ave.N.E.", "7 Houndstooth Rd." };
    static String hiria[] = { "Seattle", "Tacoma", "Kirkland", "Redmond", "Londres", "Londres",
                              "Londres", "Seattle", "Londres" };

    private static int erregistroKop = Tamaina; 
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Kalkulatu adinak hasieratu aurretik
        for (int i = 0; i < Tamaina; i++) {
            adina[i] = calcularAdina(jaiotzeData[i]);
        }

        int Aukera;

        do {
            erakutsiMenua();
            System.out.println("Zer aukeratuko duzu:");
            Aukera = sc.nextInt();

            switch (Aukera) {
                case 1:
                    ErregistroZerrenda();
                    break;
                case 2:
                    kontsultatuErresitroa();
                    break;
                case 3:
                    batBesKalkulatu();
                    break;
                case 4:
                    zenbakiHandiena();
                    break;
                case 5:
                    dataZaharrena();
                    break;
                case 6:
                    textuLuzeena();
                    break;
                case 0:
                    System.out.println("Programatik irteten");
                    break;
                default:
                    System.out.println("Sartutako zenbakiak ez du aukerarik.");
            }
            
            if (Aukera != 0) {
                if (!galdeMenuaEdoItxi()) {
                    Aukera = 0; 
                }
            }
        } while (Aukera != 0);
    }
    
    /**
     * Jaiotze datatik adina kalkulatzen duen metodoa.
     * 
     * @param jaiotzeData Jaiotze data.
     * @return Kalkulatutako adina urteetan.
     */
    public static int calcularAdina(LocalDate jaiotzeData) {
        LocalDate gaur = LocalDate.now();
        Period adina = Period.between(jaiotzeData, gaur);
        return adina.getYears();
    }

    /**
     * Menua erakusten duen metodoa.
     */
    public static void erakutsiMenua() {
        System.out.println("\nMenua:");
        System.out.println("1. Erregistro guztia zerrendatu");
        System.out.println("2. Erregistria kontsultatu kode bidez");
        System.out.println("3. Lortu zenbaki eremuan bataz-bestekoa");
        System.out.println("4. Lortu zenbaki eremuan balore handiena");
        System.out.println("5. Lortu data zaharrena");
        System.out.println("6. Lortu kate luzeena");
        System.out.println("0. Irten");
    }
    
    /**
     * Menu nagusira itzuli nahi den edo programatik irten nahi den galdetzen duen metodoa.
     * 
     * @return Bai edo ez erantzuten duen boolean.
     */
    public static boolean galdeMenuaEdoItxi() {
        System.out.println("Menu nagusira itzuli nahi duzu? (bai/ez):");
        String erantzuna = sc.next();
        return erantzuna.equalsIgnoreCase("bai");
    }
    
    /**
     * Erregistro guztiak zerrendatzen dituen metodoa.
     */
    public static void ErregistroZerrenda() {
        if (erregistroKop == 0) {
            System.out.println("Ez dago erregistrorik.");
        } else {
            for (int i = 0; i < erregistroKop; i++) {
                System.out.println("Id: " + kodea[i] + 
                                    ", Abizena: " + abizena[i] +
                                    ", Izena: " + izena[i] + 
                                    ", Kargua: " + kargua[i] + 
                                    ", Tratua: " + tratua[i] + 
                                    ", Jaiotze data: " + jaiotzeData[i] + 
                                    ", Adina: " + adina[i] + 
                                    ", Kontratazio data: " + kontratuData[i] + 
                                    ", Helbidea: " + helbidea[i] + 
                                    ", Hiria: " + hiria[i] + ". ");
            }
        }
    }
    
    /**
     * Kodea emanda, erregistroa kontsultatzen duen metodoa.
     */
    public static void kontsultatuErresitroa() {
        System.out.print("Zer kode bilatzen ari zara:");
        int sartutakoKodea = sc.nextInt();
        int indizea = bilatuKodea(sartutakoKodea);
        if (indizea != -1) {
            System.out.println("Id: " + kodea[indizea] + 
                                ", Abizena: " + abizena[indizea] +
                                ", Izena: " + izena[indizea] + 
                                ", Kargua: " + kargua[indizea] + 
                                ", Tratua: " + tratua[indizea] + 
                                ", Jaiotze data: " + jaiotzeData[indizea] + 
                                ", Adina: " + adina[indizea] + 
                                ", Kontratazio data: " + kontratuData[indizea] + 
                                ", Helbidea: " + helbidea[indizea] + 
                                ", Hiria: " + hiria[indizea] + ". ");
        } else {
            System.out.println("Ez da erregistrorik aurkitu zenbaki horrekin.");
        }
    }
    
    /**
     * Adinen bataz besteko balioa kalkulatzen duen metodoa.
     */
    public static void batBesKalkulatu() {
        if (erregistroKop == 0) {
            System.out.println("Ez dago erregistrorik.");
            return;
        }
        double batuketa = 0;
        for (int i = 0; i < erregistroKop; i++) {
            batuketa += adina[i];
        }
        double batbes = batuketa / erregistroKop;
        System.out.printf("Adinen bataz bestekoa: %.2f\n", batbes);
    }
    
    /**
     * Adina handiena duen erregistroa bilatzen duen metodoa.
     */
    public static void zenbakiHandiena() {
        if (erregistroKop == 0) {
            System.out.println("Ez dago erregistrorik.");
            return;
        }
        double adinaHandiena = adina[0];
        int indizeHandiena = 0;
        for (int i = 1; i < erregistroKop; i++) {
            if (adina[i] > adinaHandiena) {
                adinaHandiena = adina[i];
                indizeHandiena = i;
            }
        }
        System.out.println("Adina handienaren erregistroa: Id: " + kodea[indizeHandiena] +
                           ", Abizena: " + abizena[indizeHandiena] +
                           ", Izena: " + izena[indizeHandiena] + 
                           ", Kargua: " + kargua[indizeHandiena] + 
                           ", Tratua: " + tratua[indizeHandiena] +
                           ", Jaiotze data: " + jaiotzeData[indizeHandiena] + 
                           ", Adina: " + adina[indizeHandiena] + 
                           ", Kontratazio data: " + kontratuData[indizeHandiena] +
                           ", Helbidea: " + helbidea[indizeHandiena] +
                           ", Hiria: " + hiria[indizeHandiena] + ". ");
    }
    
    /**
     * Kontratu datuen artean data zaharrena bilatzen duen metodoa.
     */
    public static void dataZaharrena() {
        if (erregistroKop == 0) {
            System.out.println("Ez dago erregistrorik.");
            return;
        }

        LocalDate dataZaharrena = kontratuData[0];
        int indizeZaharrena = 0;

        for (int i = 1; i < erregistroKop; i++) {
            if (kontratuData[i].isBefore(dataZaharrena)) {
                dataZaharrena = kontratuData[i];
                indizeZaharrena = i;
            }
        }
        System.out.println("Enpresan denbora gehienda daraman erregistroa: Id: " + kodea[indizeZaharrena] + 
                           ", Abizena: " + abizena[indizeZaharrena] +
                           ", Izena: " + izena[indizeZaharrena] +
                           ", Kargua: " + kargua[indizeZaharrena] +
                           ", Tratua: " + tratua[indizeZaharrena] +
                           ", Jaiotze data: " + jaiotzeData[indizeZaharrena] +
                           ", Adina: " + adina[indizeZaharrena] +
                           ", Kontratazio data: " + kontratuData[indizeZaharrena] +
                           ", Helbidea: " + helbidea[indizeZaharrena] +
                           ", Hiria: " + hiria[indizeZaharrena] + ". ");
    }
    
    /**
     * Kargu izen luzeena duen erregistroa bilatzen duen metodoa.
     */
    public static void textuLuzeena() {
        if (erregistroKop == 0) {
            System.out.println("Ez dago erregistrorik.");
            return;
        }
        String karguLuzeena = kargua[0];
        int indizeLuzeena = 0;
        for (int i = 1; i < erregistroKop; i++) {
            if (kargua[i].length() > karguLuzeena.length()) {
                karguLuzeena = kargua[i];
                indizeLuzeena = i;
            }
        }
        System.out.println("Kargu izen luzeena duen erregistroa: Id: " + kodea[indizeLuzeena] +
                           ", Abizena: " + abizena[indizeLuzeena] +
                           ", Izena: " + izena[indizeLuzeena] +
                           ", Kargua: " + kargua[indizeLuzeena] +
                           ", Tratua: " + tratua[indizeLuzeena] +
                           ", Jaiotze data: " + jaiotzeData[indizeLuzeena] +
                           ", Adina: " + adina[indizeLuzeena] +
                           ", Kontratazio data: " + kontratuData[indizeLuzeena] +
                           ", Helbidea: " + helbidea[indizeLuzeena] +
                           ", Hiria: " + hiria[indizeLuzeena] + ". ");
    }
    
    /**
     * Kodearen arabera erregistroaren indizea bilatzen duen metodoa.
     * 
     * @param kodeaId Kodea bilatu behar den.
     * @return Erregistroaren indizea edo -1 kode hori aurkitzen ez bada.
     */
    public static int bilatuKodea(int kodeaId) {
        for (int i = 0; i < erregistroKop; i++) {
            if (kodea[i] == kodeaId) {
                return i;
            }
        }
        return -1; // Ez du kode hori aurkitu
    }
}
