package SnakeGame.View.Scene;
import javafx.stage.Stage;


/**
 * Classe Singleton pour gérer les scènes dans le jeu Snake.
 * Permet de changer la scène affichée sur la fenêtre principale.
 */
public class SceneManager {
    private static SceneManager instance;
    private Stage primaryStage;

    /**
     * Constructeur privé pour empêcher l'instanciation directe depuis l'extérieur de la classe.
     */
    private SceneManager() {
        // Empêche l'instanciation directe depuis l'extérieur de la classe
    }

    /**
     * Obtient l'instance unique de la classe SceneManager.
     *
     * @return L'instance de la classe SceneManager.
     */
    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    /**
     * Définit la scène principale (Stage) à utiliser par le SceneManager.
     *
     * @param stage Le Stage principal de l'application.
     */
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Change la scène affichée sur la fenêtre principale.
     *
     * @param newScene La nouvelle scène à afficher.
     */
    public void changeScene(SceneAdaptater newScene) {
        primaryStage.setScene(newScene.getScene());
    }

    /**
     * Affiche la fenêtre principale.
     */
    public void show() {
        primaryStage.show();
    }
}
