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
	    try (PrintWriter pw = new PrintWriter(new FileWriter(fitxategia, false))) { // false -> sobrescribir archivo
	        Scanner sc = new Scanner(System.in);
	        pw.println(VERSION); // Escribir la versión en la primera línea del archivo
	        int kopurua = 0;
	        
	        // Pedir al usuario cuántos registros quiere agregar
	        while (kopurua <= 0) {
	            System.out.println("Zenbat erregistro sartu nahi dituzu?");
	            if (sc.hasNextInt()) {
	                kopurua = sc.nextInt();
	            } else {
	                sc.next(); // Limpiar el buffer del Scanner
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
	        System.out.println("Errorea fitxategia betetzean: " + e.getMessage());
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
	    
	    if (fitxategia.length() == 0) {
	        System.out.println("Fitxategia jada hutsik dago.");
	        return;
	    }

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
	        String lehenLerroa = br.readLine();
	        
	        if (lehenLerroa != null && lehenLerroa.equals(VERSION)) {
	            lerroKopurua = 1; 
	        }

	        String lerroa;
	        while ((lerroa = br.readLine()) != null) {
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
	    sc.nextLine();  

	    try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
	        String lerroa;
	        int lerroKonta = 0;
	        boolean aurkitua = false;
	        
	        while ((lerroa = br.readLine()) != null) {
	            lerroKonta++;

	            if (lerroKonta == 1) continue;

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
	        int lerroKonta = 0;
	        boolean aurkitua = false;
	        
	        while ((lerroa = br.readLine()) != null) {
	            lerroKonta++;

	            if (lerroa.toLowerCase().contains(testua.toLowerCase())) {
	                System.out.println("Erregistroa aurkitu da (lerroa " + lerroKonta + "): " + lerroa);
	                aurkitua = true;
	            }
	        }
	        
	        if (!aurkitua) {
	            System.out.println("Ez da erregistroa aurkitu.");
	        }
	    } catch (IOException e) {
	        System.out.println("Errorea erregistroa bilatzean.");
	    } finally {
	        sc.close();
	    }
	}

    /**
     * Fitxategira erregistro berri bat gehitzen du.
     *
     * @param fitxategia Erregistroa gehituko den fitxategia
     */
	public static void erregistroaGehitu(File fitxategia) {
	    try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
	        // Lee todas las líneas del archivo y verifica si el código ya existe
	        Set<Integer> existitzenKodeak = new HashSet<>();
	        String lerroa;
	        while ((lerroa = br.readLine()) != null) {
	            String[] erregistroak = lerroa.split(";");
	            if (erregistroak.length > 0) {
	                try {
	                    int kodea = Integer.parseInt(erregistroak[0]);
	                    existitzenKodeak.add(kodea);
	                } catch (NumberFormatException e) {
	                    // Ignorar cualquier línea que no tenga un código válido en el primer campo
	                    continue;
	                }
	            }
	        }

	        // Pedir el nuevo registro
	        String erregistroa = sartuErregistroa(fitxategia);
	        String[] datos = erregistroa.split(";");
	        int nuevoCodigo = Integer.parseInt(datos[0]);

	        if (existitzenKodeak.contains(nuevoCodigo)) {
	            System.out.println("Errorea: Kodea dagoeneko existitzen da.");
	        } else {
	            try (PrintWriter pw = new PrintWriter(new FileWriter(fitxategia, true))) {
	                pw.println(erregistroa);
	                System.out.println("Erregistroa gehitu da.");
	            } catch (IOException e) {
	                System.out.println("Errorea erregistroa gehitzean.");
	            }
	        }

	    } catch (IOException e) {
	        System.out.println("Errorea fitxategia irakurtzean.");
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

	            if (line.startsWith(kodeaEzabatu + ";")) {
	                System.out.println("Aurkitutako erregistroa: " + line);
	                System.out.println("Ziur ezabatu nahi duzula? (bai/ez)");

	                String confirmation = sc.nextLine().trim().toLowerCase();

	                if (confirmation.equals("bai")) {
	                    aurkituta = true; 
	                    System.out.println("Erregistroa ezabatuko da.");
	                } else if (confirmation.equals("ez")) {
	                    
	                    writer.write(line);
	                    writer.newLine();
	                } else {
	                    System.out.println("Sartu 'bai' edo 'ez' mesedez.");
	                    
	                    continue;
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

	    if (fitxategiAuxiliar.exists()) {
	       
	        if (fitxategiAuxiliar.renameTo(fitxategia)) {
	            System.out.println("Fitxategia berriz izendatua: " + fitxategia.getName());
	        } else {
	            System.out.println("Errorea fitxategia berriz izendatzean.");
	        }
	    } else {
	        System.out.println("Errorea: fitxategi auxiliarra ez da sortu.");
	    }
	    sc.close();
	}


    /**
     * Fitxategiaren kopia bat sortzeko metodoa.
     *
     * @param fitxategia Kopia sortuko den fitxategia
     */
	public static void fitxategiarenKopia(File fitxategia) {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Sartu kopiatutako fitxategiaren izena (adibidez, kopia_fitxategi.txt): ");
	    String destinoNombre = sc.nextLine().trim();

	    File destino = new File(destinoNombre);
	    if (destino.exists()) {
	        System.out.println("Errorea: " + destinoNombre + " dagoeneko existitzen da.");
	        System.out.println("Desio al duzu fitxategiaren izena aldatzea? (bai/ez)");
	        String respuesta = sc.nextLine().trim().toLowerCase();

	        if (respuesta.equals("bai")) {
	            System.out.println("Sartu fitxategiaren izen berria:");
	            destinoNombre = sc.nextLine().trim();
	            destino = new File(destinoNombre);
	        } else {
	            System.out.println("Operazioa bertan behera uzten da.");
	            sc.close();
	            return;
	        }
	    }

	    try (InputStream in = new FileInputStream(fitxategia);
	         OutputStream out = new FileOutputStream(destino)) {

	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = in.read(buffer)) > 0) {
	            out.write(buffer, 0, length);
	        }
	        System.out.println("Fitxategiaren kopia sortu da: " + destinoNombre);
	    } catch (IOException e) {
	        System.out.println("Errorea fitxategiaren kopia sortzean: " + e.getMessage());
	    } finally {
	        sc.close();  
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
	                System.out.println("Aurkitutako erregistroa: " + lerroa);
	                String nuevoRegistro = sartuErregistroa(fitxategia);
	                pw.println(nuevoRegistro); 
	                aurkitua = true;
	            } else {
	                pw.println(lerroa); 
	            }
	        }

	        if (!aurkitua) {
	            System.out.println("Ez da erregistroa aurkitu kode honekin: " + kodea);
	            irakurritakoFitxategia.delete();
	        }

	    } catch (IOException e) {
	        System.out.println("Errorea fitxategia irakurtzean edo idaztean.");
	        e.printStackTrace();
	    }

	    if (irakurritakoFitxategia.exists()) {
	        if (!fitxategia.delete()) {
	            System.out.println("Errorea fitxategia ezabatu zenean.");
	        } else if (!irakurritakoFitxategia.renameTo(fitxategia)) {
	            System.out.println("Errorea fitxategia berriz izendatzerakoan.");
	        } else {
	            System.out.println("Erregistroa aldatu da.");
	        }
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
	        br.readLine(); 
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
	            int intentos = 0;
	            while (existitzenKodeak.contains(kodea)) {
	                kodea++; 
	                intentos++;
	                if (intentos > 100) { 
	                    System.out.println("Ez da posible kodea aurkitzea. Saiatu berriro.");
	                    return null; 
	                }
	            }
	        } else {
	            sc.next();
	            System.out.println("Kodea zenbaki positiboa izan behar da.");
	        }
	    }
	    sc.nextLine(); 

	    // Pedir nombre
	    String izena;
	    while (true) {
	        System.out.print("Sartu erregistroaren izena (letrak bakarrik): ");
	        izena = sc.nextLine();
	        if (izena.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) { 
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
	        if (herria.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) { 
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
