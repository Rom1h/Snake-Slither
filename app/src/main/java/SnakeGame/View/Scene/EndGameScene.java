package SnakeGame.View.Scene;

import SnakeGame.App;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class EndGameScene implements SceneAdaptater {
    private Scene endGamScene;
    private Pane root;
    private int whichPlayerWin;
    /**
     * Constructeur de la classe MenuScene.
     * Initialise la scène du menu principal avec ses éléments.
     */
    public EndGameScene() {
        root = new Pane();
        endGamScene = new Scene(root, App.getScreenWidth(), App.getScreenHeight());
        
        initialiseMulti();
    }
    public EndGameScene(int wichPlayerWin) {
        root = new Pane();
        endGamScene = new Scene(root, App.getScreenWidth(), App.getScreenHeight());
        this.whichPlayerWin=wichPlayerWin;
        initialise1V1();
    }

    /**
     * Initialise les éléments de la scène du menu.
     */
    public void initialise1V1() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(App.getScreenWidth(), App.getScreenHeight());

        Label winnerLabel = new Label("Le joueur " + whichPlayerWin + " à gagné!");
        winnerLabel.setFont(new Font("Arial", 24));
        winnerLabel.setTextAlignment(TextAlignment.CENTER);

        Button backButton = new Button("Return to Menu");
        backButton.setFont(new Font("Arial", 18));
        backButton.setOnAction(e->{
            SceneManager.getInstance().changeScene(new MenuScene());
        });


        layout.getChildren().addAll(winnerLabel, backButton);
        root.getChildren().add(layout);

    }
    public void initialiseMulti() {
        VBox layout = new VBox(20);
       
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(App.getScreenWidth(), App.getScreenHeight());


        Label loserLabel = new Label("Vous avez perdu!");
        loserLabel.setFont(new Font("Arial", 24));
        loserLabel.setTextAlignment(TextAlignment.CENTER);

        Button backButton = new Button("Retour au menu");
        backButton.setFont(new Font("Arial", 18));
        backButton.setOnAction(e->{
            SceneManager.getInstance().changeScene(new MenuScene());
        });

        layout.getChildren().addAll(loserLabel, backButton);
        root.getChildren().add(layout);
    }
    @Override
    public Scene getScene() {
        return endGamScene;
    }
    
}
