package SnakeGame.Model;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;

public class Serpent extends ArrayList<Serpent.Segment>{

    private List<Segment> snake = new ArrayList<>();
    private Segment head ;
    private int size = snake.size();
    private double vitesse; // va de moins en moins vite qd il grossit de plus en plus
    private boolean is_dead;

    public Serpent(double startPosX, double startPosY){
        head = new Segment(startPosX,startPosX); //coordonées temporaires
        snake.add(head);
        is_dead=false;
    }
    public class Segment extends Rectangle{ // classe segment/ classe imbriqué
        private static double size = 50;

        public Segment(double coorX,double coorY){
            super(coorX,coorY,size,size); //taille 
        } 
    }
    public void MoveHeadX(double x) { //on deplace dabord le corps ensuite la tete horizontalement
                                    // le dernier prends la place de l'avant dernier etc etc 
        for (int i = size ; i >= 1 ; i--){
            snake.get(i).setTranslateX(snake.get(i-1).getTranslateX());
            snake.get(i).setTranslateY(snake.get(i-1).getTranslateY());
        }
        head.setTranslateX(head.getTranslateX()+ x);

    }
    public void MoveHeadY(double y) { //on deplace dabord le corps ensuite la tete verticalement
                                    // le dernier prends la place de l'avant dernier etc etc 
        for (int i = size ; i >= 1 ; i--){
            snake.get(i).setTranslateX(snake.get(i-1).getTranslateX());
            snake.get(i).setTranslateY(snake.get(i-1).getTranslateY());
        }
        head.setTranslateY(head.getTranslateY()+y);
    }

    public Segment getHead(){
        return this.head;
    }
}
