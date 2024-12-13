package DatuAtzipena7;

import java.util.List;
import java.util.Scanner;

public class Menua {

    
    public void mostrarMenu(List<Enpresa> enpresas) {
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
                        
                        metodoak.generarXMLConEmpresas(enpresas);
                        System.out.println(" XML fitxategia sortuta");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Errorea XML fitxategia sortzerarkoan ");
                    }
                    break;
                case 2:
                    try {
                        metodoak.mostrarDesdeXML("datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("XML fitxategia erakusterakoan errorea");
                    }
                    break;
                    
                case 3:
                    try {
                        metodoak.añadirRegistroN("datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("XML fitxategian erregistroa sartzerakoan errora");
                    }
                    break;
                case 4:
                    try {
                        metodoak.buscarRegistroPorCodigo("datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Lagilea kode bidez bilatzerakoan errorea");
                    }
                    break;
                case 5:
                    try {
                        metodoak.buscarRegistrosPorPalabra("datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Lagilea hitz baten bidez bilatzerakoan errorea.");
                    }
                    break;   
                case 6:
                    try {
                        metodoak.ordenarPorAtributo("datuak.xml");
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