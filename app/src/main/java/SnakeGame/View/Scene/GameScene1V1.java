package SnakeGame.View.Scene;

import SnakeGame.App;
import SnakeGame.Controller.ButtonFactory;
import SnakeGame.Model.GameConfig;
import SnakeGame.View.GameViewFolder.SoloView.Game1V1;
import javafx.scene.Scene;

public class GameScene1V1 extends GameScene{
    
    public GameScene1V1(GameConfig config){
        super(config);
        initScene();
    }
    @Override
    public void initScene() {
        setScene(new Scene(getRoot(), App.getScreenWidth(), App.getScreenHeight()));
        setGameView(new Game1V1(this));
        addRoot(
            ButtonFactory.createButton(100, 500,"Menu bouton",
            ()->   
            SceneManager.getInstance().changeScene(new MenuScene()))
            );

    }
    
}