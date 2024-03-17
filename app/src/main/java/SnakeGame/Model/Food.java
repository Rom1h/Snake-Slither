package SnakeGame.Model;

import java.util.Random;
import java.util.UUID;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
public class Food extends Circle {
    private static final double FOOD_SIZE=10;
    private static Color color;
    private UUID id;
    public Food(UUID id){
        super(FOOD_SIZE, Color.RED);
        this.id=id;
    

    }
    public Food(double x,double y){
        super(FOOD_SIZE, generateRandomColor());
        Random random = new Random();
        double foodX = Math.floor(random.nextDouble(x/ 20)) * 20; // Génère un multiple de 20 pour la position X
        double foodY = Math.floor(random.nextDouble(y / 20)) * 20; // Génère un multiple de 20 pour la position Y
        setLayoutX(foodX);
        setLayoutY(foodY);
        this.id=UUID.randomUUID();
    }
    public UUID getUuid(){
        return id;
    }
    private static Color generateRandomColor() {
        // Génère une couleur aléatoire (bleue avec une chance sur trois, rouge avec deux chances sur trois)
        Random random = new Random();
        int isBlue = random.nextInt(3); // Valeur entre 0 et 2

        if (isBlue == 0) {
            color = Color.BLUE;
            return color;
        } else {
            color = Color.RED;
            return color;
        }
    }
    public boolean isBlue(){
        return color == Color.BLUE;
    }
}
