package SnakeGame.View.GameViewFolder.MultiView;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import SnakeGame.App;
import SnakeGame.Model.CircleSegment;
import SnakeGame.Model.Food;
import SnakeGame.Model.FoodDeserialize;
import SnakeGame.Model.FoodSerializable;
import SnakeGame.Model.GameConfig;
import SnakeGame.Model.Segment;
import SnakeGame.Model.Snake;
import SnakeGame.Model.SerpentDeserialise;
import SnakeGame.Model.SerpentSerializable;
import SnakeGame.View.GameViewFolder.GameView;
import SnakeGame.View.Scene.EndGameScene;
import SnakeGame.View.Scene.GameScene;
import SnakeGame.View.Scene.SceneManager;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
/**
 * Classe GameViewMultiClient implémentant GameView pour le mode multijoueur.
 * Gère l'affichage et la mise à jour de l'état du jeu pour un client.
 */
public class GameViewMultiClient implements GameView{
    private static Gson gson=new Gson();
    private GameScene gameScene;
    private double deltaX, deltaY;//Distance entre la souris et le serpent dans le client
    private double moveX, moveY;//Deplacement du serpent
    private double snakeSpeed=4.00;//client
    private List<Food> foodList;
    private List<Snake> snakeList;
    private List<SerpentSerializable> snakeListSerializable;
    private List<FoodSerializable> foodListSerializables;
    private Snake snakePlayer;
    private GameConfig config;
    /**
     * Constructeur pour GameViewMultiClient.
     *
     * @param gameScene La scène de jeu.
     * @param config    La configuration du jeu.
     */
    public GameViewMultiClient(GameScene gameScene,GameConfig config){
        snakeListSerializable=new LinkedList<>();
        foodListSerializables=new LinkedList<>();
        this.config=config;
        this.gameScene=gameScene;
        
        snakeList=new LinkedList<>();
        foodList=new LinkedList<>();
        Random random = new Random();
        double posX = Math.floor(random.nextDouble(config.getSizeMapHeight())); // Génère un multiple de 20 pour la position X
        double posY = Math.floor(random.nextDouble(config.getSizeMapWidth())); // Génère un multiple de 20 pour la position Y
        snakePlayer=new Snake(new CircleSegment(posX, posY));//client
        gameScene.addRoot(snakePlayer.getHeadNode());//client
        snakePlayer.setSnakeSize(10, gameScene.getRoot());//On definit la taille initial du serpent à 10
        snakeList.add(snakePlayer);

        setupMouseMovement();
        animate();

        

    }
    /**
     * Configure le mouvement du serpent en réponse aux mouvements de la souris.
     */
    private void setupMouseMovement() {
         gameScene.getScene().setOnMouseMoved(event -> {
            double sceneMouseX = event.getSceneX();
            double sceneMouseY = event.getSceneY();

            // Conversion des coordonnées de la scène en coordonnées locales pour le Pane
            Point2D paneCoords = gameScene.getRoot().sceneToLocal(sceneMouseX, sceneMouseY);

            // Mise à jour des variables mouseX et mouseY
            double mouseX = paneCoords.getX();
            double mouseY = paneCoords.getY();
            deltaX = mouseX - ((Circle) snakePlayer.getHead()).getCenterX();
            deltaY = mouseY - ((Circle) snakePlayer.getHead()).getCenterY();
    
            
        });
    } 
    /**
    * Anime le jeu, mettant à jour les éléments graphiques et les interaction dans le jeu.
    */
    @Override
    public void animate() {
        new AnimationTimer(){
            
            @Override
            public void handle(long now) {

                snakeAnimation();
                checkCollisionWithOtherSnakes();
                updateSnakes(snakeListSerializable);
                foodUpdate();
                checkCollisionWithFoodInList();
     
            }
            
        }.start();;
    }
    /**
     * Vérifie et gère les collisions entre le serpent et les aliments.
     *
     * @param food L'objet Food à vérifier pour la collision.
     */
    public void checkCollisionWithFood(Food food) {
        for(Snake snake: snakeList){
            if (snake.getHeadNode().getBoundsInParent().intersects(food.getBoundsInParent())) {
                    // Collision détectée entre le serpent et la nourriture 'food'
                    foodGenerate(food);
                    CircleSegment rec = new CircleSegment(snakePlayer.getTail().getSegX(),snakePlayer.getTail().getSegY());
                    snake.addToList(rec);
                    gameScene.addRoot(rec);
                }
            }
     }
     /**
      * Verifie pour chaque bonus du jeu si un serpent le touche.
      **/
     public void checkCollisionWithFoodInList(){
         for(int i=0;i<foodList.size();i++){
             checkCollisionWithFood(foodList.get(i));
         }
     }

    /**
     * Génère de nouveaux aliments à des emplacements aléatoires sur la scène.
     *
     * @param food L'objet Food à régénérer.
     */
    public void foodGenerate(Food food) {
        // TODO Auto-generated method stub
        gameScene.removeRoot(food);
        foodList.remove(food);
        food=new Food(App.getScreenWidth(),App.getScreenHeight());
        foodList.add(food);
        gameScene.addRoot(food);
    }
    
    /**
     * Met à jour la foodList en la synchronisant par rapport au serveur.
     */
    public void foodUpdate(){
        if(foodList.size()==0){
            for(FoodSerializable food :foodListSerializables){
                Food f =FoodDeserialize.foodDeserialize(food);
                foodList.add(f);
                gameScene.addRoot(f);
            }
        }
        else{
            for(FoodSerializable food :foodListSerializables){
                updateFoodId(food);
            }
        }
    }
    /**
     * 
     * @param foodS
     */
    public void updateFoodId(FoodSerializable foodS){
        for(Food food:foodList){
            if(food.getUuid().equals(foodS.getUId())){
                food.setCenterX(foodS.getX());
                food.setCenterY(foodS.getY());
            }
        }
    }


    @Override
    public Food getFood() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFood'");
    }
    @Override
    // Met à jour et anime le serpent en fonction de la position actuelle de la souris.
    public void snakeAnimation() {
        Point2D movement = snakeMovement();

        snakePlayer.MoveHeadX(movement.getX());
        snakePlayer.MoveHeadY(movement.getY());
        handleScreenBoundaries();
        centerViewOnSnake(snakePlayer.getHeadX(),snakePlayer.getHeadY(),gameScene.getRoot());
    }
    
    //Calcule et renvoie le nouveau déplacement en X et Y pour la tête du serpent vers la souris
    private Point2D snakeMovement() {
        double angleToMouse = Math.atan2(deltaY, deltaX);
        moveX = Math.cos(angleToMouse) * snakeSpeed;
        moveY = Math.sin(angleToMouse) * snakeSpeed;
        return new Point2D(moveX, moveY);
    }

    /*Methode qui gère le cas où la tête du serpent dépasse les limites de l'écran
    en déplaçant le serpent de l'autre côté*/

    private void handleScreenBoundaries() {
        if(snakePlayer.getHeadX() >= config.getSizeMapWidth()) snakePlayer.setBodySnakePos(-config.getSizeMapWidth(),0);
        if(snakePlayer.getHeadY() >= config.getSizeMapWidth()) snakePlayer.setBodySnakePos(0,-config.getSizeMapHeight());
        if(snakePlayer.getHeadX() <= 0) snakePlayer.setBodySnakePos(config.getSizeMapWidth(),0);
        if(snakePlayer.getHeadY() <= 0) snakePlayer.setBodySnakePos(0,config.getSizeMapHeight());
    }
    //Ajuste la vue pour que le serpent reste centré à l'écran lors de son déplacement
    public void centerViewOnSnake(double x,double y ,Pane root){
        double posX = x - App.getScreenWidth()/ 2;
        double posY = y - App.getScreenHeight() / 2;

        root.setTranslateX(-posX);
        root.setTranslateY(-posY);

    }
    /**
     * Methode pour communiquer l'etat du client au serveur.
     * @return L'etat courrant du jeu coté client.
     */
    public String currentGameState(){
        SerpentSerializable snake=new SerpentSerializable(snakePlayer,moveX,moveY);
        GameStateClient gameState=new GameStateClient(snake, foodListSerializables);
        String res =gson.toJson(gameState);
        return res;
    }
    /**
     * Met à jour la view du client par rapport à l'état du serveur.
     * @param json etat courant du serveur.
     */
    public void updateClientView(String json){
        // Tentative de désérialisation du JSON
        GameStateServer gameState;
        try {
            gameState = gson.fromJson(json, GameStateServer.class);
        } catch (JsonSyntaxException e) {
            System.err.println("Erreur de désérialisation: " + e.getMessage());
            return;
        }
        // Vérifier si les données sont valides
        if (gameState == null) {
            System.err.println("État du jeu reçu est invalide.");
            return;
        }
        //Mets à jour la liste des objets serializables.
        snakeListSerializable=gameState.getSerpents();
        foodListSerializables=gameState.getFoods();
    }
    // Méthodes pour mettre à jour les serpents et la nourriture sur le client
    private void updateSnakes(List<SerpentSerializable> serpents) {
       
        for(int i=0;i<serpents.size();i++){
            if(!serpents.get(i).getId().equals(snakePlayer.getId())){
                if(isInList(serpents.get(i).getId())){       
                    Snake serpentD=getSerpentId(serpents.get(i).getId());
                    if(serpents.get(i).isDead()){
                     removeSnake(serpentD);
        
                    }
                    else{
                        moveSnakeI(serpentD, serpents.get(i));
                    }
                }
                else{
                    
                    Snake newS=SerpentDeserialise.deserialize(gson.toJson(serpents.get(i)));
                    snakeList.add(newS);
                    addSnake(newS);
                }
            }
        }
    }
    /**
     * Deplace le serpent des autre joueur sur le client en fonction.
     * @param serpent
     * @param serpentMovement
     */
    public void moveSnakeI(Snake serpent,SerpentSerializable serpentMovement){
        serpent.MoveHeadX(serpentMovement.getDeltaX());
        serpent.MoveHeadY(serpentMovement.getDeltaY());
        
        if(serpent.getHeadX() >= config.getSizeMapWidth()) serpent.setBodySnakePos(-config.getSizeMapWidth(),0);
        if(serpent.getHeadY() >= config.getSizeMapWidth()) serpent.setBodySnakePos(0,-config.getSizeMapHeight());
        if(serpent.getHeadX() <= 0) serpent.setBodySnakePos(config.getSizeMapWidth(),0);
        if(serpent.getHeadY() <= 0) serpent.setBodySnakePos(0,config.getSizeMapHeight());
    }
    public boolean isInList(UUID id){
        for(int i=0;i<snakeList.size();i++){
            if(snakeList.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    /**
     * 
     * @param id id du serpent.
     * @return le serpent de qui à son UUID égale à id.
     */
    public Snake getSerpentId(UUID id){
        for(int i=0;i<snakeList.size();i++){
            if(snakeList.get(i).getId().equals(id)){
                return snakeList.get(i);
                
            }
        }
        return null;
    }
    /**
     * Ajout le serpent sur la scene du client.
     * @param serpent Le serpent que l'on veut ajouter.
     */
    public void addSnake(Snake serpent){
        //On parcourt la liste de segment pour ajouter chaque segment du serpent au root.
        for(int i=0;i<serpent.getSize();i++){
            gameScene.addRoot(serpent.getSnake().get(i).getSegment());
        }
    }
    public void removeSnake(Snake serpent){
        //On parcourt la liste de segment pour ajouter chaque segment du serpent au root.
        for(int i=0;i<serpent.getSize();i++){
            gameScene.removeRoot(serpent.getSnake().get(i).getSegment());
        }
    }
    /**
     * Vérifie si le serpent du joueur entre en collision avec un autre serpent.
     */
    public void checkCollisionWithOtherSnakes() {
        for (Snake otherSnake : snakeList) {
            // Assurer que nous ne vérifions pas de collision avec le serpent du joueur lui-même
            if (!otherSnake.getId().equals(snakePlayer.getId())) {
                for (Segment segment : otherSnake.getSnake()) {
                    CircleSegment seg=(CircleSegment) segment;
                    if (snakePlayer.getHeadNode().intersects(seg.getBoundsInParent())) {
                        // Collision détectée
                        handleCollisionWithSnake();
                        return; // Sortie immédiate après la détection d'une collision
                    }
                }
            }
        }
    }
    /**
     * Gère les actions à effectuer lorsqu'une collision avec un autre serpent est détectée.
     * @param otherSnake Le serpent avec lequel la collision a eu lieu.
     */
    private void handleCollisionWithSnake() {
        snakePlayer.setIsDead(true);
        gameScene.getRoot().getChildren().removeAll(gameScene.getRoot().getChildren());
        SceneManager.getInstance().changeScene(new EndGameScene());
    }

    
}
