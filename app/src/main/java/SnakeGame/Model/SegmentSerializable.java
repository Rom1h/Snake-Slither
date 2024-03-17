package SnakeGame.Model;

/**
 * Classe SegmentSerializable représentant une version sérialisable d'un segment.
 */
public class SegmentSerializable {
    private double x,y;
    
    /**
     * Constructeur pour SegmentSerializable.
     * Initialise un nouveau segment avec des coordonnées spécifiées.
     *
     * @param x La position en X du segment.
     * @param y La position en Y du segment.
     */
    public SegmentSerializable(double x,double y){
        this.x=x;
        this.y=y;
    }
    public double getX(){
        return x;
    }
     public double getY(){
        return y;
    }
    
}
