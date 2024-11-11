package DatuAtzipena;

import java.io.*;
import java.util.Scanner;

/**
 * {@code FitxategiaIrakurri} klaseak fitxategiak irakurtzeko eta sortzeko funtzionalitatea eskaintzen du.
 * Lehenengo lerroan fitxategiaren bertsioa gordetzen du eta bertsio egokia ez badu, fitxategia ez da onartzen.
 * 
 * <p>Klase honek erabiltzaileari fitxategiaren izena eta ibilbidea eskatzen dio, eta fitxategia existitzen ez bada,
 * sortzeko aukera ematen dio.</p>
 * 
 */
public class FitxategiaIrakurri {

    // Fitxategiaren bertsioaren konstantea
    private static final String VERSION = "Empleados 1.0"; // La versión esperada del archivo

    /**
     * Fitxategiaren izena eta ibilbidea erabiltzailetik eskatzen du, eta fitxategia irakurtzen du.
     * 
     * <p>Fitxategia ez bada existitzen, erabiltzaileari sortzeko aukera eskaintzen zaio. 
     * Fitxategiak bertsio egokia badu, fitxategiaren objektua itzultzen du, bestela {@code null} itzultzen du.</p>
     * 
     * @return {@code File} objektua, fitxategia ondo sortu edo irakurri bada, edo {@code null} errore baten kasuan.
     */
    public static File irakurriFitxategia() {
        // Scanner objektua sortzen da erabiltzailearen sarrerak jasotzeko
        Scanner sc = new Scanner(System.in);
        
        // Erabiltzaileari fitxategiaren izena eta ibilbidea sartzeko eskatzen dio
        System.out.println("Sartu fitxategiaren izena eta ibilbidea:");
        String fitxategiIzenbidea = sc.nextLine();

        // Fitxategiaren objektua sortzen da jasotako ibilbidearekin
        File fitxategia = new File(fitxategiIzenbidea);

        // Fitxategia ez badago, erabiltzaileari sortzeko aukera eskaintzen zaio
        if (!fitxategia.exists()) {
            System.out.println("Fitxategia ez da existitzen. Sortu nahi duzu? (bai/ez)");
            String erantzuna;
            do {
                // Erabiltzailearen erantzuna jasotzen da
                erantzuna = sc.nextLine();
                if (!erantzuna.equalsIgnoreCase("bai") && !erantzuna.equalsIgnoreCase("ez")) {
                    // Erantzun okerrak sartutakoan berriro eskatzen da
                    System.out.println("Sartu 'bai' edo 'ez' mesedez.");
                }
            } while (!erantzuna.equalsIgnoreCase("bai") && !erantzuna.equalsIgnoreCase("ez"));

            // Erabiltzaileak 'bai' aukeratzen badu, fitxategia sortzen da
            if (erantzuna.equalsIgnoreCase("bai")) {
                try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "rw")) {
                    // Gorde bertsioa lehen lerroan
                    raf.writeBytes(VERSION + System.lineSeparator());
                    System.out.println("Fitxategia sortu da.");
                } catch (IOException e) {
                    System.out.println("Errorea fitxategia sortzean.");
                    return null; // Errorea gertatzen bada, null itzultzen du
                }
            } else {
                // Erabiltzaileak 'ez' aukeratzen badu, fitxategia ez da sortzen eta null itzultzen da
                System.out.println("Ez da fitxategirik sortuko.");
                return null;
            }
        } else {
            // Fitxategia existitzen bada, lehen lerroan bertsioa egiaztatzen da
            try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "r")) {
                // Lehen lerroa irakurri eta bertsioa egiaztatu
                String lehenLerroa = raf.readLine();
                if (!lehenLerroa.equals(VERSION)) {
                    System.out.println("Fitxategiak ez du bertsio zuzena.");
                    return null; // Bertsioa okerra bada, null itzultzen da
                }
            } catch (IOException e) {
                System.out.println("Errorea fitxategia irakurtzean.");
                return null; // Irakurketan errorea izanez gero, null itzultzen da
            }
        }

        // Fitxategia ondo sortu edo irakurri bada, fitxategiaren objektua itzultzen da
        return fitxategia;
    }
}