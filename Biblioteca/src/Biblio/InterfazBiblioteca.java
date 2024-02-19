package Biblio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class InterfazBiblioteca {

	// ATRIBUTOS
	static Biblioteca biblioteca = new Biblioteca();
     
	private static JFrame frame;
	private static JPanel panelConFondo;
	private static JPanel panelBotonesLibros = new JPanel(new GridBagLayout());
	private static JPanel panelBotones = new JPanel(new GridBagLayout());
	private static GridBagConstraints constraints;
	private static BufferedImage imagenFondo = null;
	private static Dimension dimensionFija = new Dimension(200, 80);;
	private static JTextArea textArea;
	private static JScrollPane scrollPane;
	private static JButton botonAnimado = new JButton("Bienvenido a la Biblioteca");
	private static JButton botonLibros = new JButton("Libros");
	private static JButton botonMostrarXML = new JButton("Mostrar xml");
	private static JButton botonOtrosDatos = new JButton("Otros Datos");
	private static JButton botonDesplegarLiteratura = new JButton();
	private static JButton botonDesplegarConsultayReferencia = new JButton();
	private static JButton botonDesplegarInfantiles = new JButton();
	private static JButton botonDesplegarDivulgativos = new JButton();
	private static JButton botonDesplegarTecnicos = new JButton();

	private static JButton btnModificarTitulo = new JButton("<html>Modificar<br>Título</html>");
	private static JButton btnAgregarLibro = new JButton("<html>Agregar<br>Libro</html>");
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
		private static String[] opcionesLibrosTecnicos = { "Clean Code: A Handbook of Agile Software Craftsmanship", "Introduction to Algorithms", "Design Patterns: Elements of Reusable Object-Oriented",
				"The Pragmatic Programmer: Your Journey to Mastery", "Cracking the Coding Interview" };

		

	// JComboBox para los libros
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
		frame.setSize(1680, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		File archivo = new File("ArchivoXML/Biblioteca.xml");

		if (archivo.exists()) {
			biblioteca = Metodos.reutilizarXML();
			actualizarDesplegablesDesdeXML();
		} else {
			
			biblioteca = Metodos.crearXML();
		}

		try {
			imagenFondo = ImageIO.read(new File("IMG/portadaMM.jpg"));
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

		botonAnimado.setBackground(new Color(255, 255, 153));
		botonAnimado.setForeground(Color.BLACK);
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
				
			}
		});

		
		panelConFondo.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(150, 0, 0, 0);
		botonAnimado.setPreferredSize(new Dimension(200, 50)); 
	     
	     MovimienoBotonAnimado();
	     
		panelConFondo.add(botonAnimado, constraints);

		btnVolver.setBackground(new Color(255, 255, 153));
		btnVolver.setForeground(Color.BLACK);
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

	public static void MovimienoBotonAnimado() {    // Crear e iniciar un nuevo hilo para mover el botón
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int x = 0;
                int y = 0;
                int deltaX = 5;
                int deltaY = 2;

                while (true) {
                    // Calcular las nuevas coordenadas
                    int newX = x + deltaX;
                    int newY = y + deltaY;

                    // Cambiar la dirección si alcanza los límites del panel
                    if (newX <= 0 || newX >= panelConFondo.getWidth() - botonAnimado.getWidth()) {
                        deltaX *= -1;
                    }
                    if (newY <= 0 || newY >= panelConFondo.getHeight() - botonAnimado.getHeight()) {
                        deltaY *= -1;
                    }

                    // Actualizar las coordenadas del botón en el hilo de la interfaz gráfica
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                        	botonAnimado.setLocation(newX, newY);
                        }
                    });

                    // Actualizar las coordenadas actuales
                    x = newX;
                    y = newY;

                    // Esperar un breve período antes de la siguiente actualización
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start(); // Iniciar el hilo}
	}
	
	public static void abrirXML() {
		GridBagConstraints constraints = new GridBagConstraints();

		botonLibros = new JButton("Libros");

		botonMostrarXML = new JButton("Mostrar xml");
		botonOtrosDatos = new JButton("Otros Datos");

		Color colorFijo = new Color(255, 255, 153);
		botonLibros.setBackground(colorFijo);
		botonMostrarXML.setBackground(colorFijo);
		botonOtrosDatos.setBackground(colorFijo);

		botonLibros.setForeground(Color.BLACK);
		botonMostrarXML.setForeground(Color.BLACK);
		botonOtrosDatos.setForeground(Color.BLACK);

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
		constraints.gridy = 1;
		constraints.insets = new Insets(20, 20, 20, 20);
		panelBotonesLibros.add(botonLibros, constraints);

		constraints.gridx = 2;
		constraints.gridy = 1;
		panelBotonesLibros.add(botonMostrarXML, constraints);

		constraints.gridx = 1;
		panelBotonesLibros.add(botonOtrosDatos, constraints);

		panelBotonesLibros.setOpaque(false);

		botonLibros.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Agregar desplegables de libros
		    
		        agregarDesplegablesLibros();
		        botonLibros.setVisible(false);
		        botonMostrarXML.setVisible(false);
		        botonOtrosDatos.setVisible(false);
		        btnVolver.setVisible(true);
		        btnVolver.setEnabled(true);
		        

		        // Array con los nombres de las categorías
		        String[] categorias = {"Literatura", "Consulta y Referencia", "Infantiles", "Divulgativos", "Técnicos"};

		     // Primero, declara una lista para almacenar las etiquetas de categoría
		        ArrayList<JLabel> listaEtiquetas = new ArrayList<>();

		        // Agregar etiquetas de categoría con fondo azul y texto blanco y más grande
		        for (int i = 0; i < categorias.length; i++) {
		            JLabel label = new JLabel(categorias[i], SwingConstants.CENTER);
		            label.setOpaque(true); // Hacer el fondo visible
		            label.setBackground(Color.YELLOW); // Establecer el fondo azul
		            label.setForeground(Color.BLACK); // Establecer el color del texto a blanco
		            label.setFont(label.getFont().deriveFont(Font.BOLD, 16f)); // Aumentar el tamaño de la fuente
		            GridBagConstraints gbc = new GridBagConstraints();
		            gbc.gridx = i;
		            gbc.gridy = 0;
		            gbc.weightx = 1.0;
		            gbc.fill = GridBagConstraints.HORIZONTAL;
		            panelBotonesLibros.add(label, gbc);
		            
		            // Agregar la etiqueta actual a la lista
		            listaEtiquetas.add(label);
		        }

		        // Luego, configura un ActionListener para el botón "volver"
		        btnVolver.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                // Itera sobre la lista de etiquetas y elimínalas del panel
		                for (JLabel etiqueta : listaEtiquetas) {
		                    panelBotonesLibros.remove(etiqueta);
		                }
		                // Asegúrate de que el panel se vuelva a pintar correctamente
		                panelBotonesLibros.revalidate();
		                panelBotonesLibros.repaint();
		            }
		        });

		        // Volvemos a activar los desplegables de los libros
		        desplegableLibrosLiteratura.setVisible(true);
		        desplegableLibrosConsultayReferencia.setVisible(true);
		        desplegableLibrosInfantiles.setVisible(true);
		        desplegableLibrosDivulgativos.setVisible(true);
		        desplegableLibrosTecnicos.setVisible(true);
		    
		        desplegableLibrosLiteratura.setEnabled(true);
		        desplegableLibrosConsultayReferencia.setEnabled(true);
		        desplegableLibrosInfantiles.setEnabled(true);
		        desplegableLibrosDivulgativos.setEnabled(true);
		        desplegableLibrosTecnicos.setEnabled(true);
		        
		    }
		});





		botonMostrarXML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String mostrar = Metodos.leerXML("ArchivoXML/Biblioteca.xml");
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

	static void actualizarDesplegablesDesdeXML() {
	    // Actualizar opciones de Literatura
	    opcionesLibrosLiteratura = obtenerTitulosDesdeXML("literatura");
	    desplegableLibrosLiteratura.setModel(new DefaultComboBoxModel<>(opcionesLibrosLiteratura));

	    // Actualizar opciones de Consulta y Referencia
	    opcionesLibrosConsultayReferencia = obtenerTitulosDesdeXML("consultayreferencia");
	    desplegableLibrosConsultayReferencia.setModel(new DefaultComboBoxModel<>(opcionesLibrosConsultayReferencia));

	    // Actualizar opciones de Infantiles
	    opcionesLibrosInfantiles = obtenerTitulosDesdeXML("infantiles");
	    desplegableLibrosInfantiles.setModel(new DefaultComboBoxModel<>(opcionesLibrosInfantiles));

	    // Actualizar opciones de Divulgativos
	    opcionesLibrosDivulgativos = obtenerTitulosDesdeXML("divulgativos");
	    desplegableLibrosDivulgativos.setModel(new DefaultComboBoxModel<>(opcionesLibrosDivulgativos));

	    // Actualizar opciones de Tecnicos
	    opcionesLibrosTecnicos = obtenerTitulosDesdeXML("tecnicos");
	    desplegableLibrosTecnicos.setModel(new DefaultComboBoxModel<>(opcionesLibrosTecnicos));
	}
	
	public static String[] obtenerTitulosDesdeXML(String categoria) {
	    List<String> titulos = new ArrayList<>();
	    switch (categoria) {
	        case "literatura":
	            for (Libro libro : CategoriaLibros.librosLiteratura) {
	                titulos.add(libro.getTitulo());
	            }
	            break;
	        case "consultayreferencia":
	            for (Libro libro : CategoriaLibros.librosConsultayReferencia) {
	                titulos.add(libro.getTitulo());
	            }
	            break;
	        case "infantiles":
	            for (Libro libro : CategoriaLibros.librosInfantiles) {
	                titulos.add(libro.getTitulo());
	            }
	            break;
	        case "divulgativos":
	            for (Libro libro : CategoriaLibros.librosDivulgativos) {
	                titulos.add(libro.getTitulo());
	            }
	            break;
	        case "tecnicos":
	            for (Libro libro : CategoriaLibros.librosTecnicos) {
	                titulos.add(libro.getTitulo());
	            }
	            break;
	        default:
	            System.out.println("Categoría no válida.");
	    }
	    return titulos.toArray(new String[0]); // Convertir lista a arreglo de cadenas
	}

	
	public static void agregarDesplegablesLibros() {
		
		botonDesplegarLiteratura.setEnabled(true);
		botonDesplegarConsultayReferencia.setEnabled(true);
		botonDesplegarInfantiles.setEnabled(true);
		botonDesplegarDivulgativos.setEnabled(true);
		botonDesplegarTecnicos.setEnabled(true);
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

		desplegableLibrosLiteratura.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String libroSeleccionado = (String) desplegableLibrosLiteratura.getSelectedItem();

				// Obtenemos la información del libro
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

				// Obtenemos la información del libro
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

				// Obtenemos la información del libro
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

				// Obtenemos la información del libro
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

				// Obtenemos la información del libro
				String infoLibro = Metodos.obtenerInfoPorTitulo(biblioteca, libroSeleccionado);

			
				// Mostramos la información en un diálogo de mensaje (JOptionPane)
				JOptionPane.showMessageDialog(frame, infoLibro, "Información del libro: " + libroSeleccionado,
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Configuración de la alineación horizontal de los componentes
		((JLabel) desplegableLibrosLiteratura.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) desplegableLibrosConsultayReferencia.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) desplegableLibrosInfantiles.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) desplegableLibrosDivulgativos.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) desplegableLibrosTecnicos.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		// Establecer la dimensión fija para todos los componentes desplegables
		Dimension dimensionFija = new Dimension(300, 30);
		desplegableLibrosLiteratura.setPreferredSize(dimensionFija);
		desplegableLibrosConsultayReferencia.setPreferredSize(dimensionFija);
		desplegableLibrosInfantiles.setPreferredSize(dimensionFija);
		desplegableLibrosDivulgativos.setPreferredSize(dimensionFija);
		desplegableLibrosTecnicos.setPreferredSize(dimensionFija);

		// Establecer las restricciones del GridBagConstraints
		constraints = new GridBagConstraints();
		constraints.gridx = 0; // Columna inicial
		constraints.gridy = 2; // Fila donde se agregarán los componentes
		constraints.insets = new Insets(20, 20, 20, 20); // Espacio entre componentes

		// Agregar los componentes al panel con el GridBagConstraints configurado
		panelBotonesLibros.add(desplegableLibrosLiteratura, constraints);

		// Actualizar las restricciones para la siguiente columna y agregar el siguiente componente
		constraints.gridx++;
		panelBotonesLibros.add(desplegableLibrosConsultayReferencia, constraints);

		// Repetir el proceso para los otros componentes
		constraints.gridx++;
		panelBotonesLibros.add(desplegableLibrosInfantiles, constraints);

		constraints.gridx++;
		panelBotonesLibros.add(desplegableLibrosDivulgativos, constraints);

		constraints.gridx++;
		panelBotonesLibros.add(desplegableLibrosTecnicos, constraints);

		// Revalidar el panel para actualizar la presentación
		panelConFondo.revalidate();

	}


	public static void desplegarOtrosDatos() {
		frame.getContentPane().add(panelBotones, BorderLayout.CENTER);
		
		constraints = new GridBagConstraints();
		panelBotones.setVisible(true);
		panelBotones.setEnabled(true);

		Color colorFijo = new Color(255, 255, 153);
		btnModificarTitulo.setBackground(colorFijo);
		btnAgregarLibro.setBackground(colorFijo);
		btnEliminarContenido.setBackground(colorFijo);
		btnLectores.setBackground(colorFijo);

		btnModificarTitulo.setForeground(Color.BLACK);
		btnAgregarLibro.setForeground(Color.BLACK);
		btnEliminarContenido.setForeground(Color.BLACK);
		btnLectores.setForeground(Color.BLACK);

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

		GridBagConstraints constraintsVolver = new GridBagConstraints();
		
		  // Configura las restricciones para el botón "Volver"
		constraintsVolver.gridx = 0;
		constraintsVolver.gridy = GridBagConstraints.RELATIVE;
		constraintsVolver.gridwidth = 4;
		constraintsVolver.anchor = GridBagConstraints.CENTER;
		constraintsVolver.insets = new Insets(20, 20, 20, 20);

	    // Agrega el botón "Volver" al panel
	    panelBotones.add(btnVolver, constraintsVolver);
		
		constraints.gridx = 0;
		constraints.insets = new Insets(20, 20, 20, 20);
		panelBotones.add(btnAgregarLibro, constraints);

		constraints.gridx = 1;
		panelBotones.add(btnModificarTitulo, constraints);

		constraints.gridx = 2;
		panelBotones.add(btnEliminarContenido, constraints);

		constraints.gridx = 3;
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
