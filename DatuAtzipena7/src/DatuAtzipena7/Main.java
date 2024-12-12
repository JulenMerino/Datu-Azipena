package DatuAtzipena7;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Crear una instancia de Datuak, que tiene las empresas y los empleados ya definidos
        Datuak datuak = new Datuak();

        // Crear una lista de empresas
        List<Enpresa> empresas = Arrays.asList(
            datuak.enpresa1,
            datuak.enpresa2,
            datuak.enpresa3,
            datuak.enpresa4
        );

        // Crear una instancia del menú
        Menua menu = new Menua();

        // Llamamos al método mostrarMenu pasando la lista de empresas
        menu.mostrarMenu(empresas);  // Esto pasará la lista de empresas a Menu
    }
}
