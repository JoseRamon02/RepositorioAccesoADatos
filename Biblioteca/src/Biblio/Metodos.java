package Biblio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Metodos {

	public static Biblioteca crearXML() {

		// Creamos las listas para agregar los libros

		List<Libro> librosLiteratura = new ArrayList<>();
		List<Libro> librosConsultayReferencia = new ArrayList<>();
		List<Libro> librosInfantiles = new ArrayList<>();
		List<Libro> librosDivulgativos = new ArrayList<>();
		List<Libro> librosTecnicos = new ArrayList<>();

		// Espectadores de libros

		// Libros de Literatura
		int[] lectoresCienAniosSoledad = { 1000, 1800, 1500, 1900, 1200, 1300, 1400, 900, 2200, 2000, 1700, 1600 };
		int[] lectores1984 = { 950, 1750, 1600, 1850, 1350, 1450, 1250, 850, 2350, 1950, 1675, 1625 };
		int[] lectoresSenorAnillos = { 1100, 1950, 1700, 2000, 1400, 1600, 1350, 800, 2100, 1900, 1750, 1550 };
		int[] lectoresOrgulloPrejuicio = { 1050, 1900, 1650, 1925, 1300, 1400, 1450, 875, 2250, 2050, 1725, 1575 };
		int[] lectoresDonQuijote = { 980, 1740, 1480, 1870, 1250, 1350, 1420, 920, 2380, 2030, 1760, 1590 };

		// Libros de Consulta y Referencia
		int[] lectoresEnciclopedia = { 1000, 1800, 1500, 1900, 1200, 1300, 1400, 900, 2200, 2000, 1700, 1600 };
		int[] lectoresDiccionario = { 950, 1750, 1600, 1850, 1350, 1450, 1250, 850, 2350, 1950, 1675, 1625 };
		int[] lectoresAtlasMundial = { 1100, 1950, 1700, 2000, 1400, 1600, 1350, 800, 2100, 1900, 1750, 1550 };
		int[] lectoresGuinnessWorldRecords = { 1050, 1900, 1650, 1925, 1300, 1400, 1450, 875, 2250, 2050, 1725, 1575 };
		int[] lectoresElementsOfStyle = { 980, 1740, 1480, 1870, 1250, 1350, 1420, 920, 2380, 2030, 1760, 1590 };

		// Libros Infantiles
		int[] lectoresHarryPotter = { 1000, 1800, 1500, 1900, 1200, 1300, 1400, 900, 2200, 2000, 1700, 1600 };
		int[] lectoresWhereWildThings = { 950, 1750, 1600, 1850, 1350, 1450, 1250, 850, 2350, 1950, 1675, 1625 };
		int[] lectoresHungryCaterpillar = { 1100, 1950, 1700, 2000, 1400, 1600, 1350, 800, 2100, 1900, 1750, 1550 };
		int[] lectoresMatilda = { 1050, 1900, 1650, 1925, 1300, 1400, 1450, 875, 2250, 2050, 1725, 1575 };
		int[] lectoresCharlottesWeb = { 980, 1740, 1480, 1870, 1250, 1350, 1420, 920, 2380, 2030, 1760, 1590 };

		// Libros Divulgativos
		int[] lectoresCosmos = { 1000, 1800, 1500, 1900, 1200, 1300, 1400, 900, 2200, 2000, 1700, 1600 };
		int[] lectoresBriefHistoryTime = { 950, 1750, 1600, 1850, 1350, 1450, 1250, 850, 2350, 1950, 1675, 1625 };
		int[] lectoresSapiens = { 1100, 1950, 1700, 2000, 1400, 1600, 1350, 800, 2100, 1900, 1750, 1550 };
		int[] lectoresPowerOfHabit = { 1050, 1900, 1650, 1925, 1300, 1400, 1450, 875, 2250, 2050, 1725, 1575 };
		int[] lectoresFreakonomics = { 980, 1740, 1480, 1870, 1250, 1350, 1420, 920, 2380, 2030, 1760, 1590 };

		// Libros Técnicos
		int[] lectoresCleanCode = { 1000, 1800, 1500, 1900, 1200, 1300, 1400, 900, 2200, 2000, 1700, 1600 };
		int[] lectoresIntroductionAlgorithms = { 950, 1750, 1600, 1850, 1350, 1450, 1250, 850, 2350, 1950, 1675, 1625 };
		int[] lectoresDesignPatterns = { 1100, 1950, 1700, 2000, 1400, 1600, 1350, 800, 2100, 1900, 1750, 1550 };
		int[] lectoresPragmaticProgrammer = { 1050, 1900, 1650, 1925, 1300, 1400, 1450, 875, 2250, 2050, 1725, 1575 };
		int[] lectoresCrackingCodingInterview = { 980, 1740, 1480, 1870, 1250, 1350, 1420, 920, 2380, 2030, 1760,
				1590 };

		// Creación de libros

		// Libros de Literatura
		Libro cienAniosSoledad = new Libro("Cien años de soledad", 2000, "Gabriel García Márquez", 5.0,lectoresCienAniosSoledad);
		Libro libro1984 = new Libro("1984", 2001, "George Orwell", 4.8, lectores1984);
		Libro senorAnillos = new Libro("El señor de los anillos", 2002, "J.R.R. Tolkien", 4.9, lectoresSenorAnillos);
		Libro orgulloPrejuicio = new Libro("Orgullo y prejuicio", 2003, "Jane Austen", 4.7, lectoresOrgulloPrejuicio);
		Libro donQuijote = new Libro("Don Quijote de la Mancha", 2004, "Miguel de Cervantes", 4.9, lectoresDonQuijote);

		// Libros de Consulta y Referencia
		Libro enciclopedia = new Libro("Enciclopedia Britannica", 3000, "Varios autores", 4.9, lectoresEnciclopedia);
		Libro diccionario = new Libro("Diccionario de la lengua española", 3001, "Real Academia Española", 4.8,
				lectoresDiccionario);
		Libro atlasMundial = new Libro("Atlas mundial", 3002, "Varios autores", 4.7, lectoresAtlasMundial);
		Libro guinnessWorldRecords = new Libro("Guinness World Records", 3003, "Varios autores", 4.7,
				lectoresGuinnessWorldRecords);
		Libro elementsOfStyle = new Libro("The Elements of Style", 3004, "William Strunk Jr. and E.B. White", 4.6,
				lectoresElementsOfStyle);

		// Libros Infantiles
		Libro harryPotter = new Libro("Harry Potter and the Philosopher's Stone", 4000, "J.K. Rowling", 4.9,
				lectoresHarryPotter);
		Libro whereWildThings = new Libro("Where the Wild Things Are", 4001, "Maurice Sendak", 4.8,
				lectoresWhereWildThings);
		Libro hungryCaterpillar = new Libro("The Very Hungry Caterpillar", 4002, "Eric Carle", 4.7,
				lectoresHungryCaterpillar);
		Libro matilda = new Libro("Matilda", 4003, "Roald Dahl", 4.8, lectoresMatilda);
		Libro charlottesWeb = new Libro("Charlotte's Web", 4004, "E.B. White", 4.8, lectoresCharlottesWeb);

		// Libros Divulgativos
		Libro cosmos = new Libro("Cosmos", 5000, "Carl Sagan", 4.9, lectoresCosmos);
		Libro briefHistoryTime = new Libro("A Brief History of Time", 5001, "Stephen Hawking", 4.8,
				lectoresBriefHistoryTime);
		Libro sapiens = new Libro("Sapiens: A Brief History of Humankind", 5002, "Yuval Noah Harari", 4.7,
				lectoresSapiens);
		Libro powerOfHabit = new Libro("The Power of Habit", 5003, "Charles Duhigg", 4.6, lectoresPowerOfHabit);
		Libro freakonomics = new Libro("Freakonomics", 5004, "Steven D. Levitt and Stephen J. Dubner", 4.7,
				lectoresFreakonomics);

		// Libros Técnicos
		Libro cleanCode = new Libro("Clean Code: A Handbook of Agile Software Craftsmanship", 6000, "Robert C. Martin",
				4.9, lectoresCleanCode);
		Libro introductionAlgorithms = new Libro("Introduction to Algorithms", 6001,
				"Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein", 4.8,
				lectoresIntroductionAlgorithms);
		Libro designPatterns = new Libro("Design Patterns: Elements of Reusable Object-Oriented Software", 6002,
				"Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides", 4.7, lectoresDesignPatterns);
		Libro pragmaticProgrammer = new Libro("The Pragmatic Programmer: Your Journey to Mastery", 6003,
				"Andrew Hunt and David Thomas", 4.6, lectoresPragmaticProgrammer);
		Libro crackingCodingInterview = new Libro("Cracking the Coding Interview", 6004, "Gayle Laakmann McDowell", 4.7,
				lectoresCrackingCodingInterview);

		// ADDs

		// Libros de Literatura
		librosLiteratura.add(cienAniosSoledad);
		librosLiteratura.add(libro1984);
		librosLiteratura.add(senorAnillos);
		librosLiteratura.add(orgulloPrejuicio);
		librosLiteratura.add(donQuijote);

		// Libros de Consulta y Referencia
		librosConsultayReferencia.add(enciclopedia);
		librosConsultayReferencia.add(diccionario);
		librosConsultayReferencia.add(atlasMundial);
		librosConsultayReferencia.add(guinnessWorldRecords);
		librosConsultayReferencia.add(elementsOfStyle);

		// Libros Infantiles
		librosInfantiles.add(harryPotter);
		librosInfantiles.add(whereWildThings);
		librosInfantiles.add(hungryCaterpillar);
		librosInfantiles.add(matilda);
		librosInfantiles.add(charlottesWeb);

		// Libros Divulgativos
		librosDivulgativos.add(cosmos);
		librosDivulgativos.add(briefHistoryTime);
		librosDivulgativos.add(sapiens);
		librosDivulgativos.add(powerOfHabit);
		librosDivulgativos.add(freakonomics);

		// Libros Técnicos
		librosTecnicos.add(cleanCode);
		librosTecnicos.add(introductionAlgorithms);
		librosTecnicos.add(designPatterns);
		librosTecnicos.add(pragmaticProgrammer);
		librosTecnicos.add(crackingCodingInterview);

		// Añadimos a la categoria
		CategoriaLibros categoriaLibros = new CategoriaLibros(librosLiteratura, librosConsultayReferencia,
				librosInfantiles, librosDivulgativos, librosTecnicos);
		
		Biblioteca biblioteca = new Biblioteca(categoriaLibros);

		// Utilizar JAXB para marshalling y convertir objetos en XML
		System.out.println("Archivo Biblioteca creado");
		try {
			JAXBContext context = JAXBContext.newInstance(Biblioteca.class);
			Marshaller marshaller = context.createMarshaller();

			// Marshalling de Biblioteca
			System.out.println("llego aqui");
			marshaller.marshal(biblioteca, new File("ArchivoXML/Biblioteca.xml"));
			System.out.println("Archivo generado");

		} catch (JAXBException e) {
			System.out.println("Error al realizar el marshalling de Biblioteca: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocurrió el siguiente error: " + e.getMessage());
		}
		return biblioteca;

	}

	public static String leerXML(String rutaArchivoXML) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File xmlFile = new File(rutaArchivoXML);

			Document document = builder.parse(xmlFile);
			document.getDocumentElement().normalize();

			// Se realiza el procesamiento del documento XML y se devuelve el resultado
			return displayNode(document.getDocumentElement(), "");
		} catch (ParserConfigurationException e) {
			return "Error de configuración del analizador XML: " + e.getMessage();
		} catch (SAXException e) {
			return "Error al analizar el archivo XML: " + e.getMessage();
		} catch (IOException e) {
			return "Error de entrada/salida al leer el arvhico XML: " + e.getMessage();
		} catch (Exception e) {
			return "Error inesperado al leer el archivo XML: " + e.getMessage();
		}
	}

	public static String displayNode(Node node, String indent) {
		StringBuilder result = new StringBuilder();
		try {

			if (node instanceof Element) {
				result.append(indent).append("").append(node.getNodeName()).append("\n");
				NodeList children = node.getChildNodes();
				for (int i = 0; i < children.getLength(); i++) {
					result.append(displayNode(children.item(i), indent + "  "));

				}
			} else if (node instanceof Text) {
				String text = ((Text) node).getWholeText().trim();
				if (!text.isEmpty()) {
					result.append(indent).append(text).append("\n");
				}
			}
		} catch (ClassCastException e) {
			result.append(indent).append("Error en el nodo: ").append(e.getMessage()).append("\n");
		} catch (NullPointerException e) {
			result.append(indent).append("Error en el nodo: Referencia nula").append(e.getMessage()).append("\n");
		}

		return result.toString();
	}

	public static Biblioteca reutilizarXML() {
		Biblioteca biblioteca = null;

		try {
			JAXBContext context = JAXBContext.newInstance(Biblioteca.class);

			Unmarshaller unmarshaller = context.createUnmarshaller();

			biblioteca = (Biblioteca) unmarshaller.unmarshal(new File("ArchivoXML/Biblioteca.xml"));

			System.out.println("Archivo leído con éxito");

		} catch (JAXBException e) {
			System.out.println("Error al deserializar el archivo XML: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocurrió un error inesperado al reutilizar el archivo XML: " + e.getMessage());
		}
		return biblioteca;
	}

	public static void modificarTitulo(Biblioteca biblioteca) {
		String[] opciones = { "Buscar por código", "Buscar por título" };
		int opcionSeleccionada = JOptionPane.showOptionDialog(null,
				"¿Cómo deseas buscar el libro que deseas modificar el título?", "Selecciona una opción",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		if (opcionSeleccionada == -1) {
			// El usuario canceló la selección o presionó Cancelar
			return;
		}

		String nuevoTitulo = JOptionPane.showInputDialog("Introduce el nuevo título: ");

		if (nuevoTitulo == null) {
			// El usuario canceló la entrada o presionó Cancelar
			return;
		}

		if (opcionSeleccionada == 0) { // Buscar por código
			String codigoString = JOptionPane
					.showInputDialog("Introduce el código del libro que deseas modificar el título:");

			if (codigoString == null) {
				return;
			}

			try {
				int codigo = Integer.parseInt(codigoString);
				CategoriaLibros categoriaLibros = biblioteca.getLibros();

				boolean encontrado = false;

				for (Libro libro : categoriaLibros.getLibrosLiteratura()) {
					if (libro.getCodigo() == codigo) {
						libro.setTitulo(nuevoTitulo);
						encontrado = true;
					}
				}


				for (Libro libro : categoriaLibros.getLibrosConsultayReferencia()) {
					if (libro.getCodigo() == codigo) {
						libro.setTitulo(nuevoTitulo);
						encontrado = true;
					}
				}

				for (Libro libro : categoriaLibros.getLibrosInfantiles()) {
					if (libro.getCodigo() == codigo) {
						libro.setTitulo(nuevoTitulo);
						encontrado = true;
					}
				}

				for (Libro libro : categoriaLibros.getLibrosDivulgativos()) {
					if (libro.getCodigo() == codigo) {
						libro.setTitulo(nuevoTitulo);
						encontrado = true;
					}
				}

				for (Libro libro : categoriaLibros.getLibrosDivulgativos()) {
					if (libro.getCodigo() == codigo) {
						libro.setTitulo(nuevoTitulo);
						encontrado = true;
					}
				}

				if (encontrado) {
					JOptionPane.showMessageDialog(null, "Título modificado correctamente!");
				} else {
					JOptionPane.showMessageDialog(null,
							"No se encontró ningun libro con el código " + codigo);
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, introduce un código válido.");
			}
		} else { // Buscar por título
			String tituloBusqueda = JOptionPane
					.showInputDialog("Introduce el título del libro que deseas modificar:");

			if (tituloBusqueda == null) {
				return;
			}

			CategoriaLibros categoriaLibros = biblioteca.getLibros();

			boolean encontrado = false;

			for (Libro libro : categoriaLibros.getLibrosLiteratura()) {
				if (libro.getTitulo().equals(tituloBusqueda)) {
					libro.setTitulo(nuevoTitulo);
					encontrado = true;
				}
			}

			for (Libro libro : categoriaLibros.getLibrosConsultayReferencia()) {
				if (libro.getTitulo().equals(tituloBusqueda)) {
					libro.setTitulo(nuevoTitulo);
					encontrado = true;
				}
			}

			for (Libro libro : categoriaLibros.getLibrosInfantiles()) {
				if (libro.getTitulo().equals(tituloBusqueda)) {
					libro.setTitulo(nuevoTitulo);
					encontrado = true;
				}
			}

			for (Libro libro : categoriaLibros.getLibrosDivulgativos()) {
				if (libro.getTitulo().equals(tituloBusqueda)) {
					libro.setTitulo(nuevoTitulo);
					encontrado = true;
				}
			}

			for (Libro libro : categoriaLibros.getLibrosTecnicos()) {
				if (libro.getTitulo().equals(tituloBusqueda)) {
					libro.setTitulo(nuevoTitulo);
					encontrado = true;
				}
			}

			if (encontrado) {
				JOptionPane.showMessageDialog(null, "Título modificado correctamente!");
			} else {
				JOptionPane.showMessageDialog(null,
						"No se encontró ningun libro con el título " + tituloBusqueda);
			}
		}

		try {
			JAXBContext context = JAXBContext.newInstance(Biblioteca.class);
			Marshaller marshaller = context.createMarshaller();

			// Marshalling de Biblioteca
			marshaller.marshal(biblioteca, new File("ArchivoXML/Biblioteca.xml"));

			System.out.println("achivo realisado");
		} catch (JAXBException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void lectoresAnuales(Biblioteca biblioteca) {
		String[] opciones = { "Buscar por código", "Buscar por título" };
		double lectoresAnuales = 0;
		double porcentaje = 0;
		String respuesta = "";
		double espectadoresTotales = lectoresTotales(biblioteca);
		int opcionSeleccionada = JOptionPane.showOptionDialog(null, "¿Cómo deseas buscar el libro?",
				"Selecciona una opción", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,
				opciones[0]);

		if (opcionSeleccionada == -1) {
			// El usuario canceló la selección o presionó Cancelar
			return;
		}

		if (opcionSeleccionada == 0) { // Buscar por código
			String codigoString = JOptionPane.showInputDialog("Introduce el código de la película");

			if (codigoString == null) {
				return;
			}

			try {
				int codigo = Integer.parseInt(codigoString);
				CategoriaLibros categoriaLibros = biblioteca.getLibros();

				boolean encontrado = true;

				// Busca en las listas de películas
				for (Libro libro : categoriaLibros.getLibrosLiteratura()) {
					if (libro.getCodigo() == codigo) {
						lectoresAnuales = sumarlectoresAnuales(libro.getLectores());
						porcentaje = (lectoresAnuales / espectadoresTotales) * 100;
						respuesta = "Los espectadores anuales de " + libro.getTitulo() + " han sido: " + lectoresAnuales
								+ " , representando un " + porcentaje + "% del total.";
						JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
						encontrado = true;
					}
				}

				for (Libro libro : categoriaLibros.getLibrosConsultayReferencia()) {
					if (libro.getCodigo() == codigo) {
						lectoresAnuales = sumarlectoresAnuales(libro.getLectores());
						porcentaje = (lectoresAnuales / espectadoresTotales) * 100;
						respuesta = "Los espectadores anuales de " + libro.getTitulo() + " han sido: " + lectoresAnuales
								+ " , representando un " + porcentaje + "% del total.";
						JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
						encontrado = true;
					}
				}

				for (Libro libro : categoriaLibros.getLibrosInfantiles()) {
					if (libro.getCodigo() == codigo) {
						lectoresAnuales = sumarlectoresAnuales(libro.getLectores());
						porcentaje = (lectoresAnuales / espectadoresTotales) * 100;
						respuesta = "Los espectadores anuales de " + libro.getTitulo() + " han sido: " + lectoresAnuales
								+ " , representando un " + porcentaje + "% del total.";
						JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
						encontrado = true;
					}
				}

				for (Libro libro : categoriaLibros.getLibrosDivulgativos()) {
					if (libro.getCodigo() == codigo) {
						lectoresAnuales = sumarlectoresAnuales(libro.getLectores());
						porcentaje = (lectoresAnuales / espectadoresTotales) * 100;
						respuesta = "Los espectadores anuales de " + libro.getTitulo() + " han sido: " + lectoresAnuales
								+ " , representando un " + porcentaje + "% del total.";
						JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
						encontrado = true;
					}
				}

				for (Libro libro : categoriaLibros.getLibrosTecnicos()) {
					if (libro.getCodigo() == codigo) {
						lectoresAnuales = sumarlectoresAnuales(libro.getLectores());
						porcentaje = (lectoresAnuales / espectadoresTotales) * 100;
						respuesta = "Los espectadores anuales de " + libro.getTitulo() + " han sido: " + lectoresAnuales
								+ " , representando un " + porcentaje + "% del total.";
						JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
						encontrado = true;
					}
				}

				if (!encontrado) {
					JOptionPane.showMessageDialog(null,
							"No se encontró ningun libro con el código " + codigo);
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, introduce un código válido.");
			}
		} else { // Buscar por título
			String tituloBusqueda = JOptionPane.showInputDialog("Introduce el título de la película");

			if (tituloBusqueda == null) {
				return;
			}
			try {
				CategoriaLibros categoriaLibros = biblioteca.getLibros();

				boolean encontrado = true;

				// Busca en las listas de películas
				for (Libro libro : categoriaLibros.getLibrosLiteratura()) {
					if (libro.getTitulo().equals(tituloBusqueda)) {
						lectoresAnuales = sumarlectoresAnuales(libro.getLectores());
						porcentaje = (lectoresAnuales / espectadoresTotales) * 100;
						respuesta = "Los espectadores anuales de " + libro.getTitulo() + " han sido: " + lectoresAnuales
								+ " , representando un " + porcentaje + "% del total.";
						JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
						encontrado = true;

					}
				}

				for (Libro libro : categoriaLibros.getLibrosConsultayReferencia()) {
					if (libro.getTitulo().equals(tituloBusqueda)) {
						lectoresAnuales = sumarlectoresAnuales(libro.getLectores());
						porcentaje = (lectoresAnuales / espectadoresTotales) * 100;
						respuesta = "Los espectadores anuales de " + libro.getTitulo() + " han sido: " + lectoresAnuales
								+ " , representando un " + porcentaje + "% del total.";
						JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
						encontrado = true;
					}
				}

				for (Libro libro : categoriaLibros.getLibrosInfantiles()) {
					if (libro.getTitulo().equals(tituloBusqueda)) {
						lectoresAnuales = sumarlectoresAnuales(libro.getLectores());
						porcentaje = (lectoresAnuales / espectadoresTotales) * 100;
						respuesta = "Los espectadores anuales de " + libro.getTitulo() + " han sido: " + lectoresAnuales
								+ " , representando un " + porcentaje + "% del total.";
						JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
						encontrado = true;
					}
				}

				for (Libro libro : categoriaLibros.getLibrosDivulgativos()) {
					if (libro.getTitulo().equals(tituloBusqueda)) {
						lectoresAnuales = sumarlectoresAnuales(libro.getLectores());
						porcentaje = (lectoresAnuales / espectadoresTotales) * 100;
						respuesta = "Los espectadores anuales de " + libro.getTitulo() + " han sido: " + lectoresAnuales
								+ " , representando un " + porcentaje + "% del total.";
						JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
						encontrado = true;
					}
				}

				for (Libro libro : categoriaLibros.getLibrosTecnicos()) {
					if (libro.getTitulo().equals(tituloBusqueda)) {
						lectoresAnuales = sumarlectoresAnuales(libro.getLectores());
						porcentaje = (lectoresAnuales / espectadoresTotales) * 100;
						respuesta = "Los espectadores anuales de " + libro.getTitulo() + " han sido: " + lectoresAnuales
								+ " , representando un " + porcentaje + "% del total.";
						JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
						encontrado = true;
					}
				}

				if (!encontrado) {
					JOptionPane.showMessageDialog(null,
							"No se encontró ningun libro con el titulo " + tituloBusqueda);
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, introduce un titulo válido.");
			}
		}
	}

	// Método auxiliar para sumar los valores de un array
	private static int sumarlectoresAnuales(int[] espectadores) {
		int suma = 0;
		try {

			for (int espectadoresmes : espectadores) {
				suma += espectadoresmes;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return suma;
	}

	public static double lectoresTotales(Biblioteca biblioteca) {
		double lectoresTotales = 0;

		try {

			for (Libro libro : biblioteca.getLibros().getLibrosLiteratura()) {
				lectoresTotales += sumarlectoresAnuales(libro.getLectores());
			}
			for (Libro libro : biblioteca.getLibros().getLibrosConsultayReferencia()) {
				lectoresTotales += sumarlectoresAnuales(libro.getLectores());
			}
			for (Libro libro : biblioteca.getLibros().getLibrosInfantiles()) {
				lectoresTotales += sumarlectoresAnuales(libro.getLectores());
			}
			for (Libro libro : biblioteca.getLibros().getLibrosDivulgativos()) {
				lectoresTotales += sumarlectoresAnuales(libro.getLectores());
			}
			for (Libro libro : biblioteca.getLibros().getLibrosTecnicos()) {
				lectoresTotales += sumarlectoresAnuales(libro.getLectores());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Devolvemos el dato
		System.out.println(lectoresTotales);
		return lectoresTotales;
	}

	public static void agregarLibros(Biblioteca biblioteca) {

		try {
			String titulo = JOptionPane.showInputDialog("Introduce el título del libro:");
			if (titulo == null)
				return; // Usuario canceló

			String codigoStr = JOptionPane.showInputDialog("Introduce el código del libro:");
			if (codigoStr == null)
				return;
			int codigo = Integer.parseInt(codigoStr);

			String autor = JOptionPane.showInputDialog("Introduce el nombre del autor:");
			if (autor == null)
				return;

			String notaStr = JOptionPane.showInputDialog("Introduce la nota del libro:");
			if (notaStr == null)
				return;
			double nota = Double.parseDouble(notaStr);

			int[] lectores = new int[12];
			for (int i = 0; i < 12; i++) {
				lectores[i] = 0;
			}

			Libro nuevoLibro = new Libro(titulo, codigo, autor, nota, lectores);

			String categoria = JOptionPane.showInputDialog("Introduce la categoría del libro:").toLowerCase();
			if (categoria == null)
				return;

			CategoriaLibros categoriaLibros = biblioteca.getLibros();
			List<Libro> listaCategoria = null;

			switch (categoria) {
			case "literatura":
				listaCategoria = categoriaLibros.getLibrosLiteratura();
				break;
			case "consulta y referencia":
				listaCategoria = categoriaLibros.getLibrosConsultayReferencia();
				break;
			case "infantiles":
				listaCategoria = categoriaLibros.getLibrosInfantiles();
				break;
			case "divulgativos":
				listaCategoria = categoriaLibros.getLibrosDivulgativos();
				break;
			case "tecnicos":
				listaCategoria = categoriaLibros.getLibrosTecnicos();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Categoría no válida");
				return;
			}

			listaCategoria.add(nuevoLibro);

			JOptionPane.showMessageDialog(null, "Libro agregado a la lista de " + categoria);

			try {
				JAXBContext context = JAXBContext.newInstance(Biblioteca.class);
				Marshaller marshaller = context.createMarshaller();

				// Marshalling de Biblioteca
				marshaller.marshal(biblioteca, new File("ArchivoXML/Biblioteca.xml"));

				System.out.println("Archivo actualizado");
			} catch (JAXBException e) {
				e.getMessage();
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Error en la conversión de datos. Asegúrate de ingresar números válidos.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado. Verifica los datos ingresados.");
		}
	}
	
	public static String obtenerInfoPorTitulo(Biblioteca biblioteca, String titulo) {
		StringBuilder info = new StringBuilder();

		CategoriaLibros categoriaLibros = biblioteca.getLibros();

		try {

			// Buscar en las listas de películas
			for (Libro libro : categoriaLibros.getLibrosLiteratura()) {
			    if (libro.getTitulo().equalsIgnoreCase(titulo)) {
			        info.append("Título: ").append(libro.getTitulo()).append("\n");
			        info.append("Código: ").append(libro.getCodigo()).append("\n");
			        info.append("Autor: ").append(libro.getAutor()).append("\n");
			        info.append("Nota: ").append(libro.getNota()).append("\n");
			        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
			        for (int i = 0; i < 12; i++) {
			            info.append(meses[i]).append(": ").append(libro.getLectores()[i]).append("\n");
			        }
			        info.append("Categoría: Técnico").append("\n");
			        return info.toString();
			    }
			}
			
			for (Libro libro : categoriaLibros.getLibrosConsultayReferencia()) {
			    if (libro.getTitulo().equalsIgnoreCase(titulo)) {
			        info.append("Título: ").append(libro.getTitulo()).append("\n");
			        info.append("Código: ").append(libro.getCodigo()).append("\n");
			        info.append("Autor: ").append(libro.getAutor()).append("\n");
			        info.append("Nota: ").append(libro.getNota()).append("\n");
			        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
			        for (int i = 0; i < 12; i++) {
			            info.append(meses[i]).append(": ").append(libro.getLectores()[i]).append("\n");
			        }
			        info.append("Categoría: Técnico").append("\n");
			        return info.toString();
			    }
			}
			
			
			for (Libro libro : categoriaLibros.getLibrosInfantiles()) {
			    if (libro.getTitulo().equalsIgnoreCase(titulo)) {
			        info.append("Título: ").append(libro.getTitulo()).append("\n");
			        info.append("Código: ").append(libro.getCodigo()).append("\n");
			        info.append("Autor: ").append(libro.getAutor()).append("\n");
			        info.append("Nota: ").append(libro.getNota()).append("\n");
			        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
			        for (int i = 0; i < 12; i++) {
			            info.append(meses[i]).append(": ").append(libro.getLectores()[i]).append("\n");
			        }
			        info.append("Categoría: Técnico").append("\n");
			        return info.toString();
			    }
			}
			
			
			for (Libro libro : categoriaLibros.getLibrosDivulgativos()) {
			    if (libro.getTitulo().equalsIgnoreCase(titulo)) {
			        info.append("Título: ").append(libro.getTitulo()).append("\n");
			        info.append("Código: ").append(libro.getCodigo()).append("\n");
			        info.append("Autor: ").append(libro.getAutor()).append("\n");
			        info.append("Nota: ").append(libro.getNota()).append("\n");
			        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
			        for (int i = 0; i < 12; i++) {
			            info.append(meses[i]).append(": ").append(libro.getLectores()[i]).append("\n");
			        }
			        info.append("Categoría: Técnico").append("\n");
			        return info.toString();
			    }
			}
			
			for (Libro libro : categoriaLibros.getLibrosTecnicos()) {
			    if (libro.getTitulo().equalsIgnoreCase(titulo)) {
			        info.append("Título: ").append(libro.getTitulo()).append("\n");
			        info.append("Código: ").append(libro.getCodigo()).append("\n");
			        info.append("Autor: ").append(libro.getAutor()).append("\n");
			        info.append("Nota: ").append(libro.getNota()).append("\n");
			        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
			        for (int i = 0; i < 12; i++) {
			            info.append(meses[i]).append(": ").append(libro.getLectores()[i]).append("\n");
			        }
			        info.append("Categoría: Técnico").append("\n");
			        return info.toString();
			    }
			}

		} catch (Exception e) {
			return "Ocurrió un error al nuscar la información";
		}
		return "No se encontró ningun libro con el título: " + titulo;
	}
	
	public static void eliminarPorCodigo(Biblioteca biblioteca) {
		String[] opciones = {"Buscar por código", "Buscar por título"};
		int opcionSeleccionada = JOptionPane.showOptionDialog(null, "¿Cómo deseas buscar el libro que deseas eliminar?",
				"Selecciona una opción", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		if (opcionSeleccionada == -1) {
			// El usuario canceló la selección o presionó Cancelar
			return;
		}

		if (opcionSeleccionada == 0) { // Buscar por código
			String codigoString = JOptionPane.showInputDialog("Introduce el código del libro que deseas eliminar");

			if (codigoString == null) {
				return;
			}

			try {
				int codigo = Integer.parseInt(codigoString);
				CategoriaLibros categoriaLibros = biblioteca.getLibros();
				
				boolean encontrado = false;
				boolean libroEliminado= false;
				

				// Buscar en las listas de películas
				
				//Literatura
				for (Libro libro : categoriaLibros.getLibrosLiteratura()) {
					if (libro.getCodigo() == codigo) {
						categoriaLibros.getLibrosLiteratura().remove(libro);
						libroEliminado=true;
						encontrado=true;
						JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " eliminado.");
						break;
					}
				}
				
				//Consulta y Referencia
				for (Libro libro : categoriaLibros.getLibrosConsultayReferencia()) {
					if (libro.getCodigo() == codigo) {
						categoriaLibros.getLibrosConsultayReferencia().remove(libro);
						libroEliminado = true;
						encontrado=true;
						JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " eliminado.");
						break;
					}
				}
				
				//Infantiles
				for (Libro libro : categoriaLibros.getLibrosInfantiles()) {
					if (libro.getCodigo() == codigo) {
						categoriaLibros.getLibrosInfantiles().remove(libro);
						libroEliminado = true;
						encontrado=true;
						JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " eliminado.");
						break;
					}
				}
				
				//Divulgativos
				for (Libro libro : categoriaLibros.getLibrosDivulgativos()) {
					if (libro.getCodigo() == codigo) {
						categoriaLibros.getLibrosDivulgativos().remove(libro);
						libroEliminado = true;
						encontrado=true;
						JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " eliminado.");
						break;
					}
				}

				//Tecnicos
				for (Libro libro : categoriaLibros.getLibrosTecnicos()) {
					if (libro.getCodigo() == codigo) {
						categoriaLibros.getLibrosTecnicos().remove(libro);
						libroEliminado = true;
						encontrado=true;
						JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " eliminado.");
						break;
					}
				}
				
				if (!libroEliminado) {
					JOptionPane.showMessageDialog(null, "No se encontró ninguna libro con el código " + codigo);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, introduce un código válido.");
			}
		}else { // Buscar por título
			String tituloBusqueda = JOptionPane.showInputDialog("Introduce el título del libro");

			if (tituloBusqueda == null) {
				return;
			}
			try {
				CategoriaLibros categoriaLibros = biblioteca.getLibros();
			
				boolean encontrado=false;
				boolean libroEliminado=false;
				

				//Literatura
				for (Libro libro : categoriaLibros.getLibrosLiteratura()) {
					if (libro.getTitulo().equals(tituloBusqueda)) {
						categoriaLibros.getLibrosLiteratura().remove(libro);
						libroEliminado=true;
						encontrado=true;
						JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " eliminado.");
						break;
					}
				}
				
				//Consulta y Referencia
				for (Libro libro : categoriaLibros.getLibrosConsultayReferencia()) {
					if (libro.getTitulo().equals(tituloBusqueda)) {
						categoriaLibros.getLibrosConsultayReferencia().remove(libro);
						libroEliminado = true;
						encontrado=true;
						JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " eliminado.");
						break;
					}
				}
				
				//Infantiles
				for (Libro libro : categoriaLibros.getLibrosInfantiles()) {
					if (libro.getTitulo().equals(tituloBusqueda)) {
						categoriaLibros.getLibrosInfantiles().remove(libro);
						libroEliminado = true;
						encontrado=true;
						JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " eliminado.");
						break;
					}
				}
				
				//Divulgativos
				for (Libro libro : categoriaLibros.getLibrosDivulgativos()) {
					if (libro.getTitulo().equals(tituloBusqueda)) {
						categoriaLibros.getLibrosDivulgativos().remove(libro);
						libroEliminado = true;
						encontrado=true;
						JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " eliminado.");
						break;
					}
				}

				//Tecnicos
				for (Libro libro : categoriaLibros.getLibrosTecnicos()) {
					if (libro.getTitulo().equals(tituloBusqueda)) {
						categoriaLibros.getLibrosTecnicos().remove(libro);
						libroEliminado = true;
						encontrado=true;
						JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " eliminado.");
						break;
					}
				}
			
				if (!libroEliminado) {
					JOptionPane.showMessageDialog(null, "No se encontró ninguna libro con el título " + tituloBusqueda);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, introduce un título válido.");
			}
		}

		try {
			JAXBContext context = JAXBContext.newInstance(Biblioteca.class);
			Marshaller marshaller = context.createMarshaller();

			// Marshalling de Biblioteca
			marshaller.marshal(biblioteca, new File("ArchivoXML/Biblioteca.xml"));


			System.out.println("archivo actualizado");
		} catch (JAXBException e) {
			e.getMessage();
		}
	}

}
