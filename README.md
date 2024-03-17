
# Snake-Slither.io

## Compilation du projet

### **Mode Normal**
Pour compiler et exécuter le projet en mode normal :

```shell
./gradlew build
./gradlew run
```

### **Mode Réseau**
Pour utiliser le mode réseau :

- Sur un terminal, exécutez la commande pour lancer le serveur de jeu :

  ```shell
  ./gradlew runGameServer
  ```

- Sur un autre terminal, compilez et exécutez le client :

  ```shell
  ./gradlew build
  ./gradlew run
  ```

Ensuite, lancez le jeu en mode multijoueur.

**Remarque :** Il est important de lancer le serveur avant les clients pour éviter les erreurs de connexion.

## Gestion des scènes

Nous avons implémenté une interface `Adapter` possédant une méthode unique `getScene`. Toutes nos scènes implémentent cette interface, permettant au `SceneManager` (un singleton) de gérer le changement de scènes en conservant une instance de la scène courante.

## Gestion du serpent et des segments

Nous avons développé deux types de segments : un pour le mode multijoueur et un pour le mode classique. Pour cela, nous utilisons l'interface `Segment` qui définit les méthodes pour déplacer les segments et récupérer leurs coordonnées. Les classes `CircleSegment` et `RectangleSegment` implémentent `Segment` et étendent respectivement les classes `Circle` et `Rectangle` de JavaFX, permettant une adaptation au mode de jeu.

## Gestion du réseau

Le serveur local (localhost) est utilisé pour le réseau.

- **Serveur :** Une classe `Server` crée un pool de threads et attend la connexion des joueurs, communiquant l'état courant du jeu à chaque client. L'état du serveur est stocké dans une classe `GameViewServer`, qui conserve une liste de serpents et de nourriture sérialisables.

- **Client :** Le client utilise deux threads : un pour envoyer son état au serveur (déplacement du serpent, liste de nourriture) et l'autre pour mettre à jour la vue du client (`GameViewMultiClient`) selon l'état reçu du serveur.

- **Sérialisation/désérialisation :** Le serpent est sérialisé en envoyant la position de chaque segment et les déplacements en X et Y, maintenant une fluidité dans le mouvement des serpents des autres joueurs, même si la synchronisation des positions reste un défi.

## Modes de jeu

Trois modes de jeu sont actuellement disponibles :

- **Snake Solo Mode :** Le mode solo reprend les règles classiques de Snake, joué contre une intelligence artificielle.

- **Snake Multi Mode :** Un mode 1v1 où les joueurs s'affrontent directement. Les contrôles sont assignés aux touches (ZQSD) pour un joueur et (IJKL) pour l'autre.

- **Mode Multi Joueur :** Contrôlez le serpent avec la souris, mangeant pour grandir. Si un serpent touche la queue d'un autre, il est éliminé, mais peut retenter sa chance.
