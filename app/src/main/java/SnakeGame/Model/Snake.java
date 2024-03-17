package SnakeGame.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import SnakeGame.App;
import SnakeGame.Controller.Direction;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Snake {
    private List<Segment> snake = new ArrayList<>();
    private UUID id;
    private Segment head;
    private double speed = 20;
    private boolean is_dead;
    private int vie = 0;
    public boolean is2v2 = false;
    private Direction currentDirection;
    public Snake(){
        is_dead=false;
    }
    public Snake(Segment head){
        this.head = head;
        this.id=UUID.randomUUID();
        snake.add(head);
        currentDirection=Direction.RIGHT;
        is_dead=false;
    }

    public Segment getHead(){return this.head;}
    public Node getHeadNode(){return head.getSegment();}
    public double getHeadX(){return head.getSegX();}
    public double getHeadY(){return head.getSegY();}
    public Segment getTail(){return snake.get(snake.size()-1);}
    public double getVitesse(){return this.speed;}
    public Direction getDirection(){return currentDirection;}
    public UUID getId(){return this.id;}
    public boolean isDead(){return is_dead;}
    public List<Segment> getSnake(){return this.snake;}
    public int getSize(){return snake.size();}

    public void setIsDead(boolean isDead){this.is_dead=isDead;}
    public void setId(UUID id){this.id=id;}
    public void setDirection(Direction direction){this.currentDirection = direction;}
    public void setVitesse(double x){ this.speed = x;}
    public void setSnakeSize(int x,Pane root){
        for(int i=1;i<x;i++){
            CircleSegment segment=new CircleSegment(600-(i*10), 600);
            snake.add(segment);
            root.getChildren().addAll(segment);
        }
    }

    public void MoveHeadX(double x) {
        head.updateSegmentsList(snake);
        snake.get(0).move(x, 0);
    }
    public void MoveHeadY(double y) {
        head.updateSegmentsList(snake);
        snake.get(0).move(0, y);
    }
    
    public void setBodySnakePos(double x,double y){
        for (int i = 0; i <snake.size(); i++) {
            snake.get(i).move(x,0);
            snake.get(i).move(0,y);
        }
    }
    public void addToList(Segment seg){snake.add(seg);}
    
    public void autoMove(Direction direction){
        switch(direction){
            case UP : this.MoveHeadY(-(this.getVitesse())); break;
            case DOWN : this.MoveHeadY(this.getVitesse()); break;
            case LEFT : this.MoveHeadX(-(this.getVitesse())); break;
            case RIGHT : this.MoveHeadX(this.getVitesse()); break;
            default : break;
        }
    }
    public boolean isTouchingBorder() {
        return getHead().getSegX() < 0 || getHead().getSegX() > App.getScreenWidth()
                || getHead().getSegY() < 0 || getHead().getSegY() > App.getScreenHeight();
    }
    public boolean isTouchingSnake(Snake serpent) {

        for (int i = 0 ; i < serpent.getSegmentBody().size() ; i++){ 
            if ( this.getHead().getSegX() == serpent.getSegmentBody().get(i).getSegX()
            && this.getHead().getSegY() == serpent.getSegmentBody().get(i).getSegY()){
                return true;
            }
        }
        return false;
    }

    public boolean isTouchingHimself(){
        for (int i = 1 ; i < snake.size() ; i++){ 

            if(getHead().getSegX() == snake.get(i).getSegX() && getHead().getSegY() == snake.get(i).getSegY()){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Segment> getSegmentBody(){
        return (ArrayList<Segment>) snake;
    }


}
