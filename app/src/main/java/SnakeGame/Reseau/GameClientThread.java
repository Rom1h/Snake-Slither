    package SnakeGame.Reseau;


    import java.io.PrintWriter;

import SnakeGame.View.GameViewFolder.MultiView.GameViewMultiClient;
/**
 * Classe GameClientThread étend Thread et est utilisée pour envoyer continuellement 
 * l'état actuel du jeu depuis le client vers le serveur.
 */
public class GameClientThread extends Thread {
    
    PrintWriter sock_pw;
    GameViewMultiClient state;
    /**
     * Constructeur pour GameClientThread.
     *
     * @param name Le nom du thread.
     * @param sock_pw Le PrintWriter pour envoyer des données au serveur.
     * @param state L'état actuel du jeu côté client à envoyer.
     */
    public GameClientThread(String name, PrintWriter sock_pw,GameViewMultiClient state)
        {
            super(name);
            this.sock_pw = sock_pw;
            this.state = state;
        }
         /**
     * Exécute le thread, envoyant continuellement l'état actuel du jeu au serveur.
     */
    @Override
    public void run(){
        try{
            while(true){   
                String s=state.currentGameState();               
                sock_pw.println(s);
            }
        }
        catch(Exception e){
            System.err.println("chat_server_writer: Exception occured:\n" + e);
        }
    }
}