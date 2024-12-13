package DatuAtzipena7;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class FitxategiHelbidea {
	Scanner sc = new Scanner(System.in);
	Menua menua = new Menua();
	
    public void FitxategiHelbideaBilatu() {
       
    	while (true) {
            System.out.print("Sartu fitxategia dagoen helbidea: ");
            String dirHelbidea = sc.nextLine();

            
            File dir = new File(dirHelbidea);
            if (dir.exists() && dir.isDirectory()) {
            	
            	Datuak datuak = new Datuak();

                List<Enpresa> enpresas = Arrays.asList(
                    datuak.enpresa1,
                    datuak.enpresa2,
                    datuak.enpresa3,
                    datuak.enpresa4
                );
                menua.mostrarMenu(enpresas);
                break;
                
            } else {
                System.out.println("Sartutako direktorioa ez da existitzen edo ez du balio. Sartu berriro");
            }
            
            
        }
    }
}
            
            

