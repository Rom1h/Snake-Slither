package SnakeGame.View.GameViewFolder.MultiView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import SnakeGame.Model.Food;
import SnakeGame.Model.FoodSerializable;
import SnakeGame.Model.SerpentSerializable;
/**
 * Classe pour gérer l'état du serveur dans le mode multijoueur.
 * Cette classe s'occupe de la synchronisation de l'état des serpents et des aliments entre le serveur et les clients.
 */
public class GameViewMultiServer{
    private List<SerpentSerializable> snakeListSerializables;
    private List<FoodSerializable> foodListSerializables;
    /**
     * Constructeur pour GameViewMultiServer.
     * Initialise les listes de serpents et d'aliments.
     */
    public GameViewMultiServer(){
        foodListSerializables=new ArrayList<>();
        snakeListSerializables=new LinkedList<>();
        initFoodList();
        
        
    }
     /**
     * Renvoie l'état actuel du jeu en format JSON.
     *
     * @return Une chaîne JSON représentant l'état actuel du jeu côté serveur.
     */
    public String currentGameState(){
        GameStateServer gameState=new GameStateServer(this.snakeListSerializables,foodListSerializables);
        var gson=new Gson();
        return gson.toJson(gameState);
    }
    /**
     * Met à jour l'état du serveur en fonction des informations reçues d'un client.
     *
     * @param json L'état du jeu côté client sous forme de chaîne JSON.
     */
    public void updateServerClient(String json){
        Gson gson = new Gson();

        // Tentative de désérialisation du JSON
        GameStateClient gameClientStat;
        try {
            gameClientStat = gson.fromJson(json, GameStateClient.class);
        } catch (JsonSyntaxException e) {
            System.err.println("Erreur de désérialisation: " + e.getMessage());
            return;
        }
        updateSnakeId(gameClientStat.getSnake());
        updateFoodsList(gameClientStat.getFoods());

    }
     /**
     * Met à jour l'état d'un serpent spécifique basé sur les informations reçues.
     *
     * @param snake Le serpent à mettre à jour.
     */
    public void updateSnakeId(SerpentSerializable snake){
        for (SerpentSerializable serpent : snakeListSerializables) {
            if(serpent.getId().equals(snake.getId())){
                if(serpent.isDead()){
                    snakeListSerializables.remove(serpent);
                    return;
                }
                serpent.setSegmentList(snake);
                serpent.setDeltaX(snake.getDeltaX());
                serpent.setDeltaY(snake.getDeltaY());
                return;
            }
        }
        snakeListSerializables.add(snake);
       
    }
    /**
     * Met à jour la liste des bonus en fonction des informations reçues.
     *
     * @param foods La liste des aliments à mettre à jour.
     */

    public void updateFoodsList(List<FoodSerializable> foods){
        for(FoodSerializable food:foods){
            modifyFoodId(food);
        }
    }
    /**
     * Met à jour l'element de la liste par le l'element en paramètre.
     *
     * @param foods element qu'on veut modifier dans la liste.
     */
    public void modifyFoodId(FoodSerializable foods){
        for(FoodSerializable food :  foodListSerializables){
            if(foods.getUId().equals(food.getUId())){food=foods;return;}
        }
    }
     /**
     * Initialise la liste des aliments présents dans le jeu.
     */
    public void initFoodList(){
        for(int i=0;i<400;i++){
            Food food=new Food(4000, 4000);
            FoodSerializable foodSerializable=new FoodSerializable(food);
            foodListSerializables.add(foodSerializable);
        }
    }
}
