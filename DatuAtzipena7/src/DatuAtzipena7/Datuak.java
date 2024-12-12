package DatuAtzipena7;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Datuak{
	
	Langileak langile1 = new Langileak("1", "Davolio", "Nancy", "Representante de ventas", "Stra.", 
            LocalDate.of(1968, 12, 8), LocalDate.of(1992, 5, 1), "507-20th Ave.E.", "Seattle", true);
    Langileak langile2 = new Langileak("2", "Fuller", "Andrew", "Vicepresidente comercial", "Dr.", 
            LocalDate.of(1952, 2, 19), LocalDate.of(1992, 8, 14), "908 W. Capital Way", "Tacoma", true);
    Langileak langile3 = new Langileak("3", "Leverling", "Janet", "Representante de ventas", "Stra.", 
            LocalDate.of(1963, 8, 30), LocalDate.of(1992, 4, 1), "722 Moss Bay Blvd.", "Kirkland", true);
    Langileak langile4 = new Langileak("4", "Peacock", "Margaret", "Representante de ventas", "Sra.", 
            LocalDate.of(1958, 9, 19), LocalDate.of(1993, 5, 3), "4110 Old Redmond Rd.", "Redmond", false);
    Langileak langile5 = new Langileak("5", "Buchanan", "Steven", "Gerente de ventas", "Sr.", 
            LocalDate.of(1955, 3, 4), LocalDate.of(1993, 10, 17), "14 Garrett Hill", "Londres", true);
    Langileak langile6 = new Langileak("6", "Suyama", "Michel", "Representante de ventas", "Sr.", 
            LocalDate.of(1963, 7, 2), LocalDate.of(1993, 10, 17), "Coventry House", "Londres", true);
    Langileak langile7 = new Langileak("7", "King", "Robert", "Representante de ventas", "Sr.", 
            LocalDate.of(1960, 5, 29), LocalDate.of(1994, 1, 2), "Edgeham Hollow", "Londres", false);
    Langileak langile8 = new Langileak("8", "Callahan", "Laura", "Coordinador ventas internacionales", "Stra.", 
            LocalDate.of(1958, 1, 9), LocalDate.of(1994, 3, 5), "4726-11th Ave.N.E.", "Seattle", true);
    Langileak langile9 = new Langileak("9", "Dodsworth", "Anne", "Representante de ventas", "Stra.", 
            LocalDate.of(1969, 7, 2), LocalDate.of(1994, 11, 2), "7 Houndstooth Rd.", "Londres", true);
	
	Enpresa enpresa1 = new Enpresa(1, "TecnoSoluciones S.A.", "Tecnología", 120, "+52 55 1234 5678", "contacto@tecnosoluciones.com", "Av. Insurgentes Sur 1234, Piso 4", "Ciudad de México",Arrays.asList(langile6,langile7));
    Enpresa enpresa2 = new Enpresa(2, "VerdeVida Agroindustrial", "Agricultura", 80, "+57 1 9876 5432", "info@verdevida.co", "Calle 45 No. 23-56", "Bogotá", Arrays.asList(langile5,langile9));
    Enpresa enpresa3 = new Enpresa(3, "SaludVital Clínicas", "Salud", 50, "+34 910 234 567", "contacto@saludvital.es", "Calle Mayor 89, Planta 2", "Madrid", Arrays.asList(langile2,langile3));
    Enpresa enpresa4 = new Enpresa(4, "Logística Global Express", "Transporte y Logística", 150, "+1 212 345 6789", "support@globalexpress.com", "456 7th Avenue", "Nueva York",Arrays.asList(langile1,langile4,langile8));
    
    
}
