package SnakeGame.View.Scene;

import java.io.IOException;

import SnakeGame.App;
import SnakeGame.Controller.ButtonFactory;
import SnakeGame.Model.GameConfig;
import SnakeGame.Reseau.GameClient;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Classe représentant la scène du menu principal du jeu Snake.
 * Permet à l'utilisateur de choisir entre les modes de jeu solo et multijoueur.
 */
public class MenuScene implements SceneAdaptater {
    private Scene menuScene;
    private Pane root;

    /**
     * Constructeur de la classe MenuScene.
     * Initialise la scène du menu principal avec ses éléments.
     */
    public MenuScene() {
        root = new Pane();
        menuScene = new Scene(root, App.getScreenWidth(), App.getScreenHeight());
        initialise();
    }

    /**
     * Initialise les éléments de la scène du menu.
     */
    public void initialise() {
        Label label = new Label("Ceci est la page du menu");
        root.getChildren().add(label);
        
        GameConfig soloConfig = new GameConfig(App.getScreenHeight(), App.getScreenWidth(), 10);
        Button snake1v1=ButtonFactory.createButton(App.getScreenWidth()/2.3, 400,"Snake multi mode",
        ()->SceneManager.getInstance().changeScene(new GameScene1V1(soloConfig)));
        
        Button snakeSolo=ButtonFactory.createButton(App.getScreenWidth()/2.3, 300,"Snake solo mode",
                    ()->SceneManager.getInstance().changeScene(new SoloGameScene(soloConfig)));
    
        GameConfig multiConfig = new GameConfig(2000, 2000, 20);
        Button multi = ButtonFactory.createButton(App.getScreenWidth()/2.3,500, "Mode multijoueur", () -> {
            // Changez la scène pour le jeu multijoueur
            MultiGameScene multiGameScene = new MultiGameScene(multiConfig);
            SceneManager.getInstance().changeScene(multiGameScene);

            // Initialisez et démarrez le client de jeu multijoueur
            GameClient gameClient = new GameClient(multiGameScene.getClientView());
            try {
                String serverAddress = "localhost";
                int port = 13000;
                gameClient.startClient(serverAddress, port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        root.getChildren().addAll(snakeSolo, multi,snake1v1);
    }

    /**
     * Obtient la scène du menu principal.
     *
     * @return La scène du menu principal.
     */
    @Override
    public Scene getScene() {
        return menuScene;
    }

    
}
