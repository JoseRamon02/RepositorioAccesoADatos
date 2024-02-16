package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.JuegoModelo;
import Vista.JuegoPelea;

public class JuegoControlador {
    private JuegoModelo modelo;
    private JuegoPelea vista;

    public JuegoControlador(JuegoModelo modelo, JuegoPelea vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Configurar listener para el botón de ataque
        vista.getBotonAtacar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para manejar el ataque en el controlador
                Personaje1 personaje = modelo.getPersonaje();
                personaje.atacar();  // Suponiendo que el personaje tiene un método atacar()
                vista.actualizarPantalla();  // Actualizar la vista después del ataque
            }
        });
    }

    // Otros métodos según sea necesario
}
