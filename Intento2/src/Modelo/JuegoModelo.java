package Modelo;
import Controlador.Personaje1;
public class JuegoModelo {
    private Personaje1 personaje;

    public JuegoModelo() {
        // Inicializar el modelo con un personaje
        personaje = new Personaje1();
    }

    public Personaje1 getPersonaje() {
        return personaje;
    }

    // Otros métodos y lógica del modelo según sea necesario
}
