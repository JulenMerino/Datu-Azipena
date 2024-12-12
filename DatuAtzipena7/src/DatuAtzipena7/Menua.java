package DatuAtzipena7;

import java.util.List;
import java.util.Scanner;

public class Menua {

    // Método para mostrar el menú y gestionar las opciones
    public void mostrarMenu(List<Enpresa> enpresas) {
        Scanner scanner = new Scanner(System.in);
        Metodoak metodoak = new Metodoak(); // Instanciamos Metodoak para usar su método desde aquí

        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Generar archivo XML con la información de todas las empresas");
            System.out.println("2. Generar archivo tabulado con la información de todas las empresas");
            System.out.println("3. Crear un trabajador y meterlo en el archivo");
            System.out.println("4. Buscar un trabajador por codigo");
            System.out.println("5. Buscar un trabajador por palabra");
            System.out.println("6. Ordenar por atributo");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            // Capturamos la opción del usuario
            int opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    try {
                        // Llamamos al método de Metodoak para generar el XML con todas las empresas
                        metodoak.generarXMLConEmpresas(enpresas);
                        System.out.println("Archivo XML generado exitosamente.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Hubo un error al generar el archivo XML.");
                    }
                    break;
                case 2:
                    try {
                        // Leer y mostrar la información desde el archivo XML
                        metodoak.mostrarDesdeXML("datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error al mostrar la información desde el archivo XML.");
                    }
                    break;
                    
                case 3:
                    try {
                        // Leer y mostrar la información desde el archivo XML
                        metodoak.añadirRegistroN("datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error al mostrar la información desde el archivo XML.");
                    }
                    break;
                case 4:
                    try {
                        // Leer y mostrar la información desde el archivo XML
                        metodoak.buscarRegistroPorCodigo("datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error al mostrar la información desde el archivo XML.");
                    }
                    break;
                case 5:
                    try {
                        // Leer y mostrar la información desde el archivo XML
                        metodoak.buscarRegistrosPorPalabra("datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error al mostrar la información desde el archivo XML.");
                    }
                    break;   
                case 6:
                    try {
                        // Leer y mostrar la información desde el archivo XML
                        metodoak.ordenarPorAtributo("datuak.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error al mostrar la información desde el archivo XML.");
                    }
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }
}