
package SnakeGame.View.Scene;

import SnakeGame.Model.GameConfig;
import SnakeGame.View.GameViewFolder.GameView;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
/**
 * Classe abstraite représentant une scène de jeu.
 * Cette classe fournit une base pour la création de scènes spécifiques au jeu.
 */
public abstract class GameScene implements SceneAdaptater{
    private Scene gameScene;
    private GameView gameView;
    private Pane root;
    private GameConfig config;
    /**
     * Constructeur de la classe GameScene.
     * @param config Configuration du jeu.
     */
    GameScene(GameConfig config){
        this.config=config;
        this.root=new Pane();
    }
    /**
     * Methode abstraite utilisée pour initialiser la scene.
     */
    public abstract void initScene();
    
    /**
     * Definis la scene du jeu.
     * @param gameScene
     */
    public void setScene(Scene gameScene){
        this.gameScene=gameScene;
    }
    /**
     * Definis le gameView de la scene de jeu.
     * @param gameView
     */
    public void setGameView(GameView gameView){
        this.gameView=gameView;
    }
    /**
     * Ajoute les elements entré en parametre au root correspondant au pannel de la scene
     * @param elemt list de Node à ajouter au root
     */
    public void addRoot(Node... elemt){
        root.getChildren().addAll(elemt);
    }
    /**
     * Supprime l'élément entrée en paramètre du root.
     * @param elemt élément à supprimer
     */
    public void removeRoot(Node elemt){
        root.getChildren().remove(elemt);
    }
     /**
     * Renvoie la scene du jeu.
     * @return scene du jeu.
     */
    @Override
    public Scene getScene() {
        return gameScene;
    }

    public Pane getRoot() {
        return root;
    }
    public GameConfig getConfig(){
        return config;
    }
    

}
