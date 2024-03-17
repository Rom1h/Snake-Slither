package SnakeGame.View.GameViewFolder;

import SnakeGame.Model.Food;
/**
 * Interface GameView pour représenter la vue du jeu.
 */
public interface GameView {

    /**
     * Méthode pour animer l'ensemble du jeu.
     */
    public void animate();

    /**
     * Méthode pour animer le mouvement du serpent dans le jeu.
     */
    public void snakeAnimation();

    public Food getFood();
}