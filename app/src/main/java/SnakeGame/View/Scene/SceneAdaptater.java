package SnakeGame.View.Scene;

import javafx.scene.Scene;

/**
 * Interface définissant un adaptateur de scène.
 * Les classes qui implémentent cette interface doivent fournir une méthode pour obtenir la scène associée.
 */
public interface SceneAdaptater {

    /**
     * Obtient la scène associée à l'implémentation de l'adaptateur de scène.
     *
     * @return La scène associée.
     */
    Scene getScene();
}