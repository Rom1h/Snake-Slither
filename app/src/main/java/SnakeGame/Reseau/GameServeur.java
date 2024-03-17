package SnakeGame.Reseau;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import SnakeGame.View.GameViewFolder.MultiView.GameViewMultiServer;
/**
 * Class qui gère le lancement du serveur et son état gameVie du mode multijoueur.
 * Gère les connexions clients, reçoit les mises à jour 
 * de l'état du jeu et les distribue à tous les clients connectés.
 */
public class GameServeur{
    static GameViewMultiServer serverView;
    public static int port = 13000;
    private static ExecutorService poolThread = Executors.newFixedThreadPool(10); // Pool de 10 threads
    private static List<PrintWriter> clientWriters = new CopyOnWriteArrayList<>();
    /**
     * Point d'entrée principal du serveur.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        serverView=new GameViewMultiServer();//On initialise la view du serveur (son état initial).
        try (ServerSocket ssock = new ServerSocket(port)) {
            System.out.println("server: Waiting for clients to connect...");
            
            while (true) {
                Socket csock = ssock.accept();
                System.out.println("server: Connected to client.");
                PrintWriter csock_pw = new PrintWriter(csock.getOutputStream(), true);                
                clientWriters.add(csock_pw);
                ClientHandler clientThread = new ClientHandler(csock, clientWriters);
                poolThread.execute(clientThread);
            }
        }
    }
    /**
     * Classe interne ClientHandler pour gérer les interactions avec chaque client connecté.
     */
    private static class ClientHandler implements Runnable {
        private Socket csock;
        private List<PrintWriter> clientWriters;

        public ClientHandler(Socket csock, List<PrintWriter> clientWriters) {
            this.csock = csock;
            this.clientWriters = clientWriters;
        }



        public void run() {
            try {
                BufferedReader csock_br = new BufferedReader(new InputStreamReader(csock.getInputStream()));
                String s;
               
                while ((s = csock_br.readLine()) != null) {
                    serverView.updateServerClient(s);
                    // Envoyer l'état actuel du jeu au client
                   
                    String gameState = serverView.currentGameState();
                    for (PrintWriter writer : clientWriters) {
                        writer.println(gameState);
                        ;
                    }
                    
                }
            } catch (IOException e) {
                System.err.println("Exception in client handler: " + e.getMessage());
            }finally {
                try {
                    csock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}