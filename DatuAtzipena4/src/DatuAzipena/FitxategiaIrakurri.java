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
        System.out.println("Sartu fitxategiaren izena eta ibilbidea:");
        String fitxategiIzenbidea = sc.nextLine();

        if (!fitxategiIzenbidea.endsWith(".txt")) {
            fitxategiIzenbidea += ".txt"; 
        }

        File fitxategia = new File(fitxategiIzenbidea);
        
        if (!fitxategia.exists()) {
            System.out.println("Fitxategia ez da existitzen. Sortu nahi duzu? (bai/ez)");
            String erantzuna;
            do {
                erantzuna = sc.nextLine();
                if (!erantzuna.equalsIgnoreCase("bai") && !erantzuna.equalsIgnoreCase("ez")) {
                    System.out.println("Sartu 'bai' edo 'ez' mesedez.");
                }
            } while (!erantzuna.equalsIgnoreCase("bai") && !erantzuna.equalsIgnoreCase("ez"));

            if (erantzuna.equalsIgnoreCase("bai")) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(fitxategia))) {
                    pw.println(VERSION);  
                    System.out.println("Fitxategia sortu da.");
                } catch (IOException e) {
                    System.out.println("Errorea fitxategia sortzean: " + e.getMessage());
                    return null; 
                }
            } else {
                System.out.println("Ez da fitxategirik sortuko.");
                return null;
            }
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
                String lehenLerroa = br.readLine();
                if (!lehenLerroa.equals(VERSION)) {
                    System.out.println("Fitxategiak ez du bertsio zuzena. Bertsioa egiaztatu.");
                    return null; 
                }
            } catch (IOException e) {
                System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
                return null; 
            }
        }

        return fitxategia;
    }
}
