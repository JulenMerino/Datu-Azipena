package DatuAtzipena;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Metodoak klaseak XML fitxategian datuak kargatu, bilatu, bistaratu, eta ezabatzeko funtzionalitateak eskaintzen ditu.
 * Horrez gain, XML fitxategian datuak gorde eta eguneratzea ere ahalbidetzen du.
 */
public class Metodoak {

    // XML fitxategiaren bidea definitzen du
    private static final String FITXATEGI_BIDEA = "datuak.xml"; 

    /**
     * Kodearen arabera erregistro bat bilatzen du XML fitxategian.
     * Kodea aurkitzen bada, erregistroa bistaratzen du.
     * Bestela, mezu bat erakusten du ez dela erregistroa aurkitu.
     * 
     * @param kodea Erregistroaren identifikatzailea (ID).
     */
    public static void erregistroaKodearenAraberaBilatu(String kodea) {
        try {
            // XML fitxategia kargatzen da
            Document dokumentua = kargatuXMLFitxategitik();
            NodeList erregistroak = dokumentua.getElementsByTagName("erregistroa");
            boolean aurkituta = false;

            // Erregistro guztiak begiratzen dira
            for (int i = 0; i < erregistroak.getLength(); i++) {
                Element erregistroa = (Element) erregistroak.item(i);
                // Kodea egokitzen bada, erregistroa bistaratzen da
                if (erregistroa.getAttribute("id").equals(kodea)) {
                    aurkituta = true;
                    System.out.println("\n--- Erregistro Aurkitua ---");
                    bistaratuErregistroa(erregistroa);
                    break;
                }
            }

            // Erregistroa aurkitu ez bada
            if (!aurkituta) {
                System.out.println("Ez da kode hori duen erregistroa aurkitu.");
            }
        } catch (Exception e) {
            System.out.println("Errorea erregistroa bilatzean: " + e.getMessage());
        }
    }

    /**
     * Hitz gakoarekin erregistroak bilatzen ditu XML fitxategian.
     * Hitz gakoa aurkitzen duten erregistroak bistaratzen ditu.
     * 
     * @param hitza Hitz gakoa.
     */
    public static void hitzGakoarekinErregistroakBilatu(String hitza) {
        try {
            // XML fitxategia kargatzen da
            Document dokumentua = kargatuXMLFitxategitik();
            NodeList erregistroak = dokumentua.getElementsByTagName("erregistroa");
            boolean aurkituta = false;

            System.out.println("\n--- Hitz gakoa duten Erregistroak ---");
            // Erregistro guztietan hitz gakoa bilatzen da
            for (int i = 0; i < erregistroak.getLength(); i++) {
                Element erregistroa = (Element) erregistroak.item(i);

                // Hitz gakoa aurkitzen bada, erregistroa bistaratzen da
                if (erregistroa.getTextContent().toLowerCase().contains(hitza.toLowerCase())) {
                    aurkituta = true;
                    bistaratuErregistroa(erregistroa);
                }
            }

            // Hitz gakoa duten erregistroak aurkitu ez badira
            if (!aurkituta) {
                System.out.println("Ez da hitz gakoa duten erregistrorik aurkitu.");
            }
        } catch (Exception e) {
            System.out.println("Errorea hitz gakoa duten erregistroak bilatzean: " + e.getMessage());
        }
    }

    /**
     * Kodearen arabera erregistro bat ezabatzen du XML fitxategitik.
     * Kodea aurkitzen bada, erregistroa ezabatzen du eta fitxategian gorde.
     * Bestela, mezu bat erakusten du ez dela erregistroa aurkitu.
     * 
     * @param kodea Erregistroaren identifikatzailea (ID).
     */
    public static void erregistroaKodearenAraberaEzabatu(String kodea) {
        try {
            // XML fitxategia kargatzen da
            Document dokumentua = kargatuXMLFitxategitik();
            NodeList erregistroak = dokumentua.getElementsByTagName("erregistroa");
            boolean aurkituta = false;

            // Erregistro guztiak begiratzen dira eta kodea aurkitzen bada, ezabatzen da
            for (int i = 0; i < erregistroak.getLength(); i++) {
                Element erregistroa = (Element) erregistroak.item(i);
                if (erregistroa.getAttribute("id").equals(kodea)) {
                    erregistroa.getParentNode().removeChild(erregistroa);
                    aurkituta = true;
                    break;
                }
            }

            // Erregistroa aurkitu bada, dokumentua gorde egiten da
            if (aurkituta) {
                gordeXMLFitxategian(dokumentua);
                System.out.println("Erregistroa ezabatu da.");
            } else {
                System.out.println("Ez da kode hori duen erregistroa aurkitu.");
            }
        } catch (Exception e) {
            System.out.println("Errorea erregistroa ezabatzean: " + e.getMessage());
        }
    }

    /**
     * Erregistro baten datuak bistaratzen ditu.
     * Erregistroaren atributuak eta balioak erakusten ditu pantailan.
     * 
     * @param erregistroa XML fitxategian aurkitutako erregistro bat.
     */
    public static void bistaratuErregistroa(Element erregistroa) {
        String id = erregistroa.getAttribute("id");
        String izena = erregistroa.getElementsByTagName("izena").item(0).getTextContent();
        String abizena = erregistroa.getElementsByTagName("abizena").item(0).getTextContent();
        String kargua = erregistroa.getElementsByTagName("kargua").item(0).getTextContent();
        String tratua = erregistroa.getElementsByTagName("tratua").item(0).getTextContent();
        String jaiotzeData = erregistroa.getElementsByTagName("jaiotzeData").item(0).getTextContent();
        String kontratuData = erregistroa.getElementsByTagName("kontratuData").item(0).getTextContent();
        String helbidea = erregistroa.getElementsByTagName("helbidea").item(0).getTextContent();
        String hiria = erregistroa.getElementsByTagName("hiria").item(0).getTextContent();

        // Erregistroaren datuak pantailan erakusten dira
        System.out.printf(""" 
                ID: %s
                Izena: %s
                Abizena: %s
                Kargua: %s
                Tratua: %s
                Jaiotze Data: %s
                Kontratu Data: %s
                Helbidea: %s
                Hiria: %s
                %n""", id, izena, abizena, kargua, tratua, jaiotzeData, kontratuData, helbidea, hiria);
    }

    /**
     * XML fitxategi bat kargatzen du.
     * 
     * @return Kargatutako dokumentua.
     * @throws Exception Errorea gertatuz gero.
     */
    static Document kargatuXMLFitxategitik() throws Exception {
        File fitxategia = new File(FITXATEGI_BIDEA);
        if (!fitxategia.exists()) {
            throw new Exception("XML fitxategia ez da existitzen. Lehenik sortu behar duzu.");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(fitxategia);
    }

    /**
     * XML dokumentu bat fitxategian gorde egiten du.
     * 
     * @param dokumentua XML dokumentua.
     * @throws Exception Errorea gertatuz gero.
     */
    static void gordeXMLFitxategian(Document dokumentua) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(dokumentua);
        StreamResult result = new StreamResult(new File(FITXATEGI_BIDEA));
        transformer.transform(source, result);
    }
}
