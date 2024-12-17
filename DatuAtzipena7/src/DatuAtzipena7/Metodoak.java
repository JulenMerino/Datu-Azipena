package DatuAtzipena7;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.format.DateTimeFormatter;


public class Metodoak {

    /**
     * Enpresen zerrenda bat jasotzen du eta XML fitxategi batean gordetzen du.
     * 
     * @param enpresak Enpresen zerrenda bat.
     * @param dirHelbidea Fitxategiaren helmuga direktorioaren helbidea.
     * @throws Exception XML fitxategia sortzeko edo idazteko arazoak sor daitezke.
     */
    public void sortuEnpresenXMLa(List<Enpresa> enpresak, String dirHelbidea) throws Exception {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element root = document.createElement("Enpresak");
        document.appendChild(root);

        for (Enpresa enpresa : enpresak) {
            sortuEnpresaElement(document, root, enpresa);
        }

        File outputFile = new File(dirHelbidea, "datuak.xml");
        saveXMLToFile(document, outputFile.getAbsolutePath());
    }

    /**
     * XML fitxategi bat irakurtzen du eta kontsolan erakusten ditu enpresen eta langileen datuak.
     * 
     * @param filename XML fitxategiaren izena.
     */
    public void XMLaErabilizErakutsi(String filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));

            document.getDocumentElement().normalize();

            NodeList enpresaNodeak = document.getElementsByTagName("Enpresa");
            for (int i = 0; i < enpresaNodeak.getLength(); i++) {
                Node enpresaNode = enpresaNodeak.item(i);
                if (enpresaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element enpresaElement = (Element) enpresaNode;

                    System.out.println("Enpresa Kodea: " + enpresaElement.getElementsByTagName("Kodea").item(0).getTextContent());
                    System.out.println("Izena: " + enpresaElement.getElementsByTagName("Izena").item(0).getTextContent());
                    System.out.println("Sektorea: " + enpresaElement.getElementsByTagName("Sektorea").item(0).getTextContent());
                    System.out.println("Langile kopurua: " + enpresaElement.getElementsByTagName("PerKop").item(0).getTextContent());
                    System.out.println("Telefonoa: " + enpresaElement.getElementsByTagName("Telefonoa").item(0).getTextContent());
                    System.out.println("Korreoa: " + enpresaElement.getElementsByTagName("Korreoa").item(0).getTextContent());
                    System.out.println("Helbidea: " + enpresaElement.getElementsByTagName("Helbidea").item(0).getTextContent());
                    System.out.println("Hiria: " + enpresaElement.getElementsByTagName("Hiria").item(0).getTextContent());
                    System.out.println("\nEmpleados:");

                    NodeList langileaNodeak = enpresaElement.getElementsByTagName("Langilea");
                    for (int j = 0; j < langileaNodeak.getLength(); j++) {
                        Node langileakNode = langileaNodeak.item(j);
                        if (langileakNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element langileaElement = (Element) langileakNode;

                            System.out.println("  Langile Kodea: " + langileaElement.getElementsByTagName("Kodea").item(0).getTextContent());
                            System.out.println("  Izena: " + langileaElement.getElementsByTagName("Izena").item(0).getTextContent());
                            System.out.println("  Abizena: " + langileaElement.getElementsByTagName("Abizena").item(0).getTextContent());
                            System.out.println("  Kargua: " + langileaElement.getElementsByTagName("Kargua").item(0).getTextContent());
                            System.out.println("  Tratua: " + langileaElement.getElementsByTagName("Tratua").item(0).getTextContent());
                            System.out.println("  Jaiotze data: " + langileaElement.getElementsByTagName("JaiotzeData").item(0).getTextContent());
                            System.out.println("  Kontratazio data: " + langileaElement.getElementsByTagName("KontratazioData").item(0).getTextContent());
                            System.out.println("  Helbidea: " + langileaElement.getElementsByTagName("Helbidea").item(0).getTextContent());
                            System.out.println("  Hiria: " + langileaElement.getElementsByTagName("Hiria").item(0).getTextContent());
                            System.out.println("  Lanenan: " + langileaElement.getElementsByTagName("Lanean").item(0).getTextContent());
                            System.out.println();
                        }
                    }

                    System.out.println("----------------------------------------------------\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo XML.");
        }
    }

    /**
     * Langile berri bat enpresara gehitzen du, XML fitxategian gordetzen dena.
     * 
     * @param filename XML fitxategiaren izena.
     */
    public void SortuLangilea(String filename) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Zer enpresatan sortu nahi duzu??(sartu kodea): ");
            String enpresaId = sc.nextLine();

            System.out.print("Sartu langilearen kodea: ");
            String kodea = sc.nextLine();
            System.out.print("Sartu langilearen izena: ");
            String izena = sc.nextLine();
            System.out.print("Sartu langilearen abizena: ");
            String abizena = sc.nextLine();
            System.out.print("Sartu langilearen kargua: ");
            String kargua = sc.nextLine();
            System.out.print("Sartu langilearen tratua: ");
            String tratua = sc.nextLine();
            System.out.print("Sartu langilearen jaiotze data (YYYY-MM-DD): ");
            String jaiotzeData = sc.nextLine();
            System.out.print("Sartu langilearen kontatazio data (YYYY-MM-DD): ");
            String kontratazioData = sc.nextLine();
            System.out.print("Sartu langilearen helbidea: ");
            String helbidea = sc.nextLine();
            System.out.print("Sartu langilearen hiria: ");
            String hiria = sc.nextLine();
            System.out.print("Lanean dago? (true/false): ");
            String lanean = sc.nextLine();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));

            document.getDocumentElement().normalize();

            NodeList enpresak = document.getElementsByTagName("Enpresa");
            boolean enpresaAurkituta = false;

            for (int i = 0; i < enpresak.getLength(); i++) {
                Node enpresaNode = enpresak.item(i);
                if (enpresaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element enpresaElement = (Element) enpresaNode;
                    String Kodea = enpresaElement.getElementsByTagName("Kodea").item(0).getTextContent();

                    if (Kodea.equals(enpresaId)) {
                        enpresaAurkituta = true;

                        Element langileBerria = document.createElement("Langilea");

                        Element kodeaElement = document.createElement("Kodea");
                        kodeaElement.setTextContent(kodea);
                        langileBerria.appendChild(kodeaElement);

                        Element izenaElement = document.createElement("Izena");
                        izenaElement.setTextContent(izena);
                        langileBerria.appendChild(izenaElement);

                        Element abizenaElement = document.createElement("Abizena");
                        abizenaElement.setTextContent(abizena);
                        langileBerria.appendChild(abizenaElement);

                        Element karguaElement = document.createElement("Kargua");
                        karguaElement.setTextContent(kargua);
                        langileBerria.appendChild(karguaElement);

                        Element traruaElement = document.createElement("Tratua");
                        traruaElement.setTextContent(tratua);
                        langileBerria.appendChild(traruaElement);

                        Element jaiotzeDataElement = document.createElement("JaiotzeData");
                        jaiotzeDataElement.setTextContent(jaiotzeData);
                        langileBerria.appendChild(jaiotzeDataElement);

                        Element kontratazioDataElement = document.createElement("KontratazioData");
                        kontratazioDataElement.setTextContent(kontratazioData);
                        langileBerria.appendChild(kontratazioDataElement);

                        Element helbideaElement = document.createElement("Helbidea");
                        helbideaElement.setTextContent(helbidea);
                        langileBerria.appendChild(helbideaElement);

                        Element hiriaElement = document.createElement("Hiria");
                        hiriaElement.setTextContent(hiria);
                        langileBerria.appendChild(hiriaElement);

                        Element laneanElement = document.createElement("Lanean");
                        laneanElement.setTextContent(lanean);
                        langileBerria.appendChild(laneanElement);

                        NodeList langileakList = enpresaElement.getElementsByTagName("Enpresak");
                        if (langileakList.getLength() > 0) {
                            langileakList.item(0).appendChild(langileBerria);
                        } else {
                            Element langileak = document.createElement("Lagileak");
                            langileak.appendChild(langileBerria);
                            enpresaElement.appendChild(langileak);
                        }

                        break;
                    }
                }
            }

            if (!enpresaAurkituta) {
                System.out.println(enpresaId + " duen enpresa ez da aurkitu edo ez da existitzen");
                return;
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);

            System.out.println("Langilea sortuta " + enpresaId + " enpresan");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errorea erregistroa sortzerakoan.");
        }
    }


    /**
     * XML fitxategian bilatzen den langilea bere kodea erabiliz bilatzen du.
     * @param filename XML fitxategiaren izena.
     */
    public void bilatuLangileaKodeBidez(String filename) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Sartu bilatu nahi de langilea: ");
            String kodeaBilatu = sc.nextLine();

            File file = new File(filename);
            if (!file.exists()) {
                System.out.println(filename + " helbidean ez dago fitxategirik izen horrekin");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            document.getDocumentElement().normalize();

            NodeList enpresak = document.getElementsByTagName("Enpresa");
            boolean aurkituta = false;

            for (int i = 0; i < enpresak.getLength(); i++) {
                Node enpresaNode = enpresak.item(i);

                if (enpresaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element enpresaElement = (Element) enpresaNode;
                    NodeList langileak = enpresaElement.getElementsByTagName("Langilea");

                    for (int j = 0; j < langileak.getLength(); j++) {
                        Node langileaNode = langileak.item(j);

                        if (langileaNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element langileaElement = (Element) langileaNode;
                            String Kodea = langileaElement.getElementsByTagName("Kodea").item(0).getTextContent();

                            if (Kodea.equals(kodeaBilatu)) {
                                aurkituta = true;
                                System.out.println("Langilea bilatuta:");
                                System.out.println("Kodea: " + Kodea);
                                System.out.println("Izena: " + langileaElement.getElementsByTagName("Izena").item(0).getTextContent());
                                System.out.println("Abizena: " + langileaElement.getElementsByTagName("Abizena").item(0).getTextContent());
                                System.out.println("Kargua: " + langileaElement.getElementsByTagName("Kargua").item(0).getTextContent());
                                System.out.println("Tratua: " + langileaElement.getElementsByTagName("Tratua").item(0).getTextContent());
                                System.out.println("Jaiotze data: " + langileaElement.getElementsByTagName("JaiotzeData").item(0).getTextContent());
                                System.out.println("Kontratazio data: " + langileaElement.getElementsByTagName("KontratazioData").item(0).getTextContent());
                                System.out.println("Helbidea: " + langileaElement.getElementsByTagName("Helbidea").item(0).getTextContent());
                                System.out.println("Hiria: " + langileaElement.getElementsByTagName("Hiria").item(0).getTextContent());
                                System.out.println("Lanean: " + langileaElement.getElementsByTagName("Lanean").item(0).getTextContent());
                                break;
                            }
                        }
                    }

                    if (aurkituta) break;
                }
            }

            if (!aurkituta) {
                System.out.println("Ez da langilerik bilatu " + kodeaBilatu + " kodearekin");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errorea langilea bilatzean.");
        }
    }

    /**
     * XML fitxategian hitz baten bidez langileak bilatzen ditu.
     * @param filename XML fitxategiaren izena.
     */
    public void bilatuLangileaHitzBatenBidez(String filename) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Sartu bilatu nahi den hitza: ");
            String hitza = sc.nextLine();

            System.out.print("Zer sekzioan bilatu nahi duzu(izena, abizena, kargua, tratua, jaiotzeData, kontratazioData, helbidea, hira): ");
            String atributua = sc.nextLine();

            File file = new File(filename);
            if (!file.exists()) {
                System.out.println(filename + " helbidean ez dago fitxategirik izen horrekin");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            document.getDocumentElement().normalize();

            NodeList enpresak = document.getElementsByTagName("Enpresa");
            boolean aurkituta = false;

            System.out.println(hitza + " " + atributua + "duten langileak");
            System.out.printf("%-10s %-20s %-20s %-40s %-10s %-20s %-20s %-20s %-20s\n",
                    "Kodea", "Izena", "Abizena", "Kargua", "Tratua", "Jaiotze Data", "Kontatazio Data", "Helbidea", "Hiria");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < enpresak.getLength(); i++) {
                Node enpresaNode = enpresak.item(i);

                if (enpresaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element enpresaElement = (Element) enpresaNode;
                    NodeList langileak = enpresaElement.getElementsByTagName("Langilea");

                    for (int j = 0; j < langileak.getLength(); j++) {
                        Node langileaNode = langileak.item(j);

                        if (langileaNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element langileaElement = (Element) langileaNode;

                            NodeList sekzioaNode = langileaElement.getElementsByTagName(atributua);
                            if (sekzioaNode.getLength() > 0) {
                                String hitzaAtributu = sekzioaNode.item(0).getTextContent();
                                if (hitzaAtributu.contains(hitza)) {
                                    aurkituta = true;

                                    String kodea = langileaElement.getElementsByTagName("Kodea").item(0).getTextContent();
                                    String izena = langileaElement.getElementsByTagName("Izena").item(0).getTextContent();
                                    String abizena = langileaElement.getElementsByTagName("Abizena").item(0).getTextContent();
                                    String kargua = langileaElement.getElementsByTagName("Kargua").item(0).getTextContent();
                                    String tratua = langileaElement.getElementsByTagName("Tratua").item(0).getTextContent();
                                    String jaiotzeData = langileaElement.getElementsByTagName("JaiotzeData").item(0).getTextContent();
                                    String kontratazioData = langileaElement.getElementsByTagName("KontratazioData").item(0).getTextContent();
                                    String helbidea = langileaElement.getElementsByTagName("Helbidea").item(0).getTextContent();
                                    String hiria = langileaElement.getElementsByTagName("Hiria").item(0).getTextContent();

                                    System.out.printf("%-10s %-20s %-20s %-40s %-10s %-20s %-20s %-20s %-20s\n", 
                                            kodea, izena, abizena, kargua, tratua, jaiotzeData, kontratazioData, helbidea, hiria);
                                }
                            }
                        }
                    }
                }
            }

            if (!aurkituta) {
                System.out.println(atributua + " ez dugu aurkitu langilerik " + hitza + " hitza duena");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errorea langilea bilatzean");
        }
    }

    /**
     * XML fitxategian langileak atributu baten arabera ordenatzen ditu.
     * @param filename XML fitxategiaren izena.
     */
    public void ordenarPorAtributo(String filename) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Aukeratu zer atributuren arabera ordenatu nahi duzu:");
            System.out.println("Aukerak: Izena, Abizena, Kargua, Hiria, Kodea");
            String atributua = sc.nextLine();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));

            document.getDocumentElement().normalize();

            List<Element> langileakList = new ArrayList<>();

            NodeList enpresak = document.getElementsByTagName("Enpresa");
            for (int i = 0; i < enpresak.getLength(); i++) {
                Node epresaNode = enpresak.item(i);
                if (epresaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element empresaElement = (Element) epresaNode;

                    NodeList langileak = empresaElement.getElementsByTagName("Langilea");
                    for (int j = 0; j < langileak.getLength(); j++) {
                        Node langileaNode = langileak.item(j);
                        if (langileaNode.getNodeType() == Node.ELEMENT_NODE) {
                            langileakList.add((Element) langileaNode);
                        }
                    }
                }
            }

            langileakList.sort(Comparator.comparing(empleado -> {
                String balioa = empleado.getElementsByTagName(atributua).item(0).getTextContent();
                return (balioa != null) ? balioa : ""; 
            }));

            System.out.println("Langileak ordenatuta " + atributua + " arabera:");
            System.out.printf("%-10s %-20s %-20s %-40s %-10s %-20s %-20s %-20s %-20s\n",
                    "Kodea", "Izena", "Abizena", "Kargua", "Tratua", "Jaiotze Data", "Kontratazio Data", "Helbidea", "Hiria");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (Element langilea : langileakList) {
                String kodea = langilea.getElementsByTagName("Kodea").item(0).getTextContent();
                String izena = langilea.getElementsByTagName("Izena").item(0).getTextContent();
                String abizena = langilea.getElementsByTagName("Abizena").item(0).getTextContent();
                String kargua = langilea.getElementsByTagName("Kargua").item(0).getTextContent();
                String tratua = langilea.getElementsByTagName("Tratua").item(0).getTextContent();
                String jaiotzeData = langilea.getElementsByTagName("JaiotzeData").item(0).getTextContent();
                String kontratazioData = langilea.getElementsByTagName("KontratazioData").item(0).getTextContent();
                String helbidea = langilea.getElementsByTagName("Helbidea").item(0).getTextContent();
                String hiria = langilea.getElementsByTagName("Hiria").item(0).getTextContent();

                System.out.printf("%-10s %-20s %-20s %-40s %-10s %-20s %-20s %-20s %-20s\n",
                        kodea, izena, abizena, kargua, tratua, jaiotzeData, kontratazioData, helbidea, hiria);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errorea artibutu bidez ordenatzean");
        }
    }

    /**
     * Enpresa elementua sortzen du XML dokumentuaren erroan.
     * @param document XML dokumentua.
     * @param root Erroa elementua.
     * @param enpresa Enpresa objektua.
     */
    private void sortuEnpresaElement(Document document, Element root, Enpresa enpresa) {

        Element enpresaElement = document.createElement("Enpresa");
        root.appendChild(enpresaElement);

        appendChildElement(document, enpresaElement, "Kodea", String.valueOf(enpresa.getKodea()));
        appendChildElement(document, enpresaElement, "Izena", enpresa.getIzena());
        appendChildElement(document, enpresaElement, "Sektorea", enpresa.getSektorea());
        appendChildElement(document, enpresaElement, "PerKop", String.valueOf(enpresa.getPertsonakop()));
        appendChildElement(document, enpresaElement, "Telefonoa", enpresa.getTelefonoa());
        appendChildElement(document, enpresaElement, "Korreoa", enpresa.getEmaila());
        appendChildElement(document, enpresaElement, "Helbidea", enpresa.getHelbidea());
        appendChildElement(document, enpresaElement, "Hiria", enpresa.getHiria());

        Element langileakElement = document.createElement("Lagileak");
        enpresaElement.appendChild(langileakElement);

        for (Langileak langile : enpresa.getLangileak()) {
            Element langileaElement = document.createElement("Langilea");
            langileakElement.appendChild(langileaElement);
            appendChildElement(document, langileaElement, "Kodea", langile.getKodea());
            appendChildElement(document, langileaElement, "Izena", langile.getIzena());
            appendChildElement(document, langileaElement, "Abizena", langile.getAbizena());
            appendChildElement(document, langileaElement, "Kargua", langile.getKargua());
            appendChildElement(document, langileaElement, "Tratua", langile.getTratua());
            appendChildElement(document, langileaElement, "JaiotzeData", langile.getJaiotzeData().format(DateTimeFormatter.ISO_LOCAL_DATE));
            appendChildElement(document, langileaElement, "KontratazioData", langile.getKontratuData().format(DateTimeFormatter.ISO_LOCAL_DATE));
            appendChildElement(document, langileaElement, "Helbidea", langile.getHelbidea());
            appendChildElement(document, langileaElement, "Hiria", langile.getHiria());
            appendChildElement(document, langileaElement, "Lanean", String.valueOf(langile.isLanean()));
        }
    }

    /**
     * XML dokumentuan elementu bat gehitzen du.
     * @param document XML dokumentua.
     * @param parent Guraso elementua.
     * @param name Elementuaren izena.
     * @param value Elementuaren balioa.
     */
    private void appendChildElement(Document document, Element parent, String name, String value) {
        Element element = document.createElement(name);
        element.appendChild(document.createTextNode(value));
        parent.appendChild(element);
    }

    /**
     * XML dokumentua fitxategi batean gorde egiten du.
     * @param document XML dokumentua.
     * @param filename Fitxategiaren izena.
     * @throws IOException I/O errore bat gertatzen bada.
     * @throws TransformerException Transformer errore bat gertatzen bada.
     */
    private void saveXMLToFile(Document document, String filename) throws IOException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filename));

        transformer.transform(source, result);
    }
}