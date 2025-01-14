package DatuAtzipena;

import java.util.Scanner;

public class DatuBaseAukera {

    /**
     * Erabiltzaileari datu-base bat aukeratzeko aukera ematen dio. 
     * Aukera egiten den datu-basearekin konektatzen da eta hurrengo menuan 
     * aurkitzen diren ekintzak exekutatzen ditu (INSERT, DELETE, SELECT).
     */
    public static void DatuBaseAukeratu() {
        SQLiteDB sqlite = new SQLiteDB();
        AccessDB access = new AccessDB();
        MySQLDB mysql = new MySQLDB();

        Scanner scanner = new Scanner(System.in);
        int aukera;

        do {
            System.out.println("======================");
            System.out.println("  Aukeratu Datu Basearen Modeloa  ");
            System.out.println("======================");
            System.out.println("1. SQLite");
            System.out.println("2. Access");
            System.out.println("3. MySQL");
            System.out.println("0. Irten");
            System.out.print("Sartu zure aukera: ");

            aukera = scanner.nextInt();

            switch (aukera) {
                case 1:
                    System.out.println("Aukeratu duzu SQLite.");
                    sqlite.konektatu();  
                    erakutsiAukeraMenua("SQLite", sqlite, access, mysql);
                    break;
                case 2:
                    System.out.println("Aukeratu duzu Access.");
                    access.konektatu();  
                    erakutsiAukeraMenua("Access", sqlite, access, mysql);
                    break;
                case 3:
                    System.out.println("Aukeratu duzu MySQL.");
                    mysql.konektatu();  
                    erakutsiAukeraMenua("MySQL", sqlite, access, mysql);
                    break;
                case 0:
                    System.out.println("Programatik irteten...");
                    break;
                default:
                    System.out.println("Sartutako aukera ez da zuzena, sartu berriro.");
            }
        } while (aukera != 0);
    }

    /**
     * Datu-basearen ekintzen menua erakusten du, non erabiltzaileak aukeratu 
     * dezakeen ekintza bat (INSERT, DELETE, SELECT, edo datu-basea aldatzea).
     * 
     * @param datuBasea Aukeratutako datu-basearen izena (SQLite, Access, MySQL)
     * @param sqlite SQLite datu-basearekin lotutako objektua
     * @param access Access datu-basearekin lotutako objektua
     * @param mysql MySQL datu-basearekin lotutako objektua
     */
    public static void erakutsiAukeraMenua(String datuBasea, SQLiteDB sqlite, AccessDB access, MySQLDB mysql) {
        Scanner scanner = new Scanner(System.in);
        int aukera;

        do {
            System.out.println("======================");
            System.out.println("  " + datuBasea + " datu basearen aukerak");
            System.out.println("======================");
            System.out.println("1. Aldatu datu base modeloa");
            System.out.println("2. INSERT egin");
            System.out.println("3. DELETE egin");
            System.out.println("4. SELECT egin");
            System.out.println("0. Irten");
            System.out.print("Sartu zure aukera: ");

            aukera = scanner.nextInt();

            switch (aukera) {
                case 1:
                    System.out.println( datuBasea + " modeloa aldatzen...");
                    if (datuBasea.equals("SQLite")) {
                        sqlite.itxi();
                    } else if (datuBasea.equals("Access")) {
                        access.itxi();
                    } else if (datuBasea.equals("MySQL")) {
                        mysql.itxi();
                    }
                    DatuBaseAukeratu();
                    break;
                case 2:
                    System.out.println(datuBasea + " modeloan INSERT-ak egiten...");
                    if (datuBasea.equals("SQLite")) {
                        sqlite.datuenInsert();
                    } else if (datuBasea.equals("Access")) {
                        access.datuenInsert();
                    } else if (datuBasea.equals("MySQL")) {
                        mysql.datuenInsert();
                    }
                    break;
                case 3:
                    System.out.println(datuBasea + " modeloan DELETE-ak egiten...");
                    if (datuBasea.equals("SQLite")) {
                        sqlite.datuenDelete();
                    } else if (datuBasea.equals("Access")) {
                        access.datuenDelete();
                    } else if (datuBasea.equals("MySQL")) {
                        mysql.datuenDelete();
                    }
                    break;
                case 4:
                    System.out.println(datuBasea + " modeloan SELECT-ak egiten...");
                    if (datuBasea.equals("SQLite")) {
                        sqlite.datuenSelect();
                    } else if (datuBasea.equals("Access")) {
                        access.datuenSelect();
                    } else if (datuBasea.equals("MySQL")) {
                        mysql.datuenSelect();
                    }
                    break;
                case 0:
                    System.out.println("Programatik irteten...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Sartutako aukera ez da zuzena, sartu berriro.");
            }
        } while (aukera != 0);
    }
}
