/**
 * La clase `RankingXML` maneja la lectura y escritura de puntuaciones en un archivo XML.
 * Permite actualizar y guardar la puntuación de un jugador, así como obtener el ranking desde
 * el archivo XML y representarlo en formato HTML.
 */
package Controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Vista.GamePanel;

public class RankingXML {
    private String nombre;
    private List<Jugador> jugadores;
    GamePanel game;

    /**
     * Constructor de la clase `RankingXML`. Inicializa la lista de jugadores.
     */
    public RankingXML() {
        jugadores = new ArrayList<>();
    }

    /**
     * Establece el nombre del jugador actual.
     *
     * @param nombre El nombre del jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Actualiza la información del jugador antes de guardar la puntuación.
     */
    public void actualizarPuntuacion() {
        Jugador jugador = new Jugador(nombre, game.player.getScore());
        jugadores.add(jugador);
    }

    /**
     * Guarda la puntuación de los jugadores en un archivo XML.
     */
    public void guardarPuntuacion() {
        // Llama a actualizarPuntuacion antes de intentar guardar
        actualizarPuntuacion();

        // Utiliza una ruta absoluta para el BufferedWriter
        String filePath = "src/xml/puntuaciones.xml"; // Cambia esto a la ruta absoluta correcta
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("<puntuaciones>\n");
            for (Jugador jugador : jugadores) {
                writer.write(String.format("  <jugador nombre=\"%s\" score=\"%d\" />\n", jugador.getNombre(),
                        jugador.getScore()));
            }

            writer.write("</puntuaciones>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el ranking desde el archivo XML y lo representa en formato HTML.
     *
     * @return Una cadena HTML que representa el ranking.
     */
    public String obtenerRankingDesdeXML() {
        StringBuilder rankingInfo = new StringBuilder();
        rankingInfo.append("<html><body><h1>Ranking</h1>");

        try {
            // Cargar el archivo XML
            File inputFile = new File("src/xml/puntuaciones.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Obtener la lista de nodos 'jugador'
            NodeList jugadorNodes = doc.getElementsByTagName("jugador");

            // Iterar sobre los nodos 'jugador' y obtener la información
            for (int i = 0; i < jugadorNodes.getLength(); i++) {
                Element jugadorElement = (Element) jugadorNodes.item(i);
                String nombre = jugadorElement.getAttribute("nombre");
                int score = Integer.parseInt(jugadorElement.getAttribute("score"));
                rankingInfo.append(String.format("<p>%s: %d puntos</p>", nombre, score));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        rankingInfo.append("</body></html>");
        return rankingInfo.toString();
    }

    // Clase interna para representar a un jugador
    private static class Jugador {
        private String nombre;
        private int score;

        public Jugador(String nombre, int score) {
            this.nombre = nombre;
            this.score = score;
        }

        public String getNombre() {
            return nombre;
        }

        public int getScore() {
            return score;
        }
    }
}
