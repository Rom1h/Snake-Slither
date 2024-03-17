package SnakeGame.Model;


/**
 * Class pour la désérialisation des objets FoodSerializable.
 * Permet de convertir des objets FoodSerializable en objets Food.
 */
public class FoodDeserialize {
     /**
     * Convertit un objet FoodSerializable en objet Food.
     *
     * @param food L'objet FoodSerializable à désérialiser.
     * @return Un nouvel objet Food correspondant à l'objet FoodSerializable.
     */
    public static Food foodDeserialize(FoodSerializable food){
        Food f=new Food(food.getUId());
        f.setLayoutX(food.getX());
        f.setLayoutY(food.getY());
        return f;
    }
}
