package DatuAzipena;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Metodoak klasea fitxategiak kudeatzeko metodoez osatuta dago.
 * Klaseak hainbat funtzio eskaintzen ditu, hala nola,
 * fitxategiak betetzea, hutsik uztea, erregistroak bilatzea
 * eta beste hainbat ekintza.
 */
public class Metodoak {
	private static final String VERSION = "Ikasleak 1.0";

    /**
     * Fitxategia betetzeko metodoa.
     * Erregistro kopurua erabiltzaileari eskatzen dio
     * eta fitxategian gorde.
     *
     * @param fitxategia Fitxategia betetzeko
     */
	public static void fitxategiaBete(File fitxategia) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(fitxategia, false))) {
			Scanner sc = new Scanner(System.in);
			pw.println(VERSION);
			int kopurua = 0;
			while (kopurua <= 0) {
				System.out.println("Zenbat erregistro sartu nahi dituzu?");
				if (sc.hasNextInt()) {
					kopurua = sc.nextInt();
				} else {
					sc.next();
					System.out.println("Sartu zenbaki bat.");
				}
			}
			sc.nextLine();
			for (int i = 0; i < kopurua; i++) {
				String erregistroa = sartuErregistroa(fitxategia);
				pw.println(erregistroa);
			}
			System.out.println("Fitxategia eguneratu da.");
		} catch (IOException e) {
			System.out.println("Errorea fitxategia betetzean.");
		}
	}

    /**
     * Fitxategia hutsik uzteko metodoa.
     * Erabiltzaileari baieztapena eskatzen dio
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
     * Fitxategiko erregistro kopurua zenbatzen du.
     *
     * @param fitxategia Zenbatuko den fitxategia
     */
	public static void erregistroKopurua(File fitxategia) {
		try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
			int lerroKopurua = 0;
			while (br.readLine() != null) {
				lerroKopurua++;
			}
			System.out.println("Fitxategiak " + (lerroKopurua - 1) + " erregistro ditu.");
		} catch (IOException e) {
			System.out.println("Errorea erregistroak zenbatzean.");
		}
	}

    /**
     * Fitxategian erregistro bat bilatzen du
     * kodea erabiliz.
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

		try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
			String lerroa;
			int lerroKonta = 0;
			boolean aurkitua = false;
			while ((lerroa = br.readLine()) != null) {
				lerroKonta++;
				if (lerroKonta > 1 && lerroa.startsWith(String.valueOf(kodea))) {
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
			int lerroKonta = 0;
			boolean aurkitua = false;
			while ((lerroa = br.readLine()) != null) {
				lerroKonta++;
				if (lerroa.contains(testua)) {
					System.out.println("Erregistroa aurkitu da (lerroa " + lerroKonta + "): " + lerroa);
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
     * Fitxategira erregistro berri bat gehitzen du.
     *
     * @param fitxategia Erregistroa gehituko den fitxategia
     */
	public static void erregistroaGehitu(File fitxategia) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(fitxategia, true))) {
			String erregistroa = sartuErregistroa(fitxategia);
			pw.println(erregistroa);
			System.out.println("Erregistroa gehitu da.");
		} catch (IOException e) {
			System.out.println("Errorea erregistroa gehitzean.");
		}
	}

    /**
     * Fitxategian erregistro bat ezabatzeko metodoa.
     *
     * @param fitxategia Ezabatu nahi den fitxategia
     */
	public static void erregistroaEzabatu(File fitxategia) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Sartu ezabatu nahi duzun Erregistroa (kodea): ");

		String kodeaEzabatu = sc.nextLine();

		File fitxategiAuxiliar = new File(fitxategia.getParent(), "auxiliar_" + fitxategia.getName());

		try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
				BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategiAuxiliar))) {

			String line;

			boolean aurkituta = false;

			while ((line = reader.readLine()) != null) {

				// Konprobatu sartutako kodea
				if (line.startsWith(kodeaEzabatu + ";")) {
					System.out.println("Aurkitutako erregistroa: " + line);
					System.out.println("Ziur ezabatu nahi duzula? (bai/ez)");

					String confirmation = sc.nextLine();

					if (confirmation.equalsIgnoreCase("bai")) {
						aurkituta = true; // Erregistroa aurkitu bada markatu
						System.out.println("Iritzia ezabatuko da.");
					} else {
						writer.write(line);
						writer.newLine();
					}
				} else {
					writer.write(line);
					writer.newLine();
				}
			}

			if (!aurkituta) {
				System.out.println("Ez da aurkitu erregistroa kodearekin: " + kodeaEzabatu);
			}

		} catch (IOException e) {
			System.out.println("Errorea fitxategia irakurtzean edo idaztean: " + e.getMessage());
			return;
		}

		if (fitxategia.delete()) {
			System.out.println("Fitxategia jadanik ezabatuta.");
		} else {
			System.out.println("Errorea fitxategia ezabatzean.");
			return;
		}

		if (fitxategiAuxiliar.renameTo(fitxategia)) {
			System.out.println("Fitxategia berriz izendatua: " + fitxategia.getName());
		} else {
			System.out.println("Errorea fitxategia berriz izendatzean.");
		}
	}

    /**
     * Fitxategiaren kopia bat sortzeko metodoa.
     *
     * @param fitxategia Kopia sortuko den fitxategia
     */
	public static void fitxategiarenKopia(File fitxategia) {
		try (InputStream in = new FileInputStream(fitxategia);
				OutputStream out = new FileOutputStream("kopia_" + fitxategia.getName())) {

			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			System.out.println("Fitxategiaren kopia sortu da.");
		} catch (IOException e) {
			System.out.println("Errorea fitxategiaren kopia sortzean.");
		}
	}

    /**
     * Fitxategiko erregistro bat aldatzeko metodoa.
     *
     * @param fitxategia Aldatu nahi den fitxategia
     */
	public static void erregistroaAldatu(File fitxategia) {
		Scanner sc = new Scanner(System.in);
		int kodea = -1;

		// Solicitar el código del registro a modificar
		while (kodea < 0) {
			System.out.println("Sartu aldatu nahi duzun erregistroaren kodea:");
			if (sc.hasNextInt()) {
				kodea = sc.nextInt();
			} else {
				sc.next(); // Consumir entrada inválida
				System.out.println("Sartu zenbaki positibo bat.");
			}
		}
		sc.nextLine(); // Consumir el salto de línea después del nextInt

		File irakurritakoFitxategia = new File("temp.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(fitxategia));
				PrintWriter pw = new PrintWriter(new FileWriter(irakurritakoFitxategia))) {

			String lerroa;
			boolean aurkitua = false;
			while ((lerroa = br.readLine()) != null) {
				if (lerroa.startsWith(String.valueOf(kodea))) {
					System.out.println("Aurkitutako erregistroa: " + lerroa);

					// Llamar a sartuErregistroa para obtener el nuevo registro
					String nuevoRegistro = sartuErregistroa(fitxategia);
					pw.println(nuevoRegistro); // Escribir el nuevo registro en el archivo temporal
					aurkitua = true;
				} else {
					pw.println(lerroa); // Escribir la línea sin cambios
				}
			}

			if (!aurkitua) {
				System.out.println("Ez da erregistroa aurkitu kode honekin: " + kodea);
			}
		} catch (IOException e) {
			System.out.println("Errorea fitxategia irakurtzean edo idaztean.");
		}

		// Reemplazar el archivo original con el temporal
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
     *
     * @param fitxategia Erregistroa gorde nahi den fitxategia
     * @return Sartutako erregistroaren stringa
     */
	private static String sartuErregistroa(File fitxategia) {
		Scanner sc = new Scanner(System.in);
		int kodea = -1;

		Set<Integer> existitzenKodeak = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
			br.readLine(); // Saltar la primera línea (versión)
			String lerroa;
			while ((lerroa = br.readLine()) != null) {
				String[] erregistroak = lerroa.split(";");
				existitzenKodeak.add(Integer.parseInt(erregistroak[0]));
			}
		} catch (IOException e) {
			System.out.println("Errorea fitxategia irakurtzean.");
		}

		// Pedir código
		while (kodea < 0) {
			System.out.print("Sartu erregistroaren kodea (zenbaki positiboa): ");
			if (sc.hasNextInt()) {
				kodea = sc.nextInt();
				// Verificar si el código ya existe
				while (existitzenKodeak.contains(kodea)) {
					kodea++; // Incrementar el código hasta encontrar uno que no exista
				}
			} else {
				sc.next();
				System.out.println("Kodea zenbaki positiboa izan behar da.");
			}
		}
		sc.nextLine(); // Consumir el salto de línea

		// Pedir nombre
		String izena;
		while (true) {
			System.out.print("Sartu erregistroaren izena (letrak bakarrik): ");
			izena = sc.nextLine();
			if (izena.matches("[a-zA-Z]+")) {
				break;
			} else {
				System.out.println("Izena letrak bakarrik izan behar dira.");
			}
		}

		// Pedir pueblo
		String herria;
		while (true) {
			System.out.print("Sartu erregistroaren herria (letrak bakarrik): ");
			herria = sc.nextLine();
			if (herria.matches("[a-zA-Z]+")) {
				break;
			} else {
				System.out.println("Herria letrak bakarrik izan behar dira.");
			}
		}

		// Pedir matrícula
		String matrikulatua;
		while (true) {
			System.out.print("Sartu erregistroaren matrikulatua (letrak eta zenbakiak): ");
			matrikulatua = sc.nextLine();
			if (matrikulatua.matches("[a-zA-Z0-9]+")) {
				break;
			} else {
				System.out.println("Matrikulatua letrak eta zenbakiak izan behar dira.");
			}
		}

		return kodea + ";" + izena + ";" + herria + ";" + matrikulatua;
	}
}
