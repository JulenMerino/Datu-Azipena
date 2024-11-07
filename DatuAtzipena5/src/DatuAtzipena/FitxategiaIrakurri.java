package DatuAtzipena;

import java.io.*;
import java.util.Scanner;

public class FitxategiaIrakurri {

	private static final String VERSION = "Empleados 1.0"; // La versión esperada del archivo

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
				try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "rw")) {
					// Gorde bertsioa lehen lerroan
					raf.writeBytes(VERSION + System.lineSeparator());
					System.out.println("Fitxategia sortu da.");
				} catch (IOException e) {
					System.out.println("Errorea fitxategia sortzean.");
					return null; // Errorea gertatzen bada, null itzultzen du
				}
			} else {
				// Erabiltzaileak 'ez' aukeratzen badu, fitxategia ez da sortzen eta null
				// itzultzen da.
				System.out.println("Ez da fitxategirik sortuko.");
				return null;
			}
		} else {
			// Fitxategia existitzen bada, lehen lerroan bertsioa egiaztatzen da.
			try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "r")) {
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

		// Fitxategia ondo sortu edo irakurri bada, fitxategiaren objektua itzultzen da.
		return fitxategia;
	}

	
}
