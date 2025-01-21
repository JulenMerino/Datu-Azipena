package DatuAtzipena;

import java.util.Scanner;

public class Menua {

    /**
     * Menuko aukerak erakusten ditu eta erabiltzaileak aukera bat hautatzen du.
     * Hautatutako aukeraren arabera, dagozkion datu-baseen arteko transferentziak
     * edo eguneraketak burutzen dira.
     * 
     * Aukeratutako aukera:
     * 1. SQLite-ko datuak Access-era pasatzea.
     * 2. Langilearen kargua eguneratzea.
     * 3. Access-en aldatutako karguak eguneratzea.
     * 4. Langile berri bat Access-en gehitzea.
     * 5. Access-en sortutako langilea SQLite-ra pasatzea.
     * 6. Aplikaziotik irten.
     */
    public static void Menua() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean irten = false;

            while (!irten) {
                System.out.println("=========");
                System.out.println("  Menua  ");
                System.out.println("=========");
                System.out.println("1. SQLite-ko datuak Access-era pasa");
                System.out.println("2. Langile tablan kargua aldatu");
                System.out.println("3. Eguneratu Access aldatutako karguekin");
                System.out.println("4. Langile taulan gehitu erregistro bat");
                System.out.println("5. Access-en sortutako langilea SQLite-ra pasa");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                
                int aukera = scanner.nextInt();
                scanner.nextLine(); 

                switch (aukera) {
                    case 1:
                        DatuakEguneratu.SQLiteToAccess();
                        break;
                    case 2:
                        DatuakEguneratu.EguneratuKargua();
                        break;
                    case 3:
                        DatuakEguneratu.MysqlToAccess();
                        break;
                    case 4:
                        DatuakEguneratu.AccessenLangileaSortu();
                        break;
                    case 5:
                        DatuakEguneratu.AccessToSQLite();
                        break;
                    case 6:
                        irten = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
