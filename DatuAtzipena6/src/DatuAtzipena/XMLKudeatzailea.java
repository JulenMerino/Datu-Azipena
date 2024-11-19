package DatuAtzipena;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.time.LocalDate;

/**
 * XMLKudeatzailea klaseak XML fitxategiak kudeatzeko funtzionalitateak eskaintzen ditu.
 * Hasierako datuak sortu eta XML fitxategian gorde ditzake, baita XML fitxategian gordetako erregistro guztiak bistaratzea ere.
 */
public class XMLKudeatzailea {

    // XML fitxategiaren bidea definitzen du
    public static final String FITXATEGI_BIDEA = "datuak.xml";

    /**
     * Hasierako datuak sortzen ditu XML dokumentu batean.
     * Erregistro batzuk sortzen ditu eta horiek XML fitxategi batean gorde.
     * XML dokumentua sortzeko elementu desberdinak erabiltzen ditu, hala nola izena, abizena, kargua, jaiotze data, kontratu data, helbidea eta hiria.
     */
    public static void hasierakoDatuakSortu() {
        try {
            // Dokumentuaren builder-a sortzen da
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dokumentua = builder.newDocument();

            // Root elementua "erregistroak" izenekoa sortzen da
            Element erroa = dokumentua.createElement("erregistroak");
            dokumentua.appendChild(erroa);

            // Datuen zerrenda
            int[] kodea = { 1, 2, 3, 4, 5, 6, 7, 8, 9};
            String[] izena = { "Nancy", "Andrew", "Janet", "Margaret", "Steven", "Michel", "Robert", "Laura", "Anne" };
            String[] abizena = { "Davolio", "Fuller", "Leverling", "Peacock", "Buchanan", "Suyama", "King", "Callahan", "Dodsworth" };
            String[] kargua = { "Representante de ventas", "Vicepresidente comercial", "Representante de ventas",
                    			"Representante de ventas", "Gerente de ventas", "Representante de ventas",
                    			"Representante de ventas", "Coordinador ventas internacionales", "Representante de ventas" };
            String[] tratua = { "Stra.", "Dr.", "Stra.", "Sra.", "Sr.", "Sr.", "Sr.", "Stra.", "Stra." };
            LocalDate[] jaiotzeData = {
                    LocalDate.of(1968, 12, 8), LocalDate.of(1952, 2, 19), LocalDate.of(1963, 8, 30),
                    LocalDate.of(1958, 9, 19), LocalDate.of(1955, 3, 4), LocalDate.of(1963, 7, 2),
                    LocalDate.of(1960, 5, 29), LocalDate.of(1958, 1, 9), LocalDate.of(1969, 7, 2)
            };
            LocalDate[] kontratuData = {
                    LocalDate.of(1992, 5, 1), LocalDate.of(1992, 8, 14), LocalDate.of(1992, 4, 1),
                    LocalDate.of(1993, 5, 3), LocalDate.of(1993, 10, 17), LocalDate.of(1993, 10, 17),
                    LocalDate.of(1994, 1, 2), LocalDate.of(1994, 3, 5), LocalDate.of(1994, 11, 2)
            };
            String[] helbidea = { "507-20th Ave.E.", "908 W. Capital Way", "722 Moss Bay Blvd.",
                                  "4110 Old Redmond Rd.", "14 Garrett Hill", "Coventry House",
                                  "Edgeham Hollow", "4726-11th Ave.N.E.", "7 Houndstooth Rd." };
            String[] hiria = { "Seattle", "Tacoma", "Kirkland", "Redmond", "Londres", "Londres",
                               "Londres", "Seattle", "Londres"};

            // Erregistro bakoitza sortzen da eta XML elementu desberdinak gehitzen dira
            for (int i = 0; i < kodea.length; i++) {
                Element erregistroa = dokumentua.createElement("erregistroa");
                erregistroa.setAttribute("id", String.valueOf(kodea[i]));

                // Izena
                Element izenaElementua = dokumentua.createElement("izena");
                izenaElementua.setTextContent(izena[i]);
                erregistroa.appendChild(izenaElementua);

                // Abizena
                Element abizenaElementua = dokumentua.createElement("abizena");
                abizenaElementua.setTextContent(abizena[i]);
                erregistroa.appendChild(abizenaElementua);

                // Kargua
                Element karguaElementua = dokumentua.createElement("kargua");
                karguaElementua.setTextContent(kargua[i]);
                erregistroa.appendChild(karguaElementua);

                // Tratua
                Element tratuaElementua = dokumentua.createElement("tratua");
                tratuaElementua.setTextContent(tratua[i]);
                erregistroa.appendChild(tratuaElementua);

                // Jaiotze Data
                Element jaiotzeDataElementua = dokumentua.createElement("jaiotzeData");
                jaiotzeDataElementua.setTextContent(jaiotzeData[i].toString());
                erregistroa.appendChild(jaiotzeDataElementua);

                // Kontratu Data
                Element kontratuDataElementua = dokumentua.createElement("kontratuData");
                kontratuDataElementua.setTextContent(kontratuData[i].toString());
                erregistroa.appendChild(kontratuDataElementua);

                // Helbidea
                Element helbideaElementua = dokumentua.createElement("helbidea");
                helbideaElementua.setTextContent(helbidea[i]);
                erregistroa.appendChild(helbideaElementua);

                // Hiria
                Element hiriaElementua = dokumentua.createElement("hiria");
                hiriaElementua.setTextContent(hiria[i]);
                erregistroa.appendChild(hiriaElementua);

                erroa.appendChild(erregistroa);
            }

            // XML fitxategian gorde
            Metodoak.gordeXMLFitxategian(dokumentua);
            System.out.println("XML fitxategia sortu da hasierako datuekin.");
        } catch (Exception e) {
            System.out.println("Errorea hasierako datuak sortzean: " + e.getMessage());
        }
    }

    /**
     * XML fitxategian dauden erregistro guztiak bistaratzen ditu.
     * Fitxategitik erregistroak kargatzen ditu eta haien datuak kontsultatzen ditu.
     */
    public static void erregistroGuztiakBistaratu() {
        try {
            // XML fitxategia kargatu
            Document dokumentua = Metodoak.kargatuXMLFitxategitik();
            NodeList erregistroak = dokumentua.getElementsByTagName("erregistroa");

            System.out.println("\n--- Erregistroen Zerrenda ---");
            // Erregistro guztien datuak erakusten ditu
            for (int i = 0; i < erregistroak.getLength(); i++) {
                Element erregistroa = (Element) erregistroak.item(i);
                String id = erregistroa.getAttribute("id");
                String izena = erregistroa.getElementsByTagName("izena").item(0).getTextContent();
                String abizena = erregistroa.getElementsByTagName("abizena").item(0).getTextContent();
                String kargua = erregistroa.getElementsByTagName("kargua").item(0).getTextContent();
                String tratua = erregistroa.getElementsByTagName("tratua").item(0).getTextContent();
                String jaiotzeData = erregistroa.getElementsByTagName("jaiotzeData").item(0).getTextContent();
                String kontratuData = erregistroa.getElementsByTagName("kontratuData").item(0).getTextContent();
                String helbidea = erregistroa.getElementsByTagName("helbidea").item(0).getTextContent();
                String hiria = erregistroa.getElementsByTagName("hiria").item(0).getTextContent();

                // Datuak pantailan erakusten dira
                System.out.printf("ID: %s | Izena: %s | Abizena: %s | Kargua: %s | Tratua: %s | Jaiotze Data: %s | Kontratu Data: %s | Helbidea: %s | Hiria: %s%n",
                        id, izena, abizena, kargua, tratua, jaiotzeData, kontratuData, helbidea, hiria);
            }
        } catch (Exception e) {
            System.out.println("Errorea erregistroak erakustean: " + e.getMessage());
        }
    }
}
