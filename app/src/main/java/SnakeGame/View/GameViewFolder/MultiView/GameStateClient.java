package SnakeGame.View.GameViewFolder.MultiView;

import java.util.List;

import SnakeGame.Model.FoodSerializable;
import SnakeGame.Model.SerpentSerializable;
/**
 * Classe GameStateClient représentant l'état actuel du jeu du coté du client.
 * Cette classe stocke l'état du serpent et des aliments dans le jeu pour pouvoir être envoyé au serveur.
 */
public class GameStateClient {

    private List<FoodSerializable> foods;
    SerpentSerializable snake;
    /**
     * Constructeur pour GameStateClient.
     *
     * @param snake Le serpent (SerpentSerializable) du joueur dans le jeu.
     * @param foods La liste des aliments (FoodSerializable) présents dans le jeu coté client.
     */
     public GameStateClient(SerpentSerializable snake,List<FoodSerializable> foods) {

        this.snake = snake;
        this.foods = foods;
    }
     /**
     * Retourne l'objet SerpentSerializable représentant le serpent du joueur.
     *
     * @return Le serpent (SerpentSerializable) du joueur.
     */
    public SerpentSerializable getSnake(){
        return snake;
    }
     /**
     * Retourne la liste des objets FoodSerializable représentant les aliments présents dans le jeu du client.
     *
     * @return La liste des aliments (FoodSerializable) dans le jeu coté client.
     */
     public List<FoodSerializable> getFoods(){
        return foods;
    }
}
