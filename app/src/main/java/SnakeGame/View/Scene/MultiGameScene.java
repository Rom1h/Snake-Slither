package SnakeGame.View.Scene;
import SnakeGame.App;
import SnakeGame.Controller.ButtonFactory;
import SnakeGame.Model.GameConfig;
import SnakeGame.View.GameViewFolder.MultiView.GameViewMultiClient;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
/**
 * Classe représentant la scène de jeu du mode multijoueur.
 */
public class MultiGameScene extends GameScene{
    private GameViewMultiClient clientView;

    /**
     * Constructeur de la classe MultiGameScene.
     *
     * @param config La configuration du jeu associée à la scène multijoueur.
     */
    MultiGameScene(GameConfig config) {
        super(config);
        initScene();
        clientView = new GameViewMultiClient(this, config);
    }

    /**
     * Initialise les éléments de la scène de jeu multijoueur.
     */
    @Override
    public void initScene() {

        Group mainGroup = new Group();
        Button menuButton = ButtonFactory.createButton(100, 500, "Bouton Menu",
                () -> SceneManager.getInstance().changeScene(new MenuScene()));

        VBox buttonContainer = new VBox();
        buttonContainer.getChildren().add(menuButton);

        mainGroup.getChildren().addAll(getRoot(), buttonContainer);

        VBox.setVgrow(buttonContainer, Priority.ALWAYS);
        VBox.setMargin(buttonContainer, new Insets(10, 10, 10, 10));
        setScene(new Scene(mainGroup, App.getScreenWidth(), App.getScreenHeight()));
        setGameView(clientView);
    }

    /**
     * Obtient la vue client associée à la scène multijoueur.
     *
     * @return La vue client multijoueur.
     */
    public GameViewMultiClient getClientView() {
        return clientView;
    }
}
