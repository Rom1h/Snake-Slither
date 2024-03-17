Compiler le projet :
        Mode Normal :   ./gradlew build
                        ./gradlew run
        Mode reseau : sur un terminal executer la commande ./gradlew runGameServer
                    puis sur un autre terminal vous pouvez executer ./gradlew build 
                                                                    ./gradlew run puis lancer en multijoueur 
                (il est important de lancer le serveur avant les clients pour eviter les erreurs de connexion).
                
                

Gestion des scenes :
    Pour les scènes nous avons implementé une interface adaptater possedant une unique méthode getScene.
    Cette interface nous l'implementons à toutes nos scenes.
    Cela nous permets de gerer le changement de scenes à l'aide de la classe singleton SceneManager qui à pour instance la scène courante.

Gestion du serpent et des segments :
    Pour le serpent nous avons implémenté deux types de segments un pour le multi et un pour le mode classique.
    Pour cela nous avons créé une interface segement possedant les methodes pour deplacer les segments recuperer les coordonné etc.
    
        La classe CircleSegment implements cette interface et etends la classe Circle de javaFx.
        La classe RectangleSegment implements egalement segments et entends la classe Rectangle de JavaFx.
    
    Ainsi en fonction du mode de jeu le serpent va utiliser des segments differents et le deplacement sera different.

Gestion du Reseau :
    Nous utilisons le serveur local (localhost).
    
    Server :
        Pour le reseau nous avons créé une classe serveur qui va creer un pool de thread et va attendre la connexion des joueurs.
        Lorsque un joueur se connecte le serveur va lui communiquer son état courant.(Il le fait pour chaque client).
        Pour stocker l'état du serveur nous avons créé une classe GameViewServer qui va stocker une liste de serpent et de food serialisable.
        
    Client :
        Coté client on va avoir deux threads qui vont s'executer en simultané l'un va envoyer son etat au serveur (le deplacement du serpent, la liste de food).
        et l'autre va mettre à jour la view du client (GameViewMultiClient) en fonction de l'etat du serveur qu'il aura recu.
    
    Classe de serialisation/deserialisation:
        Serialisation du serpent : Pour la serialisation du serpent nous avons decider d'envoyer à la fois la liste de de la position de chaque segment mais egalement 
        le deplacement en X et Y du serpent.Cela nous permets de garder une fluidité entre le deplacement des serpents des autres joueurs.
        (Le seul défaut est que la position des serpents n'est malheureusement pas synchronisée.) 

Mode de jeu :
    Nous proposons actuellement trois modes de jeu, tous accessibles directement depuis le menu du jeu :

    Snake solo mode:Dans ce mode solo, vous retrouverez les règles classiques du jeu Snake, avec la particularité que vous jouez contre une intelligence artificielle.
    
    Snake multi mode: Ce mode propose un face-à-face en 1v1. Les joueurs s'affrontent directement ; le premier à toucher un bord ou un autre serpent perd la partie. 
                      Le jeu se contrôle avec les touches (ZQSD) pour un joueur et (IJKL) pour l'autre.
    
    Mode multi joueur :Dans ce mode, le serpent est contrôlé à l'aide de la souris. Le but est de manger de la nourriture pour grandir. 
                       Si un serpent touche la queue d'un autre, il perd et est éliminé de la partie.
                       Il peut si il le souhaite retenter ça chance.
        
        
    
    