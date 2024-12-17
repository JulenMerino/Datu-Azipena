package DatuAtzipena7;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class FitxategiHelbidea {
    Scanner sc = new Scanner(System.in);
    Menua menua = new Menua();
    
    /**
     * Erabiltzaileari fitxategiaren helbidea eskatu eta egiaztatzen dio.
     * Sartutako helbidea zuzena bada, menua bistaratzen du eta enpresen datuak kudeatzen ditu.
     * 
     * Helbidea zuzena ez bada, errore mezu bat erakusten du eta berriro eskatzen du.
     */
    public void FitxategiHelbideaBilatu() {
       
        while (true) {
            System.out.print("Sartu fitxategia dagoen helbidea: ");
            String dirHelbidea = sc.nextLine();

            dirHelbidea = dirHelbidea.replace("\\", "/");

            File dir = new File(dirHelbidea);
            if (dir.exists() && dir.isDirectory()) {
            	
                Datuak datuak = new Datuak();

                List<Enpresa> enpresas = Arrays.asList(
                    datuak.enpresa1,
                    datuak.enpresa2,
                    datuak.enpresa3,
                    datuak.enpresa4
                );
                menua.mostrarMenu(enpresas, dirHelbidea);
                break;

            } else {
                System.out.println("Sartutako direktorioa ez da existitzen edo ez du balio. Sartu berriro");
            }
        }
    }
}
