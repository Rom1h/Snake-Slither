package SnakeGame.View.Scene;
import SnakeGame.App;
import SnakeGame.Controller.ButtonFactory;
import SnakeGame.Model.GameConfig;
import SnakeGame.View.GameViewFolder.SoloView.GameViewSolo;
import javafx.scene.Scene;
/**
 * Classe représentant la scène de jeu du mode solo.
 */
public class SoloGameScene extends GameScene{
    
    /**
     * Constructeur de la classe SoloGameScene.
     *
     * @param config La configuration du jeu associée à la scène solo.
     */
    public SoloGameScene(GameConfig config) {
        super(config);
        initScene();
    }

    /**
     * Initialise les éléments de la scène de jeu solo.
     */
    @Override
    public void initScene() {
        setScene(new Scene(getRoot(),App.getScreenWidth(), App.getScreenHeight()));
        setGameView(new GameViewSolo(this));
        addRoot(
            ButtonFactory.createButton(100, 500, "Bouton Menu",
            () -> SceneManager.getInstance().changeScene(new MenuScene())));
    }
    
}
