package DatuAtzipena7;

import java.util.List;
import java.util.Scanner;

public class Menua {

    /**
     * Menua erakusten du eta erabiltzaileak aukeratutako ekintza exekutatzen du.
     * Programak zazpi aukera eskaintzen ditu: 
     * 1. Enpresa guztien informazioa XML batean sortu,
     * 2. Enpresen informazioa XML fitxategian erakutsi,
     * 3. Langile bat sortu eta fitxategian sartu,
     * 4. Langilea kodea erabiliz bilatu,
     * 5. Langilea hitz bat erabiliz bilatu,
     * 6. Langileak atributu baten arabera zerrendatu,
     * 7. Programatik irten.
     * 
     * @param enpresas Enpresa objektuen zerrenda, menuko aukerak exekutatzeko.
     * @param dirHelbidea Fitxategien direktorioaren helbidea.
     */
    public void mostrarMenu(List<Enpresa> enpresas, String dirHelbidea) {
        Scanner sc = new Scanner(System.in);
        Metodoak metodoak = new Metodoak(); 

        boolean irten = false;
        while (!irten) {
            System.out.println("\nMenua:");
            System.out.println("1. Enpresa guztien informazioa xml batean sortu");
            System.out.println("2. Idatzi enpresen informazioa");
            System.out.println("3. Sortu langile bat eta sartu fitxategian");
            System.out.println("4. Bilatu langilea kodea erabiliz");
            System.out.println("5. Bilatu langilea hitz bat erabiliz");
            System.out.println("6. Zerrendatu atributu baten arabera");
            System.out.println("7. Irten");
            System.out.print("Sartu aukera bat: ");
            
            int aukera = sc.nextInt();
            
            switch (aukera) {
                case 1:
                    try {
                        metodoak.sortuEnpresenXMLa(enpresas, dirHelbidea);
                        System.out.println(" XML fitxategia sortuta");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Errorea XML fitxategia sortzerarkoan ");
                    }
                    break;
                case 2:
                    try {
                        metodoak.XMLaErabilizErakutsi(dirHelbidea + "/datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("XML fitxategia erakusterakoan errorea");
                    }
                    break;
                    
                case 3:
                    try {
                        metodoak.SortuLangilea(dirHelbidea + "/datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("XML fitxategian erregistroa sartzerakoan errora");
                    }
                    break;
                case 4:
                    try {
                        metodoak.bilatuLangileaKodeBidez(dirHelbidea + "/datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Langilea kode bidez bilatzerakoan errorea");
                    }
                    break;
                case 5:
                    try {
                        metodoak.bilatuLangileaHitzBatenBidez(dirHelbidea + "/datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Langilea hitz baten bidez bilatzerakoan errorea.");
                    }
                    break;   
                case 6:
                    try {
                        metodoak.ordenarPorAtributo(dirHelbidea + "/datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Langileak zerrendatzean errorea");
                    }
                    break;
                case 7:
                    System.out.println("Programatik irteten");
                    irten = true;
                    break;
                default:
                    System.out.println("Balio gabeko aukera");
            }
        }
    }
}
