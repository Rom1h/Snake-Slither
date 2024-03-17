package SnakeGame.View.Scene;

import SnakeGame.Model.GameConfig;

/**
 * Classe utilitaire pour la création de scènes de jeu.
 * Cette classe fournit des méthodes statiques pour créer des scènes de jeu spécifiques.
 */
public class GameSceneFactory {

    /**
     * Crée une nouvelle scène de jeu multi-joueur.
     *
     * @param config La configuration du jeu associée à la scène.
     * @return Une instance de MultiGameScene configurée selon les paramètres fournis.
     */
    public static MultiGameScene createMultiGameScene(GameConfig config) {
        MultiGameScene multiScene = new MultiGameScene(config);
        multiScene.getRoot().setPrefSize(config.getSizeMapWidth(), config.getSizeMapHeight());
        return multiScene;
    }
}
