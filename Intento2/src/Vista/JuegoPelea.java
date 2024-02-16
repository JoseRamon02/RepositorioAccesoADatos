package Vista;
import javax.swing.*;

import Controlador.Personaje1;
import Modelo.JuegoModelo;

import java.awt.*;

public class JuegoPelea extends JFrame {
    private JButton botonAtacar;
    private JLabel etiquetaResultado;
    private JuegoModelo modelo;

    public void JuegoVistaPela(JuegoModelo modelo) {
        this.modelo = modelo;

        // Configuración básica del JFrame
        setTitle("Juego de Pelea");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar componentes de la interfaz
        botonAtacar = new JButton("Atacar");
        etiquetaResultado = new JLabel("Esperando acción...");

        // Agregar componentes al contenedor
        add(botonAtacar, BorderLayout.CENTER);
        add(etiquetaResultado, BorderLayout.SOUTH);

        // Configurar listener para el botón de ataque
        botonAtacar.addActionListener(e -> {
            // Lógica para manejar el ataque
            etiquetaResultado.setText("¡Ataque realizado!");
        });

        // Hacer visible la ventana
        setVisible(true);
    }

    // Métodos para actualizar la vista según la lógica del juego
    public void actualizarPantalla() {
        // Lógica para actualizar la interfaz gráfica según el estado del juego
        // En este ejemplo, supongamos que el personaje tiene una posición
        Personaje1 personaje = modelo.getPersonaje();
        etiquetaResultado.setText("Posición del personaje: (" + personaje.getX() + ", " + personaje.getY() + ")");
    }
}
