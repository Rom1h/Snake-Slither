package SnakeGame.View.GameViewFolder.MultiView;

import java.util.List;

import SnakeGame.Model.FoodSerializable;
import SnakeGame.Model.SerpentSerializable;
/**
 * Classe GameStateServer conçue pour mettre à jour et gérer l'état du jeu 
 * entre le serveur et le client.
 * Cette classe stocke les informations concernant les serpents et les bonus dans le jeu.
 */
public class GameStateServer {
    private List<FoodSerializable> foods;
    List<SerpentSerializable> serpents;
     /**
     * Constructeur de GameStateServer.
     * Initialise l'état des serpents et des aliments dans le jeu.
     *
     * @param serpents Liste des serpents (SerpentSerializable) dans le jeu.
     * @param foods    Liste des aliments (FoodSerializable) présents dans le jeu.
     */
     public GameStateServer(List<SerpentSerializable> serpents,List<FoodSerializable> foods) {
        this.serpents = serpents;
        this.foods = foods;
    }
    /**
     * Retourne la liste des serpents dans le jeu.
     *
     * @return La liste des serpents (SerpentSerializable).
     */
    public List<SerpentSerializable> getSerpents(){
        return serpents;
    }
    /**
     * Retourne la liste des bonus présents dans le jeu.
     *
     * @return La liste des aliments (FoodSerializable).
     */
    public List<FoodSerializable> getFoods(){
        return foods;
    }
}
