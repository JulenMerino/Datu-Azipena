package DatuAtzipena2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class main {
	
	/** 
     * Langileen zerrenda bat gordetzen duen ArrayList-a.
     */
    private static ArrayList<Langileak> langileak = new ArrayList<>();
    
    /**
     * Erabiltzailearen sarrerak jasotzeko Scanner-a.
     */
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Datak formateatzeko erabiltzen den DateTimeFormatter-a (dd/MM/yyyy formatua).
     */
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
    	
    	 // Langileen hasierako zerrenda sortzen da
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
    	
        boolean jarraitu = true;

        while (jarraitu) {
            erakutsiMenua();
            int aukera = scanner.nextInt();
            scanner.nextLine(); // Bufferra garbitu

            // Erabiltzailearen aukeraren arabera eragiketa bat egiten da
            switch (aukera) {
                case 1:
                    zerrendatuLangileak();
                    break;
                case 2:
                    bilatuKodigotik();
                    break;
                case 3:
                    zerrendatuTartekoDatak();
                    break;
                case 4:
                    gehituLangilea();
                    break;
                case 5:
                    kenduLangilea();
                    break;
                case 6:
                    ordenatuAbizenarenArabera();
                    break;
                case 7:
                    jarraitu = false;
                    System.out.println("Irten...");
                    break;
                default:
                    System.out.println("Aukera baliogabea.");
            }
        }
    }

    
    /**
     * Menu nagusia pantailaratzen du.
     */
    private static void erakutsiMenua() {
        System.out.println("===== Langileen Kudeaketarako Menua =====");
        System.out.println("1. Langile guztiak zerrendatu");
        System.out.println("2. Langilea bilatu kodearen arabera");
        System.out.println("3. Langileak tarteko daten arabera zerrendatu");
        System.out.println("4. Langile bat gehitu");
        System.out.println("5. Langile bat kendu");
        System.out.println("6. Langileak abizenaren arabera ordenatu");
        System.out.println("7. Irten");
        System.out.print("Aukera bat aukeratu: ");
    }

    /**
     * Langile guztiak zerrendatzen ditu. Zerrenda hutsik badago, mezua inprimatzen du.
     */
    private static void zerrendatuLangileak() {
        if (langileak.isEmpty()) {
            System.out.println("Ez dago langilerik erregistratuta.");
        } else {
            System.out.println("===== Langileen Zerrenda =====");
            for (Langileak l : langileak) {
                System.out.println(l.toString());
            }
        }
    }

    
    /**
     * Langilea kodearen arabera bilatzen du, eta aurkitzen bada haren informazioa inprimatzen du.
     */
    private static void bilatuKodigotik() {
        System.out.print("Sartu langilearen kodea: ");
        String kodea = scanner.nextLine();
        Langileak langilea = bilatuLangileaKodigotik(kodea);
        if (langilea != null) {
            System.out.println(langilea.toString());
        } else {
            System.out.println("Langilea ez da aurkitu.");
        }
    }

    
    /**
     * Emandako kodearen arabera langilea bilatzen du.
     * 
     * @param kodea Langilearen kodea
     * @return Langileak objektua kodea bat badator, edo null bestela
     */
    private static Langileak bilatuLangileaKodigotik(String kodea) {
        for (Langileak l : langileak) {
            if (l.getkodea().equalsIgnoreCase(kodea)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Langileak kontratatu ziren daten tartearen arabera zerrendatzen ditu.
     */
    private static void zerrendatuTartekoDatak() {
        System.out.print("Sartu hasiera data (dd/MM/yyyy): ");
        LocalDate hasiera = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Sartu amaiera data (dd/MM/yyyy): ");
        LocalDate amaiera = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.println("===== " + hasiera + " eta " + amaiera + " artean kontratatutako langileak =====");
        for (Langileak l : langileak) {
            if (!l.getkontratuData().isBefore(hasiera) && !l.getkontratuData().isAfter(amaiera)) {
                System.out.println(l.toString());
            }
        }
    }

    /**
     * Langile berri bat gehitzen du erabiltzaileak emandako informazioaren arabera.
     */
    private static void gehituLangilea() {
        System.out.print("Sartu langilearen kodea: ");
        String kodea = scanner.nextLine();
        if (bilatuLangileaKodigotik(kodea) != null) {
            System.out.println("Kodea existitzen da, ezin da errepikatu.");
            return;
        }

        System.out.print("Abizena: ");
        String abizena = scanner.nextLine();
        System.out.print("Izena: ");
        String izena = scanner.nextLine();
        System.out.print("Kargua: ");
        String kargua = scanner.nextLine();
        System.out.print("Tratamendua: ");
        String tratamendua = scanner.nextLine();
        System.out.print("Jaiotze data (dd/MM/yyyy): ");
        LocalDate jaiotzeData = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Kontratazio data (dd/MM/yyyy): ");
        LocalDate kontratazioData = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Helbidea: ");
        String helbidea = scanner.nextLine();
        System.out.print("Hiria: ");
        String hiria = scanner.nextLine();
        System.out.print("Aktibo dago? (true/false): ");
        boolean aktibo = Boolean.parseBoolean(scanner.nextLine());

        Langileak berria = new Langileak(kodea, abizena, izena, kargua, tratamendua, jaiotzeData, kontratazioData, helbidea, hiria, aktibo);
        langileak.add(berria);
        System.out.println("Langilea ondo gehitu da.");
    }

    /**
     * Langilea kodearen arabera bilatzen du, eta erabiltzaileari baieztapena eskatzen dio 
     * langilea kentzeko.
     */
    private static void kenduLangilea() {
        System.out.print("Sartu kendu nahi duzun langilearen kodea: ");
        String kodea = scanner.nextLine();
        Langileak langilea = bilatuLangileaKodigotik(kodea);

        if (langilea != null) {
            System.out.println("Langilea aurkitu da: " + langilea.toString());
            System.out.print("Ziur zaude langilea kendu nahi duzula? (b/e): ");
            String baieztapena = scanner.nextLine();
            if (baieztapena.equalsIgnoreCase("b")) {
                langileak.remove(langilea);
                System.out.println("Langilea kendu da.");
            } else {
                System.out.println("Eragiketa bertan behera utzi da.");
            }
        } else {
            System.out.println("Langilea ez da aurkitu.");
        }
    }

    /**
     * Langileak abizenaren arabera ordenatzen ditu.
     */
    private static void ordenatuAbizenarenArabera() {
        Collections.sort(langileak, Comparator.comparing(Langileak::getabizena));
        System.out.println("Langileak abizenaren arabera ordenatu dira.");
    }
}
