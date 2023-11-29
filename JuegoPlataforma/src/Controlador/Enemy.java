package Controlador;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

import Vista.GamePanel;

/**
 * The Enemy class represents an enemy object in the game.
 * It manages the enemy's position, movement, appearance, and health.
 */
public class Enemy {

    private double x;
    private double y;
    private double dx;
    private double dy;
    private int r;
    private int originalSize; // Original size of the enemy (unscaled)
    private double scale; // Specific scale factor for the enemy
    private int speed;
    private boolean setHealth;
    private int health;
    private boolean dead;
    private double angle;
    private int maxAngle;
    private int minAngle;
    private int type;
    private int rank;
    private boolean setXY;
    private Image enemyImage;
    private boolean setFlash;
    private long startFlashTimer;
    private int flashTimer;
    private boolean slow;

    /**
     * Constructs a new Enemy object with the specified type and rank.
     *
     * @param type The type of the enemy.
     * @param rank The rank of the enemy.
     */
    public Enemy(int type, int rank) {
        this.type = type;
        this.rank = rank;
        maxAngle = 130;
        minAngle = 45;
        angle = Math.toRadians(Math.random() * maxAngle + minAngle);
        setHealth = true;
        setXY = true;
        scale = 1; // You can adjust this according to your preference
        speed = 3; // Set a common speed for all enemies
        init();
        initImage(type, rank);
    }

    /**
     * Constructs a new Enemy object with the specified type, rank, health, angle, x-coordinate, and y-coordinate.
     *
     * @param type   The type of the enemy.
     * @param rank   The rank of the enemy.
     * @param health The health of the enemy.
     * @param angle  The angle at which the enemy is oriented.
     * @param x      The x-coordinate of the enemy.
     * @param y      The y-coordinate of the enemy.
     */
    public Enemy(int type, int rank, int health, double angle, double x, double y) {
        this.type = type;
        this.rank = rank;
        this.angle = angle;
        this.health = health;
        if (rank == 1 && type == 1)
            health = 1;
        setHealth = false;
        this.x = x;
        this.y = y;
        setXY = false;
        scale = 1; // You can adjust this according to your preference
        speed = 3; // Set a common speed for all enemies
        init();
        initImage(type, rank);
    }

    private void initImage(int type, int rank) {
        try {
            String imagePath = "/res/enemy_type" + type + "_rank" + rank + ".png";
            enemyImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
            originalSize = enemyImage.getWidth(null); // Get the original size
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        dead = false;
        flashTimer = 80;
        if (type == 1) {
            if (rank == 1) {
                r = 1;
                if (setHealth)
                    health = 1;
                scale = 0.4;
                speed = 4;
            }
            if (rank == 2) {
                r = 1;
                if (setHealth)
                    health = 2;
                scale = 0.1;
                speed = 3;
            }
        } else if (type == 2) {
            if (rank == 1) {
                r = 1;
                if (setHealth)
                    health = 3;
                scale = 0.3;
                speed = 3;
            } else if (rank == 2) {
                r = 1;
                if (setHealth)
                    health = 4;
                scale = 0.1;
                speed = 3;
            }
        } else if (type == 3) {
            if (rank == 1) {
                r = 1;
                if (setHealth)
                    health = 5;
                scale = 0.15;
                speed = 2;
            } else if (rank == 2) {
                r = 1;
                if (setHealth)
                    health = 6;
                scale = 0.1;
                speed = 2;
            }
        }
        // Modify the size of the enemy according to the scale
        r = (int) (r * scale);
        if (setXY) {
            x = (Math.random() * GamePanel.WIDTH - (4 * r)) + 2 * r;
            y = -r;
        }
        dx = Math.cos(angle) * speed;
        dy = Math.sin(angle) * speed;
    }

    /**
     * Gets the current x-coordinate of the enemy.
     *
     * @return The x-coordinate of the enemy.
     */
    public double getx() {
        return x;
    }

    /**
     * Gets the current y-coordinate of the enemy.
     *
     * @return The y-coordinate of the enemy.
     */
    public double gety() {
        return y;
    }

    /**
     * Gets the radius of the enemy.
     *
     * @return The radius of the enemy.
     */
    public int getr() {
        return r;
    }

    /**
     * Gets the current health of the enemy.
     *
     * @return The health of the enemy.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the firing angle of the enemy.
     *
     * @return The firing angle of the enemy in radians.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Gets the type of the enemy.
     *
     * @return The type of the enemy.
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the rank of the enemy.
     *
     * @return The rank of the enemy.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Checks if the enemy is dead.
     *
     * @return true if the enemy is dead, false otherwise.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Sets the slow motion status for the enemy.
     *
     * @param b true if the enemy is in slow motion, false otherwise.
     */
    public void setSlow(boolean b) {
        slow = b;
    }

    /**
     * Checks if the enemy is in slow motion.
     *
     * @return true if the enemy is in slow motion, false otherwise.
     */
    public boolean isSlow() {
        return slow;
    }

    /**
     * Handles a hit to the enemy, decrementing its health.
     * If health reaches zero, the enemy is marked as dead.
     */
    public void hit() {
        health--;
        if (health > 0) {
            setFlash = true;
            startFlashTimer = System.nanoTime();
        }
        if (type == 1 && rank == 1 && health <= 0)
            dead = true;
        dead = (health <= 0);
    }

    /**
     * Updates the position and state of the enemy.
     */
    public void update() {
        if (slow) {
            x += dx * 0.3;
            y += dy * 0.3;
            flashTimer = 500;
        } else {
            x += dx;
            y += dy;
            flashTimer = 80;
        }
        if (type == 1 && rank == 1)
            health = 1;
        if (x < r && dx < 0)
            dx = -dx;
        if (y < r && dy < 0)
            dy = -dy;
        if (x > GamePanel.WIDTH - r && dx > 0)
            dx = -dx;
        if (y > GamePanel.HEIGHT - r && dy > 0)
            dy = -dy;
        if (health > 1) {
            long elapsed = (System.nanoTime() - startFlashTimer) / 1000000;
            if (elapsed > flashTimer) {
                setFlash = false;
                startFlashTimer = 0;
            }
        }
    }

    /**
     * Draws the enemy on the specified graphics context.
     *
     * @param g The graphics context on which to draw the enemy.
     */
    public void draw(Graphics2D g) {
        // Draw the scaled enemy
        int scaledSize = (int) (originalSize * scale);
        g.drawImage(enemyImage, (int) x - scaledSize / 2, (int) y - scaledSize / 2, scaledSize, scaledSize, null);
    }
}
