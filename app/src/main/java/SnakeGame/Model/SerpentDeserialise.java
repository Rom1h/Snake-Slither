package SnakeGame.Model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Classe pour désérialiser une représentation JSON d'un serpent en objet Serpent.
 * Utilise la bibliothèque Gson pour le processus de désérialisation.
 */
public class SerpentDeserialise {
    //Methode pour deserialiser un serpent
   public static Snake deserialize(String serpentJson) {
        System.out.println(serpentJson);
        Gson gson = new Gson();

        SerpentSerializable serpentSerializable;
        try {
            serpentSerializable = gson.fromJson(serpentJson, SerpentSerializable.class);
        } catch (JsonSyntaxException e) {
            System.err.println("Erreur de désérialisation: " + e.getMessage());
            return null;
        }

        // Vérification de la validité des données désérialisées
        if (serpentSerializable == null || serpentSerializable.getSegmentsList().isEmpty()) {
            System.err.println("Données de serpent sérialisées invalides ou vides.");
            return null;
        }

        // Recréation du serpent
        SegmentSerializable headS = serpentSerializable.getSegmentsList().get(0);
        int value=0;
        
        //tentative d'interpolation de la position du serpent.

        double sommeX=serpentSerializable.getDeltaX()*value;
        double sommeY=serpentSerializable.getDeltaY()*value;

        CircleSegment head = new CircleSegment(headS.getX()+sommeX, headS.getY()+sommeY);
        
        Snake serpentDeserialized = new Snake(head);
        serpentDeserialized.setId(serpentSerializable.getId());
        for (int i = 1; i < serpentSerializable.getSize(); i++) {
            SegmentSerializable segS = serpentSerializable.getSegmentsList().get(i);
            CircleSegment seg = new CircleSegment(segS.getX(), segS.getY());
            serpentDeserialized.addToList(seg);
        }

        return serpentDeserialized;
    }
    
}
