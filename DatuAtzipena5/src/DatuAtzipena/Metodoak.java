package DatuAtzipena;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Metodoak klaseak fitxategiak kudeatzeko metodoez osatuta dago. Klaseak hainbat
 * funtzio eskaintzen ditu, hala nola, fitxategiak betetzea, hutsik uztea,
 * erregistroak bilatzea, erregistroak gehitzea, ezabatzea eta aldatzea, eta beste hainbat ekintza.
 */
public class Metodoak {
    private static final String VERSION = "Empleados 1.0";  // Aplikazioaren bertsioa

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Data formatua

    // Erabiltzaileentzako datuak
    static int kodea[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    static String izena[] = { "Nancy", "Andrew", "Janet", "Margaret", "Steven", "Michel", "Robert", "Laura", "Anne" };
    static String abizena[] = { "Davolio", "Fuller", "Leverling", "Peacock", "Buchanan", "Suyama", "King", "Callahan", "Dodsworth" };
    static String kargua[] = { "Representante de ventas", "Vicepresidente comercial", "Representante de ventas",
            "Representante de ventas", "Gerente de ventas", "Representante de ventas", "Representante de ventas",
            "Coordinador ventas internacionales", "Representante de ventas" };
    static String tratua[] = { "Stra.", "Dr.", "Stra.", "Sra.", "Sr.", "Sr.", "Sr.", "Stra.", "Stra." };
    static LocalDate jaiotzeData[] = { LocalDate.of(1968, 12, 8), LocalDate.of(1952, 2, 19), LocalDate.of(1963, 8, 30),
            LocalDate.of(1958, 9, 19), LocalDate.of(1955, 3, 4), LocalDate.of(1963, 7, 2), LocalDate.of(1960, 5, 29),
            LocalDate.of(1958, 1, 9), LocalDate.of(1969, 7, 2) };

    static LocalDate kontratuData[] = { LocalDate.of(1992, 5, 1), LocalDate.of(1992, 8, 14), LocalDate.of(1992, 4, 1),
            LocalDate.of(1993, 5, 3), LocalDate.of(1993, 10, 17), LocalDate.of(1993, 10, 17), LocalDate.of(1994, 1, 2),
            LocalDate.of(1994, 3, 5), LocalDate.of(1994, 11, 2) };
    static String helbidea[] = { "507-20th Ave.E.", "908 W. Capital Way", "722 Moss Bay Blvd.", "4110 Old Redmond Rd.",
            "14 Garrett Hill", "Coventry House", "Edgeham Hollow", "4726-11th Ave.N.E.", "7 Houndstooth Rd." };
    static String hiria[] = { "Seattle", "Tacoma", "Kirkland", "Redmond", "Londres", "Londres", "Londres", "Seattle", "Londres" };

    /**
     * Fitxategia betetzeko metodoa. Erregistro kopurua erabiltzaileari eskatzen dio
     * eta fitxategian gorde.
     *
     * @param fitxategia Fitxategia betetzeko
     */
    public static void fitxategiaBete(File fitxategia) {
    	try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "rw")) {
            String lehenLerroa = raf.readLine();
            
            // Si la primera línea no es la versión, la escribimos
            if (lehenLerroa == null || !lehenLerroa.equals(VERSION)) {
                raf.seek(0);
                raf.write(VERSION.getBytes(StandardCharsets.UTF_8)); 
                raf.write("\n".getBytes(StandardCharsets.UTF_8)); 
            } else {
                raf.seek(raf.length());
            }

            for (int i = 0; i < kodea.length; i++) {
                String registro = String.format("%-5d;%-20s;%-15s;%-10s;%-15s;%-15s;%-10s;%-20s;%s;\n",
                        kodea[i], izena[i], abizena[i], jaiotzeData[i].format(dateFormatter),
                        kargua[i], tratua[i], kontratuData[i], helbidea[i], hiria[i]);
                
                raf.write(registro.getBytes(StandardCharsets.UTF_8));
            }

            System.out.println("Fitxategia datuekin bete da.");
        } catch (IOException e) {
            System.out.println("Errorea fitxategia idaztean: " + e.getMessage());
        }
    }

    /**
     * Fitxategia hutsik uzteko metodoa. Erabiltzaileari baieztapena eskatzen dio
     * fitxategia hutsik uzteko.
     *
     * @param fitxategia Hutsik utzi nahi den fitxategia
     */
    public static void fitxategiaHutsikUtzi(File fitxategia) {
        Scanner sc = new Scanner(System.in);
        String erantzuna;
        do {
            System.out.println("Ziur zaude fitxategia hutsik utzi nahi duzula? (bai/ez)");
            erantzuna = sc.nextLine();
            if (!erantzuna.equalsIgnoreCase("bai") && !erantzuna.equalsIgnoreCase("ez")) {
                System.out.println("Sartu 'bai' edo 'ez' mesedez.");
            }
        } while (!erantzuna.equalsIgnoreCase("bai") && !erantzuna.equalsIgnoreCase("ez"));

        if (erantzuna.equalsIgnoreCase("bai")) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(fitxategia, false))) {
                pw.println(VERSION); 
                System.out.println("Fitxategia hutsik geratu da.");
            } catch (IOException e) {
                System.out.println("Errorea fitxategia hutsik uztean.");
            }
        } else {
            System.out.println("Ekintza bertan behera utzi da.");
        }
    }

    /**
     * Fitxategian erregistro bat bilatzen du kodea erabiliz.
     *
     * @param fitxategia Bilatuko den fitxategia
     */
    public static void bilatuErregistroa(File fitxategia) {
        Scanner sc = new Scanner(System.in);
        int kodea = -1;
        while (kodea < 0) {
            System.out.println("Sartu bilatu nahi duzun erregistroaren kodea:");
            if (sc.hasNextInt()) {
                kodea = sc.nextInt(); 
            } else {
                sc.next();
                System.out.println("Sartu zenbaki positibo bat.");
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "r")) {
            String lerroa;
            boolean aurkitua = false;
            while ((lerroa = raf.readLine()) != null) {
                if (lerroa.startsWith(String.valueOf(kodea))) {
                    System.out.println("Erregistroa aurkitu da: " + lerroa);
                    aurkitua = true;
                    break;
                }
            }
            if (!aurkitua) {
                System.out.println("Erregistroa ez da aurkitu.");
            }
        } catch (IOException e) {
            System.out.println("Errorea erregistroa bilatzean.");
        }
    }

    /**
     * Fitxategian testu eremu baten bidez erregistroak bilatzen ditu.
     *
     * @param fitxategia Bilatuko den fitxategia
     */
    public static void bilatuErregistroakTestuEremuarekin(File fitxategia) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sartu bilatu nahi duzun hitza:");
        String testua = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
            String lerroa;
            boolean aurkitua = false;
            while ((lerroa = br.readLine()) != null) {
                if (lerroa.contains(testua)) {
                    System.out.println("Erregistroa aurkitu da: " + lerroa);
                    aurkitua = true;
                }
            }
            if (!aurkitua) {
                System.out.println("Ez da erregistroa aurkitu.");
            }
        } catch (IOException e) {
            System.out.println("Errorea erregistroa bilatzean.");
        }
    }

    /**
     * Fitxategian erregistro bat ezabatzeko metodoa.
     * Erabiltzaileak kodea sartzen du eta fitxategian bilatzen da kode hori duen erregistroa.
     * Erregistroa aurkitu eta logikoki ezabatzen da, hau da, kodea negatibo bihurtzen da.
     *
     * @param fitxategia Ezabatu nahi den fitxategia
     */
    public static void erregistroaEzabatu(File fitxategia) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sartu ezabatu nahi duzun erregistroaren kodea:");
        String kodeaEzabatu = sc.nextLine().trim(); 

        boolean found = false;
        try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "rw")) {

            raf.readLine();

            long posicionInicial = raf.getFilePointer();
            String line;
            while ((line = raf.readLine()) != null) {

                String[] fields = line.split(";");
                if (fields.length >= 9) { 
                    String codigo = fields[0].trim();

                    // Kodea bilatzen du
                    if (codigo.equals(kodeaEzabatu)) {

                        String registroBorrado = String.format(
                            "%-5s;%-20s;%-15s;%-10s;%-15s;%-15s;%-10s;%-20s;%s;",
                            "-" + kodeaEzabatu, 
                            fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8]
                        );
                        if (registroBorrado.length() < line.length()) {
                            registroBorrado += " ".repeat(line.length() - registroBorrado.length());
                        }
                        raf.seek(posicionInicial); 
                        raf.writeBytes(registroBorrado);
                        found = true;
                        System.out.println("Erregistroa logikoki ezabatuta.");
                        break;
                    }
                }
                posicionInicial = raf.getFilePointer();
            }

            if (!found) {
                System.out.println("Ez da aurkitu erregistroa kodearekin: " + kodeaEzabatu);
            }

        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean edo idaztean: " + e.getMessage());
        }
    }

    /**
     * Ezabatutako erregistroak bistaratzen dituen metodoa.
     * Fitxategian ezabatutako erregistroak (kodea negatibo dutenak) bilatzen ditu
     * eta kontsultatzen duena pantailan erakusten du.
     *
     * @param fitxategia Erregistroak ikusi nahi den fitxategia
     */
    public static void ezbatutakoaErakutsi(File fitxategia) {

        try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "r")) {
        	
            raf.readLine();
            boolean found = false;

            String line;
            System.out.println("Ezabatutako erregistroak:");

            while ((line = raf.readLine()) != null) {
                String[] fields = line.split(";");

                if (fields.length >= 9) {
                    String kodea = fields[0].trim();
                    
                    if (kodea.startsWith("-")) {
                        String izena = fields[1].trim();
                        String abizena = fields[2].trim();
                        String jaiotzeData = fields[3].trim();
                        String kargua = fields[4].trim();
                        String tratua = fields[5].trim();
                        String kontratuData = fields[6].trim();
                        String helbidea = fields[7].trim();
                        String hiria = fields[8].trim();
                        System.out.printf(
                                "Kodea: %s; Izena: %s; Abizena: %s; Jaitze data: %s; Kargua: %s; Tratua: %s; Kontratu data %s; Helbidea %s; Hiria %s%n",
                                kodea, izena, abizena, jaiotzeData, kargua, tratua, kontratuData, helbidea, hiria);

                        found = true;
                    }

                }

            }

            if (!found) {
                System.out.println("Ez dago ezabatutako erregistrorik.");
            }

        } catch (IOException e) {

            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());

        }

    }

    /**
     * Fitxategian erregistro berri bat gehitzen duen metodoa.
     * Erabiltzaileari fitxategian erregistro berri bat sartu eta kodea ez dela aurretik
     * existitzen ziurtatzen dio. Kodea negatibo ez izatea ere egiaztatzen da.
     *
     * @param fitxategia Erregistroa gehituko den fitxategia
     */
    public static void erregistroaGehitu(File fitxategia) {
        try (FileOutputStream fos = new FileOutputStream(fitxategia, true)) {
            String erregistroa = sartuErregistroa(fitxategia);
            fos.write(erregistroa.getBytes(StandardCharsets.UTF_8));
            System.out.println("Erregistroa gehitu da.");
        } catch (IOException e) {
            System.out.println("Errorea erregistroa gehitzean.");
        }
    }

    /**
     * Fitxategian erregistro bat aldatzeko metodoa.
     * Erabiltzaileak sartutako kodearekin erregistro bat bilatzen du eta aurkitzen bada,
     * erregistroa aldatu egiten du. Kodea negatibo izanez gero, ezin da aldatu.
     *
     * @param fitxategia Aldatu nahi den fitxategia
     */
    public static void erregistroaAldatu(File fitxategia) {
        Scanner sc = new Scanner(System.in);
        int kodea = -1;

        while (kodea < 0) {
            System.out.println("Sartu aldatu nahi duzun erregistroaren kodea:");
            if (sc.hasNextInt()) {
                kodea = sc.nextInt();
            } else {
                sc.next(); 
                System.out.println("Sartu zenbaki positibo bat.");
            }
        }
        sc.nextLine(); 

        File irakurritakoFitxategia = new File("temp.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia));
             PrintWriter pw = new PrintWriter(new FileWriter(irakurritakoFitxategia))) {

            String lerroa;
            boolean aurkitua = false;
            while ((lerroa = br.readLine()) != null) {
                if (lerroa.startsWith(String.valueOf(kodea))) {
                    if (lerroa.startsWith("-")) {
                        System.out.println("Ez da posible aldatu ezabatutako erregistroa.");
                        pw.println(lerroa); 
                        continue; 
                    }

                    System.out.println("Aurkitutako erregistroa: " + lerroa);

                    String erregitroBerria = sartuErregistroa(fitxategia);
                    pw.println(erregitroBerria); 
                    aurkitua = true;
                } else {
                    pw.println(lerroa); 
                }
            }

            if (!aurkitua) {
                System.out.println("Ez da erregistroa aurkitu kode honekin: " + kodea);
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean edo idaztean.");
        }

        if (!fitxategia.delete()) {
            System.out.println("Errorea fitxategia ezabatu zenean.");
            return;
        }
        if (!irakurritakoFitxategia.renameTo(fitxategia)) {
            System.out.println("Errorea fitxategia berriz izendatzerakoan.");
        }
    }

    /**
     * Erregistro berri bat sartu eta fitxategian gorde.
     * Erregistro berri bat jasotzen du eta bere kodea egiaztatzen du
     * bestalde fitxategian sartutako kodearekin bat ez egitea.
     *
     * @param fitxategia Erregistroa gorde nahi den fitxategia
     * @return Sartutako erregistroaren stringa
     */
    private static String sartuErregistroa(File fitxategia) {
        Scanner sc = new Scanner(System.in);
        int kodea = -1;

        // Kodea egiaztatu, jadanik existitzen ez dela
        Set<Integer> existitzenKodeak = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
            br.readLine();
            String lerroa;
            while ((lerroa = br.readLine()) != null) {
                String[] erregistroak = lerroa.split(";");
                existitzenKodeak.add(Integer.parseInt(erregistroak[0].trim()));
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean.");
        }

        while (kodea < 0) {
            System.out.print("Sartu erregistroaren kodea (zenbaki positiboa): ");
            if (sc.hasNextInt()) {
                kodea = sc.nextInt();
                while (existitzenKodeak.contains(kodea)) {
                    System.out.println("Kodea existitzen da. Sartu beste bat.");
                    kodea = sc.nextInt(); 
                }
            } else {
                sc.next();
                System.out.println("Kodea zenbaki positiboa izan behar da.");
            }
        }
        sc.nextLine();
        
        // Beste xehetasun batzuk eskatzen ditu
        System.out.print("Sartu izena: ");
        String izena = sc.nextLine();
        System.out.print("Sartu abizena: ");
        String abizena = sc.nextLine();
        System.out.print("Sartu kargua: ");
        String kargua = sc.nextLine();
        System.out.print("Sartu tratua: ");
        String tratua = sc.nextLine();
        System.out.print("Sartu jaiotze data (yyyy-MM-dd): ");
        String jaiotzeData = sc.nextLine();
        System.out.print("Sartu kontratu data (yyyy-MM-dd): ");
        String kontratuData = sc.nextLine();
        System.out.print("Sartu helbidea: ");
        String helbidea = sc.nextLine();
        System.out.print("Sartu hiria: ");
        String hiria = sc.nextLine();


        return String.format("%-5d;%-20s;%-15s;%-10s;%-15s;%-15s;%-10s;%-20s;%s;", kodea, izena, abizena, jaiotzeData,
                kargua, tratua, kontratuData, helbidea, hiria);
    }
}