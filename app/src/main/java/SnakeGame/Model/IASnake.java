package SnakeGame.Model;

import SnakeGame.Controller.Direction;
import SnakeGame.View.GameViewFolder.GameView;

public class IASnake extends Snake{

    GameView gameView;
    
    public IASnake(GameView gameView) {
        super(new RectangleSegment(0,0));
        this.gameView = gameView;
    }
    public boolean isObjectAt(double x,double y){return false;}

    public void changeDir(){
        if(this.getHeadNode().getTranslateX()<gameView.getFood().getLayoutX()){
            if(this.getDirection() != Direction.LEFT){this.setDirection(Direction.RIGHT);}
            else{ this.setDirection(Direction.UP);}
        }else if (this.getHeadNode().getTranslateX()>gameView.getFood().getLayoutX()){
            if(this.getDirection()!=Direction.RIGHT){this.setDirection(Direction.LEFT);}
            else {this.setDirection(Direction.UP);}
        }else if(this.getHeadNode().getTranslateY()<gameView.getFood().getLayoutY()){
            if(this.getDirection()!=Direction.UP){this.setDirection(Direction.DOWN);}
            else{ this.setDirection(Direction.LEFT);}
        }else if(this.getHeadNode().getTranslateY()>gameView.getFood().getLayoutY()){
           if(this.getDirection()!=Direction.DOWN){this.setDirection(Direction.UP);}
            else{ this.setDirection(Direction.LEFT);}
        }
    }
    
}
