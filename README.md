# EntreVoisins - Soyez connectés à vos voisins 
##### *Ce README.md vous permettra de connaître les fonctionnalités du projet, et d'y apporter des modifications par la suite si nécessaire.*

### Sommaire
  1. [Informations générales](#informations-générales) ; 
  2. [Captures d'écrans du projet](#photos-du-projet) ;
  3. [Dépendances et technologies utilisées](#technologies-utilisées) ; 
  4. [Installation du projet](#installation) ; 
  5. [Fonctionnalités développées](#fonctionnalitées-développées) ;
  6. [Auteurs et contributeurs du projet](#auteurs-et-contributeurs);
  7. [Version d'Android Studio](#version-android-studio).

#### #Informations générales
***
*Entrevoisins, est une application qui permet à des personnes d’un même quartier de se rendre des petits services : garde d’animaux, petit bricolage, troc d’objets, cours particuliers, etc....*
Entre autres, l'application répondaient aux fonctionnalités suivantes :
 * lister ses voisins ;
 * ajouter un voisin ;
 * suppression d’un voisin.

#### #Photos du projet
***
![Capture de l'écran d'accueil](https://github.com/mmarliacy/OC_PROJETS/blob/main/Entrevoisins/Images%20du%20projets/Screenshot_1630330622.png) "Ecran d'accueil"
![Profil du voisin](https://github.com/mmarliacy/OC_PROJETS/blob/main/Entrevoisins/Images%20du%20projets/Screenshot_1630330859.png) "Profil du voisin et bouton favoris vide"
![Profil du voisin](https://github.com/mmarliacy/OC_PROJETS/blob/main/Entrevoisins/Images%20du%20projets/Screenshot_1630330894.png) "Profil du voisin et bouton favoris plein"
![Voisin dans l'onglet favoris](https://github.com/mmarliacy/OC_PROJETS/blob/main/Entrevoisins/Images%20du%20projets/Screenshot_1630330903.png) "Voisin dans l'onglet favoris"

#### #Technologies utilisées
***
Un certain nombre de dépendances et de bibliothèques ont été utilisées pour ce projet, on compte parmi eux : 
 * [Butterknife](https://github.com/JakeWharton/butterknife/) : Version 10.0.0
 * [Bumptech.glide](https://github.com/bumptech/glide) : Version 4.11.0
 * [Greenrobot:eventbus](https://github.com/greenrobot/EventBus) : Version 3.1.1
 * En plus : [recyclerview-v7](https://developer.android.com/topic/libraries/support-library/packages#v7-recyclerview) : Version 28.0.0 d'Android.

#### #Installation 
***
1. Télécharger le repository à l'adresse URL suivante : https://github.com/mmarliacy/OC_PROJETS/tree/main/Entrevoisins.
2. Extraire de l'archive "Entrevoisins" le dossier d'installation de l'application dans un dossier de destination ou sur le bureau.
3. Ouvrir Android Studio.
4. Afin d'ouvrir le programme sur Android Studio, suivez les instructions suivantes à partir de la barre de menu d'Android Studio : **File -> Open -> Dossier de destination -> Entrevoisins**
5. Android Studio, avec l'intervention de Gradle synchronisera le projet, en cas de technologies ou dépendances "deprecated", mettez à jour le projet, en cliquant de nouveau sur : **File -> Sync Project with Gradle Files**.

#### #Fonctionnalitées développées
***
Le but était donc pour ce projet, de rajouter ces fonctionnalitées supplémentaires : 
 * Au clic sur un utilisateur, on devrait voir y figurer :
  * un bouton de retour fonctionnel qui permettrait de retourner sur l'écran d'accueil ;
  * l'avatar de l'utilisateur ;
  * le nom de l'utilisateur ;
  * un bouton favoris qui permettrait d'ajouter un voisin à la liste de favoris ;
 * Un onglet Favoris dans lequel les utilisateurs marqués comme favoris s’afficheraient.

#### #Auteurs et Contributeurs
***
Ce projet a été réalisé par @Deyine un intervenant d'OpenClassRooms, et j'y ai apporté les fonctionnalités supplémentaires.
> De plus, j'aimerai remercier mon mentor @ LionelMambingo pour son soutien lors de l'implémentation de celles ci.
> Selon lui, "La programmation, c'est de l'apprentissage, une succession de problèmes à résoudre qu'on apprend à connaître au fur et à mesure"

#### #Version Android Studio
***
Ce projet a été réalisé à l'aide d'Android Studio 4.2.2, puis par la version mise à jour 2020.3.1 (Patch 1).





