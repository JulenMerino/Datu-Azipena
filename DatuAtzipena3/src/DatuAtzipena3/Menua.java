package DatuAtzipena3;
//Funtzionamendu aldetik, dena ondo, eskatzen ziren eskakizun guztiak betetzen dira, gerta daitezkeen erroreak kontrolatuz

//Kode aldetik, metodoak beste klase batean egotea hobeto egongo litzateke, menu.java-n menua bakarrik utziz. Betsela, iruzkinak lagungarriak dira.


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Menua klaseak menu nagusia kudeatzen du, fitxategien 
 * eta direktorioen informazioa erakusteko eta administratzeko.
 */
public class Menua {

    /** Langileak objektuak gordetzeko lista. */
    private static ArrayList<Langileak> langileak = new ArrayList<>();

    /** Lan direktorioa kudeatzeko fitxategia. */
    private static File lanDirektorioa = new File(System.getProperty("user.dir"));

    /**
     * Programaren hasiera, menu nagusia kudeatzen du.
     *
     * @param args Komando-lerroko argumentuak (ez dira erabiltzen).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lan direktorioa aukeratu
        aukeratuLanDirektorioa(scanner);
        
        boolean jarraitu = true;

        // Menuaren zirkulua
        while (jarraitu) {
            System.out.println("-------------- MENU --------------");
            System.out.println("1. Lan direktorioaren informazioa");
            System.out.println("2. Direktorio berria sortu eta lan direktoriora aldatu");
            System.out.println("3. Sortu fitxategia");
            System.out.println("4. 'aad' luzapeneko fitxategiak ezabatu");
            System.out.println("5. Aldatu lan direktroioa");
            System.out.println("6. Irten");
            System.out.println("----------------------------------");
            System.out.print("Aukeratu zenbaki bat: ");

            int aukera = scanner.nextInt();
            scanner.nextLine(); 

            switch (aukera) {
                case 1:
                    zerrendatuFitxategiak(); // Fitxategiak zerrendatu
                    break;
                case 2:
                    sortuEtaAldatuDirektorioa(scanner); // Direktorioa sortu eta aldatu
                    break;
                case 3:
                    sortuFitxategiak(); // Fitxategiak sortu
                    break;
                case 4:
                    ezabatuAadFitxategiak(); // 'aad' fitxategiak ezabatu
                    break;
                case 5:
                    aukeratuLanDirektorioa(scanner); // Lan direktorioa aldatu
                    break;
                case 6:
                    jarraitu = false; // Programatik irten
                    break;
                default:
                    System.out.println("Aukera okerra, saiatu berriro.");
            }
        }

        scanner.close(); // Scanner-a itxi
    }

    /**
     * Lan direktorioan dauden fitxategiak zerrendatzen ditu.
     */
    private static void zerrendatuFitxategiak() {
        File[] fitxategiak = lanDirektorioa.listFiles();
        if (fitxategiak != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy  hh:mm a");
            System.out.println("Bolumena " + lanDirektorioa.getAbsolutePath());
            System.out.println();
            int totalDirectorioak = 0;
            int totalFitxategiak = 0;
            long totalTamaina = 0;

            System.out.printf("%-20s %-10s %-12s %-20s %-30s\n", "Data", "Ordua", "Tamaina", "Mota", "Izena");
            System.out.println("------------------------------------------------------------------");

            for (File fitxategi : fitxategiak) {
                String data = sdf.format(new Date(fitxategi.lastModified()));
                String tamaina = fitxategi.isDirectory() ? "<DIR>" : String.valueOf(fitxategi.length());
                String mota = fitxategi.isDirectory() ? "Direktorioa" : "Fitxategia";
                totalTamaina += fitxategi.isDirectory() ? 0 : fitxategi.length();

                if (fitxategi.isDirectory()) {
                    totalDirectorioak++;
                } else {
                    totalFitxategiak++;
                }

                System.out.printf("%-20s %-10s %-12s %-20s %-30s\n",
                        data.substring(0, 10), data.substring(11), tamaina, mota, fitxategi.getName());
            }

            System.out.println("------------------------------------------------------------------");
            System.out.println("Total fitxategiak: " + totalFitxategiak);
            System.out.println("Total direktorioak: " + totalDirectorioak);
            System.out.println("Total tamaina: " + totalTamaina + " byte");
        } else {
            System.out.println("Ez dago fitxategirik edo direktorioak ezin dira zerrendatu.");
        }
    }

    /**
     * Berria sortu eta lan direktorioa aldatu.
     *
     * @param scanner Sarrera irakurtzeko Scanner objektua.
     */
    private static void sortuEtaAldatuDirektorioa(Scanner scanner) {
        System.out.print("Sartu sortu nahi duzun direktorioaren izena: ");
        String izena = scanner.nextLine().trim();
        File direktorioBerria = new File(lanDirektorioa, izena);

        if (direktorioBerria.exists()) {
            System.out.println("Direktorioa jadanik existitzen da.");
        } else {
            if (direktorioBerria.mkdir()) {
                lanDirektorioa = direktorioBerria; // Lan direktorioa aldatu
                System.out.println("Direktorio berria sortu da eta lan-direktorio bihurtu da: " + lanDirektorioa.getAbsolutePath());
            } else {
                System.out.println("Errorea direktorioa sortzean.");
            }
        }
    }

    /**
     * Langileei buruzko fitxategiak sortzen ditu.
     */
    private static void sortuFitxategiak() {
        // Langileak gehitzen dira
        langileak.add(new Langileak("01", "Davolio", "Nancy", "Representante de ventas", "Stra.", 
                LocalDate.of(1968, 12, 8), LocalDate.of(1992, 5, 1), "507-20th Ave.E.", "Seattle", true));
        langileak.add(new Langileak("02", "Fuller", "Andrew", "Vicepresidente comercial", "Dr.", 
                LocalDate.of(1952, 2, 19), LocalDate.of(1992, 8, 14), "908 W. Capital Way", "Tacoma", true));
        langileak.add(new Langileak("03", "Leverling", "Janet", "Representante de ventas", "Stra.", 
                LocalDate.of(1963, 8, 30), LocalDate.of(1992, 4, 1), "722 Moss Bay Blvd.", "Kirkland", false));
        langileak.add(new Langileak("04", "Peacock", "Margaret", "Representante de ventas", "Sra.", 
                LocalDate.of(1958, 9, 19), LocalDate.of(1993, 5, 3), "4110 Old Redmond Rd.", "Redmond", true));
        langileak.add(new Langileak("05", "Buchanan", "Steven", "Gerente de ventas", "Sr.", 
                LocalDate.of(1955, 3, 4), LocalDate.of(1993, 10, 17), "14 Garrett Hill", "Londres", true));
        langileak.add(new Langileak("06", "Suyama", "Michel", "Representante de ventas", "Sr.", 
                LocalDate.of(1963, 7, 2), LocalDate.of(1993, 10, 17), "Coventry House", "Londres", true));
        langileak.add(new Langileak("07", "King", "Robert", "Representante de ventas", "Sr.", 
                LocalDate.of(1960, 5, 29), LocalDate.of(1994, 1, 2), "Edgeham Hollow", "Londres", false));
        langileak.add(new Langileak("08", "Callahan", "Laura", "Coordinador ventas internacionales", "Stra.", 
                LocalDate.of(1958, 1, 9), LocalDate.of(1994, 3, 5), "4726-11th Ave.N.E.", "Seattle", true));
        langileak.add(new Langileak("09", "Dodsworth", "Anne", "Representante de ventas", "Stra.", 
                LocalDate.of(1969, 7, 2), LocalDate.of(1994, 11, 2), "7 Houndstooth Rd", "Londres", false));

        // Langile bakoitzeko fitxategia sortu
        for (Langileak pertsona : langileak) {
            String izena = pertsona.getizena() + pertsona.getabizena() + ".aad"; 
            File fitxategia = new File(lanDirektorioa, izena);
            try {
                if (fitxategia.createNewFile()) {
                    // Fitxategian informazioa idatzi
                    try (FileWriter writer = new FileWriter(fitxategia)) {
                        writer.write("Kodea: " + pertsona.getkodea() + "\n");
                        writer.write("Izena: " + pertsona.getizena() + " " + pertsona.getabizena() + "\n");
                        writer.write("Kargua: " + pertsona.getkargua() + "\n");
                        writer.write("Helbidea: " + pertsona.gethelbidea() + "\n");
                        writer.write("Hiria: " + pertsona.gethiria() + "\n");
                        writer.write("Jaiotze data: " + pertsona.getjaiotzeData() + "\n");
                        writer.write("Kontratazio data: " + pertsona.getkontratuData() + "\n");
                        writer.flush(); 
                    }
                    System.out.println(izena + " fitxategia sortu da eta informazioa idatzi da.");
                } else {
                    System.out.println(izena + " fitxategia ezin izan da sortu.");
                }
            } catch (IOException e) {
                System.out.println("Errorea fitxategia sortzean: " + e.getMessage());
            }
        }
    }

    /**
     * '.aad' luzapeneko fitxategiak ezabatzen ditu.
     */
    private static void ezabatuAadFitxategiak() {
        File[] fitxategiak = lanDirektorioa.listFiles((dir, name) -> name.endsWith(".aad"));
        if (fitxategiak != null && fitxategiak.length > 0) {
            for (File fitxategi : fitxategiak) {
                if (fitxategi.delete()) {
                    System.out.println(fitxategi.getName() + " fitxategia ezabatu da.");
                } else {
                    System.out.println(fitxategi.getName() + " ezin izan da ezabatu.");
                }
            }
        } else {
            System.out.println("Ez dira 'aad' fitxategirik aurkitu.");
        }
    }

    /**
     * Lan direktorioa aukeratzeko aukera ematen du.
     *
     * @param scanner Sarrera irakurtzeko Scanner objektua.
     */
    private static void aukeratuLanDirektorioa(Scanner scanner) {
        System.out.print("Sartu lan-direktorioaren izena (edo huts utzi uneko direktorioa erabiltzeko): ");
        String direktorioa = scanner.nextLine().trim();

        if (!direktorioa.isEmpty()) {
            File bideBerria = new File(direktorioa);
            if (bideBerria.exists() && bideBerria.isDirectory()) {
                lanDirektorioa = bideBerria; // Lan direktorioa aldatu
                System.out.println("Lan-direktorioa aldatu da: " + lanDirektorioa.getAbsolutePath());
            } else {
                System.out.println("Direktorioa ez da existitzen edo ez da baliagarria. Uneko direktorioa erabiliko da: " + lanDirektorioa.getAbsolutePath());
            }
        } else {
            System.out.println("Uneko direktorioa erabiltzen jarraitzen da: " + lanDirektorioa.getAbsolutePath());
        }
    }
}
