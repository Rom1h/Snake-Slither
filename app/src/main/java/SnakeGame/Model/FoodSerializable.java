package SnakeGame.Model;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Classe FoodSerializable pour représenter une version sérialisable de l'objet Food.
 */
public class FoodSerializable {
    private double x,y;
    private UUID id;
    /**
    * Constructeur pour FoodSerializable.
    * Crée une version sérialisable d'un objet Food, capturant ses coordonnées et identifiant unique.
    * @param food L'objet Food à sérialiser.
    */
    public FoodSerializable(Food food){
        this.x=food.getLayoutX();
        this.y=food.getLayoutY();
        this.id=food.getUuid();

    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public UUID getUId(){
        return id;
    }
    /**
    * Convertit une liste d'objets Food en une liste de FoodSerializable.
    *
    * @param foods La liste d'objets Food à convertir.
    * @return Une liste de FoodSerializable correspondant aux objets Food donnés.
    */
    public static LinkedList<FoodSerializable> convertListOfFood(List<Food> foods){
        LinkedList<FoodSerializable> listFoodSerializables=new LinkedList<>();
        for(int i=0;i<foods.size();i++){
            listFoodSerializables.add(new FoodSerializable(foods.get(i)));
        }
        return listFoodSerializables;
    }
}
