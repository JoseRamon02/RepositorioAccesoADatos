package Biblio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class InterfazBiblioteca {

	// ATRIBUTOS
	static Biblioteca biblioteca = new Biblioteca();

	private static JFrame frame;
	private static JPanel panelConFondo;
	private static JButton botonAnimado = new JButton("Bienvenido a la Biblioteca");
	private static GridBagConstraints constraints;
	private static JPanel panelBotonesLibros = new JPanel(new GridBagLayout());
	private static JPanel panelBotones = new JPanel(new GridBagLayout());
	private static BufferedImage imagenFondo = null;
	private static Dimension dimensionFija = new Dimension(200, 80);;
	private static JTextArea textArea;
	private static JScrollPane scrollPane;
	private static JButton botonLibros = new JButton("Libros");
	private static JButton botonMostrarXML = new JButton("Mostrar xml");
	private static JButton botonOtrosDatos = new JButton("Otros Datos");
	private static JButton botonDesplegarLiteratura = new JButton();
	private static JButton botonDesplegarConsultayReferencia = new JButton();
	private static JButton botonDesplegarInfantiles = new JButton();
	private static JButton botonDesplegarDivulgativos = new JButton();
	private static JButton botonDesplegarTecnicos = new JButton();

	private static JButton btnModificarTitulo = new JButton("<html>Modificar<br>Título</html>");
	private static JButton btnAgregarLibro = new JButton("<html>Agregar<br>Película</html>");
	private static JButton btnEliminarContenido = new JButton("<html>Eliminar<br>Contenido</html>");
	private static JButton btnLectores = new JButton("<html>Lectores<br>Anuales</html>");

	// Opciones de los desplegables
	private static String[] opcionesLibrosLiteratura = { "Cien años de soledad", "1984", "El señor de los anillos",
			"Orgullo y prejuicio", "Don Quijote de la Mancha" };
	private static String[] opcionesLibrosConsultayReferencia = { "Enciclopedia Britannica",
			"Diccionario de la lengua española", "Atlas mundial", "Guinness World Records", "The Elements of Style" };
	private static String[] opcionesLibrosInfantiles = { "Harry Potter and the Philosopher's Stone",
			"Where the Wild Things Are", "The Very Hungry Caterpillar", "Matilda", "Charlotte's Web" };
	private static String[] opcionesLibrosDivulgativos = { "Cosmos", "A Brief History of Time",
			"Sapiens: A Brief History of Humankind", "The Power of Habit", "Freakonomics" };
	private static String[] opcionesLibrosTecnicos = { "Clean Code", "Introduction to Algorithms", "Design Patterns",
			"The Pragmatic Programmer", "Cracking the Coding Interview" };

	// JComboBox para las películas
	private static JComboBox<String> desplegableLibrosLiteratura = new JComboBox<>(opcionesLibrosLiteratura);
	private static JComboBox<String> desplegableLibrosConsultayReferencia = new JComboBox<>(opcionesLibrosConsultayReferencia);
	private static JComboBox<String> desplegableLibrosInfantiles = new JComboBox<>(opcionesLibrosInfantiles);
	private static JComboBox<String> desplegableLibrosDivulgativos = new JComboBox<>(opcionesLibrosDivulgativos);
	private static JComboBox<String> desplegableLibrosTecnicos = new JComboBox<>(opcionesLibrosTecnicos);

	// Añadimos un boton que nos permita volver al inicio del programa
	private static JButton btnVolver = new JButton("volver al inicio");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazBiblioteca window = new InterfazBiblioteca();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfazBiblioteca() {
		initialize();
	}

	// MÉTODOS PARA LA FUNCIONALIDAD DE LA INTERFAZ GRÁFICA

	public static void initialize() {
		frame = new JFrame("Bienvenido a la Biblioteca");
		frame.setSize(1150, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		File archivo = new File("ArchivoXML/Biblioteca.xml");

		if (archivo.exists()) {
			biblioteca = Metodos.reutilizarXML();
		} else {
			
			biblioteca = Metodos.crearXML();
		}

		try {
			imagenFondo = ImageIO.read(new File("IMG\\portadaMM.jpg"));
			panelConFondo = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
			panelConFondo = new JPanel();
		}

		botonAnimado.setBackground(new Color(0, 123, 255));
		botonAnimado.setForeground(Color.WHITE);
		botonAnimado.setFocusPainted(false);
		botonAnimado.setBorderPainted(false);
		botonAnimado.setOpaque(true);

		botonAnimado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Creamos la estructura del XML
				abrirXML();
				botonAnimado.setVisible(false);
				botonAnimado.setEnabled(false);
				btnVolver.setVisible(true);
				btnVolver.setEnabled(true);
			}
		});

		panelConFondo.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(150, 0, 0, 0);

		panelConFondo.add(botonAnimado, constraints);

		btnVolver.setBackground(new Color(255, 0, 0));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setOpaque(true);
		btnVolver.setVisible(false);
		btnVolver.setEnabled(false);

		// Agrega un ActionListener al botón "Volver al Inicio"
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Llama a un método para volver a la interfaz principal
				volverAInterfazPrincipal();
			}
		});

		frame.setLayout(new BorderLayout());

		// Configura la posición del botón en el panel
		constraints.gridx = 0;
		constraints.gridy = 1; // Colócalo debajo de los demás componentes
		constraints.insets = new Insets(10, 0, 10, 0); // Ajustamos los márgenes

		// Agrega el botón "Volver al Inicio" al panel
		panelConFondo.add(btnVolver, constraints);

		frame.add(panelConFondo);
	}

	public static void abrirXML() {
		GridBagConstraints constraints = new GridBagConstraints();

		botonLibros = new JButton("Libros");

		botonMostrarXML = new JButton("Mostrar xml");
		botonOtrosDatos = new JButton("Otros Datos");

		Color colorFijo = new Color(0, 80, 250);
		botonLibros.setBackground(colorFijo);
		botonMostrarXML.setBackground(colorFijo);
		botonOtrosDatos.setBackground(colorFijo);

		botonLibros.setForeground(Color.WHITE);
		botonMostrarXML.setForeground(Color.WHITE);
		botonOtrosDatos.setForeground(Color.WHITE);

		botonLibros.setFocusPainted(false);

		botonMostrarXML.setFocusPainted(false);
		botonOtrosDatos.setFocusPainted(false);

		botonLibros.setBorderPainted(false);

		botonMostrarXML.setBorderPainted(false);
		botonOtrosDatos.setBorderPainted(false);

		botonLibros.setOpaque(true);

		botonMostrarXML.setOpaque(true);
		botonOtrosDatos.setOpaque(true);

		botonLibros.setPreferredSize(dimensionFija);

		botonMostrarXML.setPreferredSize(dimensionFija);
		botonOtrosDatos.setPreferredSize(dimensionFija);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(20, 20, 20, 20);
		panelBotonesLibros.add(botonLibros, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panelBotonesLibros.add(botonMostrarXML, constraints);

		constraints.gridx = 1;
		panelBotonesLibros.add(botonOtrosDatos, constraints);

		panelBotonesLibros.setOpaque(false);

		botonLibros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDesplegablesPeliculas();
				botonLibros.setVisible(false);
				botonMostrarXML.setVisible(false);
				botonOtrosDatos.setVisible(false);
				btnVolver.setVisible(true);
				btnVolver.setEnabled(true);

				// Volvemos a activar los desplegables de las películas
				desplegableLibrosLiteratura.setVisible(true);
				desplegableLibrosLiteratura.setEnabled(true);

				desplegableLibrosConsultayReferencia.setVisible(true);
				desplegableLibrosConsultayReferencia.setEnabled(true);

				desplegableLibrosInfantiles.setVisible(true);
				desplegableLibrosInfantiles.setEnabled(true);

				desplegableLibrosDivulgativos.setVisible(true);
				desplegableLibrosDivulgativos.setEnabled(true);

				desplegableLibrosTecnicos.setVisible(true);
				desplegableLibrosTecnicos.setEnabled(true);
			}
		});

		botonMostrarXML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String mostrar = Metodos.leerXML("Biblioteca.xml");
				textArea = new JTextArea(20, 40); // Crear un JTextArea con 20 filas y 40 columnas
				textArea.setText(mostrar);

				scrollPane = new JScrollPane(textArea);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				JOptionPane.showMessageDialog(frame, scrollPane, "Biblioteca", JOptionPane.INFORMATION_MESSAGE);

				botonLibros.setVisible(true);
				botonMostrarXML.setVisible(true);
				botonOtrosDatos.setVisible(true);
			}
		});

		botonOtrosDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desplegarOtrosDatos();
				botonLibros.setVisible(false);
				botonMostrarXML.setVisible(false);
				botonOtrosDatos.setVisible(false);
				btnVolver.setVisible(true);
				btnVolver.setEnabled(true);
			}
		});

		panelConFondo.add(panelBotonesLibros);
		panelConFondo.revalidate();
	}

	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	////////// MODIFICAR IMAGENES///////////////////////
	///////////////////////////////////////////////////
	////////////////////////////////////////////////////

	public static void agregarDesplegablesPeliculas() {
		GridBagConstraints constraints = new GridBagConstraints();

		// Literatura
		botonDesplegarLiteratura.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionesLibrosLiteratura = new String[biblioteca.getLibros().getLibrosLiteratura().size()];
				for (int i = 0; i < biblioteca.getLibros().getLibrosLiteratura().size(); i++) {
					opcionesLibrosLiteratura[i] = biblioteca.getLibros().getLibrosLiteratura().get(i).getTitulo();
				}
				desplegableLibrosLiteratura.setModel(new DefaultComboBoxModel<>(opcionesLibrosLiteratura));
				desplegableLibrosLiteratura.showPopup();
			}
		});

		// Consulta y Referencia
		botonDesplegarConsultayReferencia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionesLibrosConsultayReferencia = new String[biblioteca.getLibros().getLibrosConsultayReferencia()
						.size()];
				for (int i = 0; i < biblioteca.getLibros().getLibrosConsultayReferencia().size(); i++) {
					opcionesLibrosConsultayReferencia[i] = biblioteca.getLibros().getLibrosConsultayReferencia().get(i)
							.getTitulo();
				}
				desplegableLibrosConsultayReferencia.setModel(new DefaultComboBoxModel<>(opcionesLibrosConsultayReferencia));
				desplegableLibrosConsultayReferencia.showPopup();
			}
		});

		// Infantiles
		botonDesplegarInfantiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionesLibrosInfantiles = new String[biblioteca.getLibros().getLibrosInfantiles().size()];
				for (int i = 0; i < biblioteca.getLibros().getLibrosInfantiles().size(); i++) {
					opcionesLibrosInfantiles[i] = biblioteca.getLibros().getLibrosInfantiles().get(i).getTitulo();
				}
				desplegableLibrosInfantiles.setModel(new DefaultComboBoxModel<>(opcionesLibrosInfantiles));
				desplegableLibrosInfantiles.showPopup();
			}
		});

		// Divulgativos
		botonDesplegarDivulgativos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionesLibrosDivulgativos = new String[biblioteca.getLibros().getLibrosDivulgativos().size()];
				for (int i = 0; i < biblioteca.getLibros().getLibrosDivulgativos().size(); i++) {
					opcionesLibrosDivulgativos[i] = biblioteca.getLibros().getLibrosDivulgativos().get(i).getTitulo();
				}
				desplegableLibrosDivulgativos.setModel(new DefaultComboBoxModel<>(opcionesLibrosDivulgativos));
				desplegableLibrosDivulgativos.showPopup();
			}
		});

		// Tecnicos
		botonDesplegarTecnicos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionesLibrosTecnicos = new String[biblioteca.getLibros().getLibrosTecnicos().size()];
				for (int i = 0; i < biblioteca.getLibros().getLibrosTecnicos().size(); i++) {
					opcionesLibrosTecnicos[i] = biblioteca.getLibros().getLibrosTecnicos().get(i).getTitulo();
				}
				desplegableLibrosTecnicos.setModel(new DefaultComboBoxModel<>(opcionesLibrosTecnicos));
				desplegableLibrosTecnicos.showPopup();
			}
		});

//////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////MODIFICAR IMAGENES///////////////////////
///////////////////////////////////////////////////
////////////////////////////////////////////////////
		desplegableLibrosLiteratura.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String libroSeleccionado = (String) desplegableLibrosLiteratura.getSelectedItem();

				// Obtenemos la información de la película
				String infoLibro = Metodos.obtenerInfoPorTitulo(biblioteca, libroSeleccionado);

				// Mostramos la información en un diálogo de mensaje (JOptionPane)
				JOptionPane.showMessageDialog(frame, infoLibro, "Información del libro: " + libroSeleccionado,
						JOptionPane.INFORMATION_MESSAGE);

			}
		});

		desplegableLibrosConsultayReferencia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String libroSeleccionado = (String) desplegableLibrosConsultayReferencia.getSelectedItem();

				// Obtenemos la información de la película
				String infoLibro = Metodos.obtenerInfoPorTitulo(biblioteca, libroSeleccionado);

		

				// Mostramos la información en un diálogo de mensaje (JOptionPane)
				JOptionPane.showMessageDialog(frame, infoLibro, "Información del libro: " + libroSeleccionado,
						JOptionPane.INFORMATION_MESSAGE);

			}
		});

		desplegableLibrosInfantiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String libroSeleccionado = (String) desplegableLibrosInfantiles.getSelectedItem();

				// Obtenemos la información de la película
				String infoLibro = Metodos.obtenerInfoPorTitulo(biblioteca, libroSeleccionado);


				// Mostramos la información en un diálogo de mensaje (JOptionPane)
				JOptionPane.showMessageDialog(frame, infoLibro, "Información del libro: " + libroSeleccionado,
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		desplegableLibrosDivulgativos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String libroSeleccionado = (String) desplegableLibrosDivulgativos.getSelectedItem();

				// Obtenemos la información de la película
				String infoLibro = Metodos.obtenerInfoPorTitulo(biblioteca, libroSeleccionado);

			
				// Mostramos la información en un diálogo de mensaje (JOptionPane)
				JOptionPane.showMessageDialog(frame, infoLibro, "Información del libro: " + libroSeleccionado,
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		desplegableLibrosTecnicos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String libroSeleccionado = (String) desplegableLibrosTecnicos.getSelectedItem();

				// Obtenemos la información de la película
				String infoLibro = Metodos.obtenerInfoPorTitulo(biblioteca, libroSeleccionado);

			
				// Mostramos la información en un diálogo de mensaje (JOptionPane)
				JOptionPane.showMessageDialog(frame, infoLibro, "Información del libro: " + libroSeleccionado,
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		((JLabel) desplegableLibrosLiteratura.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) desplegableLibrosConsultayReferencia.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) desplegableLibrosInfantiles.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) desplegableLibrosDivulgativos.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) desplegableLibrosTecnicos.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		desplegableLibrosLiteratura.setPreferredSize(dimensionFija);
		desplegableLibrosConsultayReferencia.setPreferredSize(dimensionFija);
		desplegableLibrosInfantiles.setPreferredSize(dimensionFija);
		desplegableLibrosDivulgativos.setPreferredSize(dimensionFija);
		desplegableLibrosTecnicos.setPreferredSize(dimensionFija);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 20, 20, 20);

		panelBotonesLibros.add(desplegableLibrosLiteratura, constraints);
		constraints.gridx = 1;
		panelBotonesLibros.add(desplegableLibrosConsultayReferencia, constraints);
		constraints.gridx = 2;
		panelBotonesLibros.add(desplegableLibrosInfantiles, constraints);
		constraints.gridx = 3;
		panelBotonesLibros.add(desplegableLibrosDivulgativos, constraints);
		constraints.gridx = 4;
		panelBotonesLibros.add(desplegableLibrosTecnicos, constraints);

		constraints.gridx = 0;
		panelBotonesLibros.add(desplegableLibrosLiteratura, constraints);
		constraints.gridx = 1;
		panelBotonesLibros.add(desplegableLibrosConsultayReferencia, constraints);
		constraints.gridx = 2;
		panelBotonesLibros.add(desplegableLibrosInfantiles, constraints);
		constraints.gridx = 3;
		panelBotonesLibros.add(desplegableLibrosDivulgativos, constraints);
		constraints.gridx = 3;
		panelBotonesLibros.add(desplegableLibrosTecnicos, constraints);

		panelConFondo.revalidate();
	}


	public static void desplegarOtrosDatos() {
		frame.getContentPane().add(panelBotones, BorderLayout.CENTER);
		GridBagConstraints constraints = new GridBagConstraints();
		panelBotones.setVisible(true);
		panelBotones.setEnabled(true);

		Color colorFijo = new Color(0, 80, 250);
		btnModificarTitulo.setBackground(colorFijo);
		btnAgregarLibro.setBackground(colorFijo);
		btnEliminarContenido.setBackground(colorFijo);
		btnLectores.setBackground(colorFijo);

		btnModificarTitulo.setForeground(Color.WHITE);
		btnAgregarLibro.setForeground(Color.WHITE);
		btnEliminarContenido.setForeground(Color.WHITE);
		btnLectores.setForeground(Color.WHITE);

		btnModificarTitulo.setFocusPainted(false);
		btnAgregarLibro.setFocusPainted(false);
		btnEliminarContenido.setFocusPainted(false);
		btnLectores.setFocusPainted(false);

		btnModificarTitulo.setBorderPainted(false);
		btnAgregarLibro.setBorderPainted(false);
		btnEliminarContenido.setBorderPainted(false);
		btnLectores.setBorderPainted(false);

		btnModificarTitulo.setOpaque(true);
		btnAgregarLibro.setOpaque(true);
		btnEliminarContenido.setOpaque(true);
		btnLectores.setOpaque(true);

		btnModificarTitulo.setPreferredSize(dimensionFija);
		btnAgregarLibro.setPreferredSize(dimensionFija);
		btnEliminarContenido.setPreferredSize(dimensionFija);
		btnLectores.setPreferredSize(dimensionFija);

		btnModificarTitulo.setVisible(true);
		btnAgregarLibro.setVisible(true);
		btnEliminarContenido.setVisible(true);
		btnLectores.setVisible(true);

		btnModificarTitulo.setEnabled(true);
		btnAgregarLibro.setEnabled(true);
		btnEliminarContenido.setEnabled(true);
		btnLectores.setEnabled(true);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(20, 20, 20, 20);
		panelBotones.add(btnAgregarLibro, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panelBotones.add(btnModificarTitulo, constraints);

		constraints.gridx = 1;
		panelBotones.add(btnEliminarContenido, constraints);

		constraints.gridx = 2;
		panelBotones.add(btnLectores, constraints);

		panelBotones.setOpaque(false);

		// Agregamos ActionListener para cada botón
		btnModificarTitulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Metodos.modificarTitulo(biblioteca);
			}
		});

		btnAgregarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Metodos.agregarLibros(biblioteca);
			}
		});

		btnEliminarContenido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Metodos.eliminarPorCodigo(biblioteca);
			}
		});

		btnLectores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Metodos.lectoresAnuales(biblioteca);
			}
		});
	}

	// Método para volver a la interfaz principal
	private static void volverAInterfazPrincipal() {
		// Ocultar y deshabilitar componentes específicos
		desplegableLibrosLiteratura.setVisible(false);
		desplegableLibrosLiteratura.setEnabled(false);

		desplegableLibrosConsultayReferencia .setVisible(false);
		desplegableLibrosConsultayReferencia .setEnabled(false);

		desplegableLibrosInfantiles.setVisible(false);
		desplegableLibrosInfantiles.setEnabled(false);

		desplegableLibrosDivulgativos.setVisible(false);
		desplegableLibrosDivulgativos.setEnabled(false);
		
		desplegableLibrosTecnicos.setVisible(false);
		desplegableLibrosTecnicos.setEnabled(false);
		
		btnAgregarLibro.setVisible(false);
		btnAgregarLibro.setEnabled(false);

		btnModificarTitulo.setVisible(false);
		btnModificarTitulo.setEnabled(false);
		btnEliminarContenido.setVisible(false);
		btnEliminarContenido.setEnabled(false);
		btnLectores.setVisible(false);
		btnLectores.setEnabled(false);

		botonDesplegarLiteratura.setVisible(false);
		botonDesplegarLiteratura.setEnabled(false);

		botonDesplegarConsultayReferencia.setVisible(false);
		botonDesplegarConsultayReferencia.setEnabled(false);

		botonDesplegarInfantiles.setVisible(false);
		botonDesplegarInfantiles.setEnabled(false);

		botonDesplegarDivulgativos.setVisible(false);
		botonDesplegarDivulgativos.setEnabled(false);
		
		botonDesplegarTecnicos.setVisible(false);
		botonDesplegarTecnicos.setEnabled(false);

		btnVolver.setVisible(false);
		btnVolver.setEnabled(false);

		panelBotones.setVisible(false);
		panelBotones.setEnabled(false);

		// Declaramos todos los botones anteriores visibles y disponibles
		botonLibros.setVisible(true);
		botonLibros.setEnabled(true);

		botonMostrarXML.setVisible(true);
		botonMostrarXML.setEnabled(true);

		botonOtrosDatos.setVisible(true);
		botonOtrosDatos.setEnabled(true);

		frame.validate(); // Actualiza la interfaz
	}
}
