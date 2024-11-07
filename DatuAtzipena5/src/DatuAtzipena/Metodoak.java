package DatuAtzipena;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Metodoak klasea fitxategiak kudeatzeko metodoez osatuta dago. Klaseak hainbat
 * funtzio eskaintzen ditu, hala nola, fitxategiak betetzea, hutsik uztea,
 * erregistroak bilatzea eta beste hainbat ekintza.
 */
public class Metodoak {
	private static final String VERSION = "Empleados 1.0";

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	static int kodea[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static String izena[] = { "Nancy", "Andrew", "Janet", "Margaret", "Steven", "Michel", "Robert", "Laura", "Anne" };
	static String abizena[] = { "Davolio", "Fuller", "Leverling", "Peacock", "Buchanan", "Suyama", "King", "Callahan",
			"Dodsworth" };
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
	static String hiria[] = { "Seattle", "Tacoma", "Kirkland", "Redmond", "Londres", "Londres", "Londres", "Seattle",
			"Londres" };

	/**
	 * Fitxategia betetzeko metodoa. Erregistro kopurua erabiltzaileari eskatzen dio
	 * eta fitxategian gorde.
	 *
	 * @param fitxategia Fitxategia betetzeko
	 */
	public static void fitxategiaBete(File fitxategia) {
		try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "rw")) {
			raf.writeBytes(VERSION + "\n");
			for (int i = 0; i < kodea.length; i++) {

				// Foramtua eman
				String record = String.format("%-5d;%-20s;%-15s;%-10s;%-15s;%-15s;%-10s;%-20s;%s;\n",
                        kodea[i], izena[i], abizena[i], jaiotzeData[i].format(dateFormatter),
                        kargua[i], tratua[i], kontratuData[i], helbidea[i], hiria[i]);



				raf.writeBytes(record);

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
	 * Fitxategian erregistro bat ezabatzeko metodoa.
	 *
	 * @param fitxategia Ezabatu nahi den fitxategia
	 */
	public static void erregistroaEzabatu(File fitxategia) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Sartu ezabatu nahi duzun erregistroaren kodea:");
		String kodeaEzabatu = sc.nextLine().trim(); // Leer el código del registro a eliminar

		boolean found = false;
		try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "rw")) {

			// Saltar el encabezado
			raf.readLine();

			// Buscar el registro a eliminar
			long posicionInicial = raf.getFilePointer();
			String line;
			while ((line = raf.readLine()) != null) {

				String[] fields = line.split(";");

				if (fields.length >= 9) { // Asegurarse de que hay suficientes campos
					String codigo = fields[0].trim();

					// Comprobar si el código coincide con el que se quiere eliminar
					if (codigo.equals(kodeaEzabatu)) {

						// Realizar borrado lógico
						raf.seek(posicionInicial); // Volver al inicio del registro
						String registroBorrado = String.format("%-5s;%s;%s;%s;%s;%s;%s%s;%s\n", "-" + kodeaEzabatu, fields[1],

								fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8]);

						raf.writeBytes(registroBorrado);
						found = true;
						System.out.println("Erregistroa logikoki ezabatuta.");
						break;

					}

				}

				posicionInicial = raf.getFilePointer(); // Actualizar la posición para el siguiente registro

			}

			if (!found) {

				System.out.println("Ez da aurkitu erregistroa kodearekin: " + kodeaEzabatu);

			}

		} catch (IOException e) {

			System.out.println("Errorea fitxategia irakurtzean edo idaztean: " + e.getMessage());

		}

	}

	public static void ezbatutakoaErakutsi(File fitxategia) {

		try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "r")) {

			// Saltar el encabezado
			raf.readLine();
			boolean found = false;

			String line;
			System.out.println("Ezabatutako erregistroak:");

			while ((line = raf.readLine()) != null) {
				String[] fields = line.split(";");

				if (fields.length >= 9) {
					String kodea = fields[0].trim();

					// Comprobar si el código tiene un guion al inicio
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

								kodea, izena, abizena, jaiotzeData, kargua, tratua, kontratuData,  helbidea, hiria);

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
