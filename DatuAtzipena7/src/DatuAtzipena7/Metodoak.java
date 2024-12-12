package DatuAtzipena7;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.format.DateTimeFormatter;

public class Metodoak {


	public void generarXMLConEmpresas(List<Enpresa> enpresas) throws Exception {

		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();


		Element root = document.createElement("Enpresas");
		document.appendChild(root);


		for (Enpresa enpresa : enpresas) {
			createEmpresaElement(document, root, enpresa);
		}


		saveXMLToFile(document, "datuak.xml");  
	}



	public void mostrarDesdeXML(String filename) {
		try {
			// Crear un DocumentBuilder para leer el archivo XML
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(filename));

			// Normalizar el documento (opcional, pero recomendado)
			document.getDocumentElement().normalize();

			// Obtener todas las empresas (nodos <empresa>)
			NodeList empresaNodes = document.getElementsByTagName("empresa");
			for (int i = 0; i < empresaNodes.getLength(); i++) {
				Node empresaNode = empresaNodes.item(i);
				if (empresaNode.getNodeType() == Node.ELEMENT_NODE) {
					Element empresaElement = (Element) empresaNode;

					// Extraer información de la empresa
					System.out.println("Empresa ID: " + empresaElement.getElementsByTagName("id").item(0).getTextContent());
					System.out.println("Nombre: " + empresaElement.getElementsByTagName("nombre").item(0).getTextContent());
					System.out.println("Sector: " + empresaElement.getElementsByTagName("sector").item(0).getTextContent());
					System.out.println("Número de Empleados: " + empresaElement.getElementsByTagName("numEmpleados").item(0).getTextContent());
					System.out.println("Teléfono: " + empresaElement.getElementsByTagName("telefono").item(0).getTextContent());
					System.out.println("Correo: " + empresaElement.getElementsByTagName("correo").item(0).getTextContent());
					System.out.println("Dirección: " + empresaElement.getElementsByTagName("direccion").item(0).getTextContent());
					System.out.println("Ciudad: " + empresaElement.getElementsByTagName("ciudad").item(0).getTextContent());
					System.out.println("\nEmpleados:");

					// Obtener todos los empleados de la empresa
					NodeList empleadoNodes = empresaElement.getElementsByTagName("empleado");
					for (int j = 0; j < empleadoNodes.getLength(); j++) {
						Node empleadoNode = empleadoNodes.item(j);
						if (empleadoNode.getNodeType() == Node.ELEMENT_NODE) {
							Element empleadoElement = (Element) empleadoNode;

							// Extraer información de cada empleado
							System.out.println("  Empleado Código: " + empleadoElement.getElementsByTagName("codigo").item(0).getTextContent());
							System.out.println("  Nombre: " + empleadoElement.getElementsByTagName("nombre").item(0).getTextContent());
							System.out.println("  Apellidos: " + empleadoElement.getElementsByTagName("apellidos").item(0).getTextContent());
							System.out.println("  Cargo: " + empleadoElement.getElementsByTagName("cargo").item(0).getTextContent());
							System.out.println("  Tratamiento: " + empleadoElement.getElementsByTagName("tratamiento").item(0).getTextContent());
							System.out.println("  Fecha de Nacimiento: " + empleadoElement.getElementsByTagName("fechaNacimiento").item(0).getTextContent());
							System.out.println("  Fecha de Contratación: " + empleadoElement.getElementsByTagName("fechaContratacion").item(0).getTextContent());
							System.out.println("  Dirección: " + empleadoElement.getElementsByTagName("direccion").item(0).getTextContent());
							System.out.println("  Ciudad: " + empleadoElement.getElementsByTagName("ciudad").item(0).getTextContent());
							System.out.println("  Actualmente trabajando: " + empleadoElement.getElementsByTagName("lanean").item(0).getTextContent());
							System.out.println();
						}
					}

					// Separar empresas con un espacio
					System.out.println("----------------------------------------------------\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al leer el archivo XML.");
		}
	}


	public void añadirRegistroN(String filename) {
		try {
			Scanner scanner = new Scanner(System.in);

			// Solicitar ID de la empresa
			System.out.print("Ingrese el ID de la empresa: ");
			String empresaId = scanner.nextLine();

			// Solicitar datos del nuevo empleado
			System.out.print("Ingrese el código del empleado: ");
			String codigo = scanner.nextLine();
			System.out.print("Ingrese el nombre del empleado: ");
			String nombre = scanner.nextLine();
			System.out.print("Ingrese los apellidos del empleado: ");
			String apellidos = scanner.nextLine();
			System.out.print("Ingrese el cargo del empleado: ");
			String cargo = scanner.nextLine();
			System.out.print("Ingrese el tratamiento del empleado: ");
			String tratamiento = scanner.nextLine();
			System.out.print("Ingrese la fecha de nacimiento del empleado (YYYY-MM-DD): ");
			String fechaNacimiento = scanner.nextLine();
			System.out.print("Ingrese la fecha de contratación del empleado (YYYY-MM-DD): ");
			String fechaContratacion = scanner.nextLine();
			System.out.print("Ingrese la dirección del empleado: ");
			String direccion = scanner.nextLine();
			System.out.print("Ingrese la ciudad del empleado: ");
			String ciudad = scanner.nextLine();
			System.out.print("¿Actualmente trabajando? (true/false): ");
			String lanean = scanner.nextLine();

			// Cargar el archivo XML existente
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(filename));

			// Normalizar el documento (opcional)
			document.getDocumentElement().normalize();

			// Buscar la empresa correspondiente
			NodeList empresas = document.getElementsByTagName("empresa");
			boolean empresaEncontrada = false;

			for (int i = 0; i < empresas.getLength(); i++) {
				Node empresaNode = empresas.item(i);
				if (empresaNode.getNodeType() == Node.ELEMENT_NODE) {
					Element empresaElement = (Element) empresaNode;
					String id = empresaElement.getElementsByTagName("id").item(0).getTextContent();

					if (id.equals(empresaId)) {
						empresaEncontrada = true;

						// Crear el nuevo nodo <empleado>
						Element nuevoEmpleado = document.createElement("empleado");

						Element codigoElem = document.createElement("codigo");
						codigoElem.setTextContent(codigo);
						nuevoEmpleado.appendChild(codigoElem);

						Element nombreElem = document.createElement("nombre");
						nombreElem.setTextContent(nombre);
						nuevoEmpleado.appendChild(nombreElem);

						Element apellidosElem = document.createElement("apellidos");
						apellidosElem.setTextContent(apellidos);
						nuevoEmpleado.appendChild(apellidosElem);

						Element cargoElem = document.createElement("cargo");
						cargoElem.setTextContent(cargo);
						nuevoEmpleado.appendChild(cargoElem);

						Element tratamientoElem = document.createElement("tratamiento");
						tratamientoElem.setTextContent(tratamiento);
						nuevoEmpleado.appendChild(tratamientoElem);

						Element fechaNacimientoElem = document.createElement("fechaNacimiento");
						fechaNacimientoElem.setTextContent(fechaNacimiento);
						nuevoEmpleado.appendChild(fechaNacimientoElem);

						Element fechaContratacionElem = document.createElement("fechaContratacion");
						fechaContratacionElem.setTextContent(fechaContratacion);
						nuevoEmpleado.appendChild(fechaContratacionElem);

						Element direccionElem = document.createElement("direccion");
						direccionElem.setTextContent(direccion);
						nuevoEmpleado.appendChild(direccionElem);

						Element ciudadElem = document.createElement("ciudad");
						ciudadElem.setTextContent(ciudad);
						nuevoEmpleado.appendChild(ciudadElem);

						Element laneanElem = document.createElement("lanean");
						laneanElem.setTextContent(lanean);
						nuevoEmpleado.appendChild(laneanElem);

						// Añadir el nuevo empleado al nodo <empleados>
						NodeList empleadosList = empresaElement.getElementsByTagName("empleados");
						if (empleadosList.getLength() > 0) {
							empleadosList.item(0).appendChild(nuevoEmpleado);
						} else {
							// Si no existe el nodo <empleados>, crearlo y añadir el empleado
							Element empleados = document.createElement("empleados");
							empleados.appendChild(nuevoEmpleado);
							empresaElement.appendChild(empleados);
						}

						break;
					}
				}
			}

			if (!empresaEncontrada) {
				System.out.println("Empresa con ID " + empresaId + " no encontrada.");
				return;
			}

			// Guardar los cambios en el archivo XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);

			System.out.println("Empleado añadido exitosamente a la empresa con ID " + empresaId + ".");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al añadir el registro.");
		}
	}


	public void buscarRegistroPorCodigo(String filename) {
		try {
			Scanner scanner = new Scanner(System.in);

			// Solicitar el código del registro a buscar
			System.out.print("Ingrese el código del registro a buscar: ");
			String codigoBuscado = scanner.nextLine();

			// Cargar el archivo XML
			File file = new File(filename);
			if (!file.exists()) {
				System.out.println("El archivo " + filename + " no existe.");
				return;
			}

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			// Buscar en todos los empleados de las empresas
			NodeList empresas = document.getElementsByTagName("empresa");
			boolean encontrado = false;

			for (int i = 0; i < empresas.getLength(); i++) {
				Node empresaNode = empresas.item(i);

				if (empresaNode.getNodeType() == Node.ELEMENT_NODE) {
					Element empresaElement = (Element) empresaNode;
					NodeList empleados = empresaElement.getElementsByTagName("empleado");

					for (int j = 0; j < empleados.getLength(); j++) {
						Node empleadoNode = empleados.item(j);

						if (empleadoNode.getNodeType() == Node.ELEMENT_NODE) {
							Element empleadoElement = (Element) empleadoNode;
							String codigo = empleadoElement.getElementsByTagName("codigo").item(0).getTextContent();

							if (codigo.equals(codigoBuscado)) {
								encontrado = true;
								System.out.println("Registro encontrado:");
								System.out.println("Código: " + codigo);
								System.out.println("Nombre: " + empleadoElement.getElementsByTagName("nombre").item(0).getTextContent());
								System.out.println("Apellidos: " + empleadoElement.getElementsByTagName("apellidos").item(0).getTextContent());
								System.out.println("Cargo: " + empleadoElement.getElementsByTagName("cargo").item(0).getTextContent());
								System.out.println("Tratamiento: " + empleadoElement.getElementsByTagName("tratamiento").item(0).getTextContent());
								System.out.println("Fecha de nacimiento: " + empleadoElement.getElementsByTagName("fechaNacimiento").item(0).getTextContent());
								System.out.println("Fecha de contratación: " + empleadoElement.getElementsByTagName("fechaContratacion").item(0).getTextContent());
								System.out.println("Dirección: " + empleadoElement.getElementsByTagName("direccion").item(0).getTextContent());
								System.out.println("Ciudad: " + empleadoElement.getElementsByTagName("ciudad").item(0).getTextContent());
								System.out.println("Trabajando actualmente: " + empleadoElement.getElementsByTagName("lanean").item(0).getTextContent());
								break;
							}
						}
					}

					if (encontrado) break;
				}
			}

			if (!encontrado) {
				System.out.println("No se encontró ningún registro con el código " + codigoBuscado + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al buscar el registro.");
		}
	}
	
	
	public void buscarRegistrosPorPalabra(String filename) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Solicitar la palabra a buscar
            System.out.print("Ingrese la palabra a buscar: ");
            String palabraBuscada = scanner.nextLine();

            // Solicitar el nombre del campo donde buscar
            System.out.print("Ingrese el campo donde buscar (ejemplo: nombre, cargo, ciudad, etc.): ");
            String campo = scanner.nextLine();

            // Cargar el archivo XML
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("El archivo " + filename + " no existe.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            document.getDocumentElement().normalize();

            // Buscar en todos los empleados de las empresas
            NodeList empresas = document.getElementsByTagName("empresa");
            boolean encontrado = false;

            System.out.println("\nRegistros encontrados que contienen \"" + palabraBuscada + "\" en el campo \"" + campo + "\":");
            System.out.printf("%-10s %-20s %-20s %-40s %-10s %-20s %-20s %-20s %-20s\n",
                    "Código", "Nombre", "Apellidos", "Cargo", "Trato", "Fecha Nacimiento", "Fecha Contratación", "Dirección", "Ciudad");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < empresas.getLength(); i++) {
                Node empresaNode = empresas.item(i);

                if (empresaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element empresaElement = (Element) empresaNode;
                    NodeList empleados = empresaElement.getElementsByTagName("empleado");

                    for (int j = 0; j < empleados.getLength(); j++) {
                        Node empleadoNode = empleados.item(j);

                        if (empleadoNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element empleadoElement = (Element) empleadoNode;

                            // Verificar si el campo contiene la palabra buscada
                            NodeList campoNodes = empleadoElement.getElementsByTagName(campo);
                            if (campoNodes.getLength() > 0) {
                                String valorCampo = campoNodes.item(0).getTextContent();
                                if (valorCampo.contains(palabraBuscada)) {
                                    encontrado = true;

                                    // Obtener y mostrar los datos del empleado
                                    String codigo = empleadoElement.getElementsByTagName("codigo").item(0).getTextContent();
                                    String nombre = empleadoElement.getElementsByTagName("nombre").item(0).getTextContent();
                                    String apellidos = empleadoElement.getElementsByTagName("apellidos").item(0).getTextContent();
                                    String cargo = empleadoElement.getElementsByTagName("cargo").item(0).getTextContent();
                                    String trato = empleadoElement.getElementsByTagName("tratamiento").item(0).getTextContent();
                                    String fechaNacimiento = empleadoElement.getElementsByTagName("fechaNacimiento").item(0).getTextContent();
                                    String fechaContratacion = empleadoElement.getElementsByTagName("fechaContratacion").item(0).getTextContent();
                                    String direccion = empleadoElement.getElementsByTagName("direccion").item(0).getTextContent();
                                    String ciudad = empleadoElement.getElementsByTagName("ciudad").item(0).getTextContent();

                                    // Imprimir los datos del empleado en formato tabular
                                    System.out.printf("%-10s %-20s %-20s %-40s %-10s %-20s %-20s %-20s %-20s\n", 
                                            codigo, nombre, apellidos, cargo, trato, fechaNacimiento, fechaContratacion, direccion, ciudad);
                                }
                            }
                        }
                    }
                }
            }

            if (!encontrado) {
                System.out.println("No se encontraron registros que contengan \"" + palabraBuscada + "\" en el campo \"" + campo + "\".");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al buscar los registros.");
        }
    }
	
	public void ordenarPorAtributo(String filename) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Solicitar al usuario el atributo por el cual ordenar
            System.out.println("Seleccione el atributo para ordenar a los empleados:");
            System.out.println("Opciones: nombre, apellidos, cargo, ciudad, codigo");
            String atributo = scanner.nextLine();

            // Crear un DocumentBuilder para leer el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));

            // Normalizar el documento (opcional, pero recomendado)
            document.getDocumentElement().normalize();

            // Crear una lista para almacenar empleados
            List<Element> empleadosList = new ArrayList<>();

            // Obtener todas las empresas (nodos <empresa>)
            NodeList empresaNodes = document.getElementsByTagName("empresa");
            for (int i = 0; i < empresaNodes.getLength(); i++) {
                Node empresaNode = empresaNodes.item(i);
                if (empresaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element empresaElement = (Element) empresaNode;

                    // Obtener todos los empleados de la empresa
                    NodeList empleadoNodes = empresaElement.getElementsByTagName("empleado");
                    for (int j = 0; j < empleadoNodes.getLength(); j++) {
                        Node empleadoNode = empleadoNodes.item(j);
                        if (empleadoNode.getNodeType() == Node.ELEMENT_NODE) {
                            empleadosList.add((Element) empleadoNode);
                        }
                    }
                }
            }

            // Ordenar la lista de empleados según el atributo especificado
            empleadosList.sort(Comparator.comparing(empleado -> {
                String valor = empleado.getElementsByTagName(atributo).item(0).getTextContent();
                return (valor != null) ? valor : ""; // Manejar valores nulos
            }));

            // Mostrar los empleados ordenados
            System.out.println("Empleados ordenados por " + atributo + ":");
            System.out.printf("%-10s %-20s %-20s %-40s %-10s %-20s %-20s %-20s %-20s\n",
                    "Código", "Nombre", "Apellidos", "Cargo", "Trato", "Fecha Nacimiento", "Fecha Contratación", "Dirección", "Ciudad");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (Element empleado : empleadosList) {
                // Obtener los datos de cada empleado
                String codigo = empleado.getElementsByTagName("codigo").item(0).getTextContent();
                String nombre = empleado.getElementsByTagName("nombre").item(0).getTextContent();
                String apellidos = empleado.getElementsByTagName("apellidos").item(0).getTextContent();
                String cargo = empleado.getElementsByTagName("cargo").item(0).getTextContent();
                String trato = empleado.getElementsByTagName("tratamiento").item(0).getTextContent();
                String fechaNacimiento = empleado.getElementsByTagName("fechaNacimiento").item(0).getTextContent();
                String fechaContratacion = empleado.getElementsByTagName("fechaContratacion").item(0).getTextContent();
                String direccion = empleado.getElementsByTagName("direccion").item(0).getTextContent();
                String ciudad = empleado.getElementsByTagName("ciudad").item(0).getTextContent();

                // Imprimir los datos del empleado en formato tabular
                System.out.printf("%-10s %-20s %-20s %-40s %-10s %-20s %-20s %-20s %-20s\n",
                        codigo, nombre, apellidos, cargo, trato, fechaNacimiento, fechaContratacion, direccion, ciudad);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al ordenar por atributo.");
        }
    }



	private void createEmpresaElement(Document document, Element root, Enpresa enpresa) {

		Element empresaElement = document.createElement("empresa");
		root.appendChild(empresaElement);


		appendChildElement(document, empresaElement, "id", String.valueOf(enpresa.getKodea()));
		appendChildElement(document, empresaElement, "nombre", enpresa.getIzena());
		appendChildElement(document, empresaElement, "sector", enpresa.getSektorea());
		appendChildElement(document, empresaElement, "numEmpleados", String.valueOf(enpresa.getPertsonakop()));
		appendChildElement(document, empresaElement, "telefono", enpresa.getTelefonoa());
		appendChildElement(document, empresaElement, "correo", enpresa.getEmaila());
		appendChildElement(document, empresaElement, "direccion", enpresa.getHelbidea());
		appendChildElement(document, empresaElement, "ciudad", enpresa.getHiria());


		Element empleadosElement = document.createElement("empleados");
		empresaElement.appendChild(empleadosElement);


		for (Langileak langile : enpresa.getLangileak()) {
			Element empleadoElement = document.createElement("empleado");
			empleadosElement.appendChild(empleadoElement);
			appendChildElement(document, empleadoElement, "codigo", langile.getKodea());
			appendChildElement(document, empleadoElement, "nombre", langile.getIzena());
			appendChildElement(document, empleadoElement, "apellidos", langile.getAbizena());
			appendChildElement(document, empleadoElement, "cargo", langile.getKargua());
			appendChildElement(document, empleadoElement, "tratamiento", langile.getTratua());
			appendChildElement(document, empleadoElement, "fechaNacimiento", langile.getJaiotzeData().format(DateTimeFormatter.ISO_LOCAL_DATE));
			appendChildElement(document, empleadoElement, "fechaContratacion", langile.getKontratuData().format(DateTimeFormatter.ISO_LOCAL_DATE));
			appendChildElement(document, empleadoElement, "direccion", langile.getHelbidea());
			appendChildElement(document, empleadoElement, "ciudad", langile.getHiria());
			appendChildElement(document, empleadoElement, "lanean", String.valueOf(langile.isLanean()));
		}
	}

	private void appendChildElement(Document document, Element parent, String name, String value) {
		Element element = document.createElement(name);
		element.appendChild(document.createTextNode(value));
		parent.appendChild(element);
	}

	private void saveXMLToFile(Document document, String filename) throws IOException, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");  

		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(filename));

		transformer.transform(source, result);
	}
}
