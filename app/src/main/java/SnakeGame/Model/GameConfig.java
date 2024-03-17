package SnakeGame.Model;

/**
 * Classe GameConfig pour stocker et gérer la configuration du jeu Snake.
 * Cette classe contient les paramètres de configuration tels que les dimensions 
 * de la carte et la vitesse du serpent.
 */
public class GameConfig {
    private double sizeMapHeight;
    private double sizeMapWidth;
    private double snakeSpeed;
    
    /**
     * Constructeur pour GameConfig.
     * Initialise la configuration du jeu avec les dimensions de la carte et la vitesse du serpent.
     *
     * @param sizeMapWidth  La largeur de la carte de jeu.
     * @param sizeMapHeight La hauteur de la carte de jeu.
     * @param snakeSpeed    La vitesse du serpent.
     */
    public GameConfig(double sizeMapWidth, double sizeMapHeight, double snakeSpeed) {
            this.sizeMapWidth = sizeMapWidth;
            this.sizeMapHeight = sizeMapHeight;
            this.snakeSpeed = snakeSpeed;
        
    }
    public double getSizeMapHeight() {
        return sizeMapHeight;
    }
    public void setSizeMapHeight(double sizeMapHeight) {
        this.sizeMapHeight = sizeMapHeight;
    }
    
    public double getSizeMapWidth() {
        return sizeMapWidth;
    }
    public void setSizeMapWidth(double sizeMapWidth) {
        this.sizeMapWidth = sizeMapWidth;
    }

    public double getSnakeSpeed() {
        return snakeSpeed;
    }
    public void setSnakeSpeed(double snakeSpeed) {
        this.snakeSpeed = snakeSpeed;
    }
   
    
}

