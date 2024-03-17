package SnakeGame.Model;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
/**
 * Classe RectangleSegment, une implémentation concrète de l'interface Segment.
 * Cette classe étend Rectangle de JavaFX et représente un segment de serpent sous forme de rectangle.
 */
public class RectangleSegment extends Rectangle implements Segment {
    private static double size = 20;
     /**
     * Constructeur pour RectangleSegment.
     * Crée un segment de serpent sous forme de rectangle avec des coordonnées spécifiées.
     *
     * @param coorX La position en X du segment.
     * @param coorY La position en Y du segment.
     */
    public RectangleSegment(double coorX,double coorY){
        super(size,size); 
        setTranslateX(coorX);
        setTranslateY(coorY);
    }
    /**
     * Déplace le segment à une nouvelle position relative.
     *
     * @param x Le déplacement en X à appliquer.
     * @param y Le déplacement en Y à appliquer.
     */
    @Override
    public void move(double x, double y) {
        setTranslateX(getSegX()+x);
        setTranslateY(getSegY()+y);
    }
    @Override
    public double getSegX() {
       return getTranslateX();
    }
    @Override
    public double getSegY() {
        return getTranslateY();
    }
    /**
     * Met à jour la liste des segments pour refléter le déplacement du serpent.
     * Chaque segment prend la position du segment précédent dans la liste.
     *
     * @param list La liste des segments du serpent.
     */
    @Override
    public void updateSegmentsList(List<Segment> list) {
        for (int i = list.size()-1 ; i >= 1 ; i--){
            Segment prev =list.get(i-1);
            list.get(i).setSegPos(prev.getSegX(), prev.getSegY());
            
        }
    }
    
    @Override
    public void setSegPos(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }
    @Override
    public Node getSegment() {
       return this;
    }
}
