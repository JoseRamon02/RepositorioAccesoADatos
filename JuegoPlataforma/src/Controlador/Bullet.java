package Controlador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Vista.GamePanel;

/**
 * The Bullet class represents a projectile in the game.
 * It handles the movement, drawing, and other properties of bullets.
 */
public class Bullet {

    // Coordinates
    private int x;
    private int y;
    // Vector Components
    private double dx;
    private double dy;
    // Speed
    private int speed;
    // Radius
    private int r;
    // Angle
    public double angle;

    /**
     * Constructs a new Bullet object with the specified angle, x-coordinate, and y-coordinate.
     *
     * @param angle The angle at which the bullet is fired, in degrees.
     * @param x     The initial x-coordinate of the bullet.
     * @param y     The initial y-coordinate of the bullet.
     */
    public Bullet(int angle, int x, int y) {

        // set coordinates
        this.x = x;
        this.y = y;

        // Set Speed
        speed = 10;

        // set radius
        r = 15;

        // Set Angle
        this.angle = Math.toRadians(angle);

        // Set Components
        dx = Math.cos(this.angle) * speed;
        dy = Math.sin(this.angle) * speed;

    }

    /**
     * Gets the current x-coordinate of the bullet.
     *
     * @return The x-coordinate of the bullet.
     */
    public int getx() {
        return x;
    }

    /**
     * Gets the current y-coordinate of the bullet.
     *
     * @return The y-coordinate of the bullet.
     */
    public int gety() {
        return y;
    }

    /**
     * Gets the radius of the bullet.
     *
     * @return The radius of the bullet.
     */
    public int getr() {
        return r;
    }

    /**
     * Gets the firing angle of the bullet.
     *
     * @return The firing angle of the bullet in radians.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Updates the position of the bullet based on its speed and direction.
     *
     * @return true if the bullet is out of the frame, false otherwise.
     */
    public boolean update() {

        x += dx;
        y += dy;
        // Return Condition of out of Frame
        if (x > GamePanel.WIDTH - r || y > GamePanel.HEIGHT - r || x < -r || y < -r)
            return true;
        // Return Default
        return false;

    }

    /**
     * Draws the bullet on the specified graphics context.
     *
     * @param g The graphics context on which to draw the bullet.
     */
    public void draw(Graphics2D g) {
        // Load the image before drawing it
        BufferedImage bulletImage = null;
        try {
            bulletImage = ImageIO.read(new File("src/res/bulletImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if the image has been loaded successfully
        if (bulletImage != null) {
            // Draw the image instead of the colored sphere
            g.drawImage(bulletImage, x, y, 2 * r, 2 * r, null);
        } else {
            // If the image fails to load, draw a colored sphere as a fallback
            g.fillOval(x, y, 2 * r, 2 * r);
            g.drawOval(x, y, 2 * r, 2 * r);
        }
    }
}
