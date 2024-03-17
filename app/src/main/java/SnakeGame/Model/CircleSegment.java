package SnakeGame.Model;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

/**
 * Classe CircleSegment, étend la classe Circle et implémente l'interface Segment.
 * Représente un segment de serpent sous forme de cercle.
 * Est utilisé pour le mode slither.io.
 */
public class CircleSegment  extends Circle implements Segment{
    private static double size = 10;
     /**
     * Constructeur pour CircleSegment.
     * Crée un segment de serpent sous forme de cercle aux coordonnées spécifiées.
     *
     * @param coorX La position initiale en X du segment.
     * @param coorY La position initiale en Y du segment.
     */
    public CircleSegment(double coorX, double coorY) {
        super(size); // Création d'un cercle avec le rayon donné (ici, size)
        setCenterX(coorX);
        setCenterY(coorY);
    }
    @Override
    public double getSegX() {
       return getCenterX();
    }

    @Override
    public double getSegY() {
       return getCenterY();
    }
    /**
     * Déplace le segment à une nouvelle position.
     *
     * @param x Le déplacement en X à appliquer au segment.
     * @param y Le déplacement en Y à appliquer au segment.
     */
    @Override
    public void move(double x, double y) {
        setCenterX(getSegX()+x);
        setCenterY(getSegY()+y);

    }
    public void setSegPos(double x,double y){
        setCenterX(x);
        setCenterY(y);

    }
     /**
     * Met à jour la liste des segments.
     * Utilise une stratégie d'interpolation lineaire pour déterminer les nouvelles positions 
     * de chaque segment.
     *
     * @param list La liste des segments à mettre à jour.
     */
    @Override
    public void updateSegmentsList(List<Segment> list) {
        for (int i = list.size()-1; i >= 1; i--) {
            Segment prevSegment = list.get(i - 1);
            Segment currentSegment = list.get(i);
            double newPosX=newPos(currentSegment.getSegX(), prevSegment.getSegX());
            double newPosY=newPos(currentSegment.getSegY(), prevSegment.getSegY());
            currentSegment.setSegPos(newPosX,newPosY);
        }
    }
    /**
     * Calcule une nouvelle position en utilisant une interpolation linéaire.
     *
     * @param start Position de départ.
     * @param end Position d'arrivée.
     * @return La nouvelle position calculée.
     */
    private double newPos(double start, double end) {
        return start + (end - start)/3;
    }
    @Override
    public Node getSegment() {
      return this;
    } 
    
}
