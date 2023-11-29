package Controlador;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

import Vista.GamePanel;

/**
 * Clase que representa un PowerUp en el juego.
 */
public class PowerUp {

    private double x;
    private double y;
    private int r;
    
    private int type;
    private int speed;
    
    private Image powerUpImage;
    
    private Enemy e;
    
    /**
     * Constructor para un nuevo PowerUp asociado a un enemigo y un tipo específico.
     * 
     * @param e    El enemigo asociado al PowerUp.
     * @param type El tipo de PowerUp:
     *             1 - +1 Vida
     *             2 - +1 Poder
     *             3 - +2 Poder
     *             5 - Slow Motion
     */
    public PowerUp(Enemy e, int type) {
        this.x = e.getx();
        this.y = e.gety();
        this.type = type;
        this.e = e;
        this.speed = 2;
        
        switch(type) {
            case 1: {
                powerUpImage = loadImage("/res/senzu.png", 20, 20); // Puedes ajustar el tamaño según tus necesidades
                r = 10; // El tamaño original de la imagen antes de escalarla
            }
            break;
            case 2: {
                powerUpImage = loadImage("/res/capsula.png", 25, 25);
                r = 10;
            }
            break;
            case 3: {
                powerUpImage = loadImage("/res/kiPowerUp.png", 25, 25);
                r = 10;
            }
            break;
            case 5: {
                powerUpImage = loadImage("/res/relojPowerUp.png", 20, 20); // Puedes ajustar el tamaño según tus necesidades
                r = 5; // El tamaño original de la imagen antes de escalarla
                speed = 1;
            }
            break;
            default: 
                System.exit(0);
        }
    }
    
    // Getter Methods
    /**
     * Obtiene la coordenada x del PowerUp.
     * 
     * @return La coordenada x del PowerUp.
     */
    public double getx() { return x; }

    /**
     * Obtiene la coordenada y del PowerUp.
     * 
     * @return La coordenada y del PowerUp.
     */
    public double gety() { return y; }

    /**
     * Obtiene el radio del PowerUp.
     * 
     * @return El radio del PowerUp.
     */
    public int getr() { return r; }

    /**
     * Obtiene el tipo del PowerUp.
     * 
     * @return El tipo del PowerUp.
     */
    public int getType() { return type; }
    
    /**
     * Actualiza la posición del PowerUp en función de la velocidad y el estado de Slow Motion.
     * 
     * @return true si el PowerUp ha llegado al final de la pantalla, false de lo contrario.
     */
    public boolean update() {
        if(e.isSlow())
            y += speed * 0.3;
        else
            y += speed;
        return y > GamePanel.HEIGHT - r;
    }

    /**
     * Dibuja el PowerUp en el panel de juego.
     * 
     * @param g El contexto gráfico en el que se dibuja el PowerUp.
     */
    public void draw(java.awt.Graphics2D g) {
        g.drawImage(powerUpImage, (int) x + e.getr(), (int) y + e.getr(), null);
    }

    /**
     * Carga una imagen desde un archivo y la escala al tamaño especificado.
     * 
     * @param path   La ruta del archivo de imagen.
     * @param width  El ancho deseado de la imagen escalada.
     * @param height La altura deseada de la imagen escalada.
     * @return La imagen escalada.
     */
    private Image loadImage(String path, int width, int height) {
        try {
            Image originalImage = ImageIO.read(getClass().getResource(path));
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return scaledImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // O puedes lanzar una excepción aquí si lo prefieres
        }
    }
}
