package SnakeGame.Model;

import java.util.List;

import javafx.scene.Node;


/**
 * Interface Segment représentant un segment du serpent.
 * Fournit les méthodes nécessaires pour gérer les propriétés et le comportement d'un segment.
 */
public interface Segment{
    /**
     * Retourne le composant graphique associé à ce segment.
     *
     * @return Le composant graphique (Node) du segment.
     */
    public Node getSegment();
    public double getSegX();
    public double getSegY();
    /**
     * Déplace le segment à une nouvelle position.
     *
     * @param x La nouvelle position en X.
     * @param y La nouvelle position en Y.
     */
    public void move(double x,double y);
     /**
     * Met à jour la liste des segments pour refléter les changements dans la structure du serpent.
     *
     * @param list Liste à mettre à jour.
     */
    public void updateSegmentsList(List<Segment> list);

    /**
     * Définit la position du segment.
     *
     * @param x La position en X à définir.
     * @param y La position en Y à définir.
     */
    public void setSegPos(double x,double y);
}
