package SnakeGame.Reseau;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import SnakeGame.View.GameViewFolder.MultiView.GameViewMultiClient;


/**
 * Class pour gérer la connexion client dans le mode multijoueur.
 * Cette classe établit une connexion avec le serveur et gère l'envoi 
 * et la réception des mises à jour de l'état du jeu entre le serveur et le client.
 */
public class GameClient{
    private GameViewMultiClient clientView;
    /**
     * Constructeur pour GameClient.
     *
     * @param viewClient La vue du client pour le jeu, utilisée pour gérer l'état côté client.
     */
    public GameClient(GameViewMultiClient viewClient) {
        this.clientView = viewClient;
    }

     /**
     * Démarre le client et établit une connexion avec le serveur de jeu.
     * Crée et lance des threads pour gérer l'envoi et la réception des données de jeu.
     *
     * @param serverAddress L'adresse IP du serveur.
     * @param port Le port sur lequel le serveur écoute.
     * @throws IOException Si une erreur I/O se produit lors de l'établissement de la connexion.
     */
    public void startClient(String serverAddress, int port) throws IOException {
        Socket sock = new Socket(serverAddress, port);
        BufferedReader sock_br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter sock_pw = new PrintWriter(sock.getOutputStream(), true);
        System.out.println("Connection established");
    
        Thread game_client_thread = new GameClientThread("game_client_thr", sock_pw, clientView);
        game_client_thread.start();
        //Thread pour mettre à jour la view du client.
        Thread updateView = new Thread(() -> { 
            String s;
            try {
                while ((s = sock_br.readLine()) != null) {
                    clientView.updateClientView(s);
                    //System.out.println(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Autres actions en cas d'erreur, comme informer l'utilisateur
            } finally {
                try {
                    sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    // Autres actions en cas d'erreur de fermeture du socket
                }
            }
        });
        updateView.start();
    }

}

