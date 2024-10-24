package DatuAzipena;

import java.io.*;
import java.util.Scanner;

/**
 * Fitxategiak irakurtzeko eta sortzeko klasea.
 * Fitxategia existitzen ez bada, sortzeko aukera ematen du eta bertsio bat egiaztatzen du.
 */
public class FitxategiaIrakurri {
    // Fitxategiaren bertsioa gordetzen duen konstantea.
    private static final String VERSION = "Ikasleak 1.0";

    /**
     * Fitxategia irakurri edo sortzen du, fitxategiaren bertsioa egiaztatuz.
     * 
     * @return Fitxategia ondo irakurri bada edo sortu bada, File objektu bat itzultzen du.
     *         Fitxategirik ez badago edo bertsioa okerra bada, null itzultzen du.
     */
    public static File irakurriFitxategia() {
        Scanner sc = new Scanner(System.in);
        // Erabiltzaileari fitxategiaren izena eta ibilbidea sartzeko eskatzen dio.
        System.out.println("Sartu fitxategiaren izena eta ibilbidea:");
        String fitxategiIzenbidea = sc.nextLine();

        File fitxategia = new File(fitxategiIzenbidea);
        
        // Fitxategia ez badago, erabiltzaileari sortzeko aukera eskaintzen zaio.
        if (!fitxategia.exists()) {
            System.out.println("Fitxategia ez da existitzen. Sortu nahi duzu? (bai/ez)");
            String erantzuna;
            do {
                erantzuna = sc.nextLine();
                if (!erantzuna.equalsIgnoreCase("bai") && !erantzuna.equalsIgnoreCase("ez")) {
                    System.out.println("Sartu 'bai' edo 'ez' mesedez.");
                }
            } while (!erantzuna.equalsIgnoreCase("bai") && !erantzuna.equalsIgnoreCase("ez"));

            // Erabiltzaileak 'bai' aukeratzen badu, fitxategia sortzen da.
            if (erantzuna.equalsIgnoreCase("bai")) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(fitxategia))) {
                    pw.println(VERSION);
                    System.out.println("Fitxategia sortu da.");
                } catch (IOException e) {
                    System.out.println("Errorea fitxategia sortzean.");
                    return null; // Errorea gertatzen bada, null itzultzen du
                }
            } else {
                // Erabiltzaileak 'ez' aukeratzen badu, fitxategia ez da sortzen eta null itzultzen da.
                System.out.println("Ez da fitxategirik sortuko.");
                return null;
            }
        } else {
            // Fitxategia existitzen bada, lehen lerroan bertsioa egiaztatzen da.
            try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
                String lehenLerroa = br.readLine();
                if (!lehenLerroa.equals(VERSION)) {
                    System.out.println("Fitxategiak ez du bertsio zuzena.");
                    return null; // Bertsioa okerra bada, null itzultzen da
                }
            } catch (IOException e) {
                System.out.println("Errorea fitxategia irakurtzean.");
                return null; // Irakurketan errorea izanez gero, null itzultzen da
            }
        }

        // Fitxategia ondo sortu edo irakurri bada, fitxategiaren objektua itzultzen da.
        return fitxategia;
    }
}
