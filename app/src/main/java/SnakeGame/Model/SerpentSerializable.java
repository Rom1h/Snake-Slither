package SnakeGame.Model;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
/**
 * Class pour représenter une version sérialisable d'un serpent dans le jeu Snake.
 * Utilisée pour faciliter la transmission de l'état du serpent sur le réseau en mode multijoueur.
 */
public class SerpentSerializable {
    private LinkedList<SegmentSerializable> segmentsList;
    private boolean is_dead;
    private UUID id;
    private double speed;
    private double deltaX,deltaY;//Pour deplacement du serpent;
    /**
     * Constructeur pour créer une instance sérialisable d'un serpent.
     *
     * @param serpent L'instance de Serpent à sérialiser.
     */

    public SerpentSerializable(Snake serpent){
        this.segmentsList=new LinkedList<>();
        this.segmentsList=convertList(serpent.getSnake());
        this.is_dead=serpent.isDead();
        this.speed=serpent.getVitesse();
        this.id=serpent.getId();
        this.deltaX=0;
        this.deltaY=0;
    }
    /**
     * Constructeur pour créer une instance sérialisable d'un serpent avec des informations sur son déplacement.
     *
     * @param serpent L'instance de Serpent à sérialiser.
     * @param deltaX Déplacement en X du serpent.
     * @param deltaY Déplacement en Y du serpent.
     */
     public SerpentSerializable(Snake serpent,double deltaX,double deltaY){
        this.segmentsList=new LinkedList<>();
        this.segmentsList=convertList(serpent.getSnake());
        this.is_dead=serpent.isDead();
        this.speed=serpent.getVitesse();
        this.id=serpent.getId();
        this.deltaX=deltaX;
        this.deltaY=deltaY;
    }
    
    /**
     * Convertit une liste de segments en une liste de segments sérialisables.
     *
     * @param segmentsList Liste des segments de serpent.
     * @return Une liste de SegmentSerializable correspondant aux segments du serpent.
     */
    public LinkedList<SegmentSerializable> convertList( List<Segment> segmentsList){
        LinkedList<SegmentSerializable> segmentListSerializable=new LinkedList<>();
        for(int i=0;i<segmentsList.size();i++){
            Segment seg=segmentsList.get(i);
            SegmentSerializable segSerializable=new SegmentSerializable(seg.getSegX(), seg.getSegY());
            segmentListSerializable.add(segSerializable);
        }
        return segmentListSerializable;
    }
    /**
    * Convertit une liste de serpents en une liste de serpents sérialisables.
    *
    * @param serpents Liste de serpents à convertir.
    * @return Une liste de SerpentSerializable.
    */
    public static LinkedList<SerpentSerializable> convertListOfSnake(List<Snake> serpents){
        LinkedList<SerpentSerializable> listSnakeSerializables=new LinkedList<>();
        for(int i=0;i<serpents.size();i++){
            listSnakeSerializables.add(new SerpentSerializable(serpents.get(i)));
        }
        return listSnakeSerializables;
    }
    /**
    * Retourne la vitesse du serpent.
    *
    * @return La vitesse du serpent.
    */
    public double getSnakeSpeed(){
        return speed;
    }
    /**
    * Retourne si le serpent et mort.
    *
    * @return L'etat du serpent.
    */
    public boolean isDead(){
        return is_dead;
    }
    /**
     * Retourne l'identifiant unique du serpent.
     *
     * @return L'UUID du serpent.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Retourne la taille du serpent (nombre de segments).
     *
     * @return La taille du serpent.
     */
    public int getSize() {
        return segmentsList.size();
    }

    public double getDeltaX(){
        return deltaX;
    }
    public double getDeltaY(){
        return deltaY;
    }
    public void setDeltaX(double deltaX){
        this.deltaX=deltaX;
    }
    public void setDeltaY(double deltaY){
        this.deltaY=deltaY;
    }

    /**
     * Met à jour la liste des segments du serpent.
     *
     * @param serpent Le SerpentSerializable dont la liste des segments est à utiliser.
     */
    public void setSegmentList(SerpentSerializable serpent) {
        this.segmentsList = serpent.getSegmentsList();
    }

    /**
     * Retourne la liste des segments du serpent.
     *
     * @return La liste des SegmentSerializable.
     */
    public LinkedList<SegmentSerializable> getSegmentsList() {
        return segmentsList;
    }
}