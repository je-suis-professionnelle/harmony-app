# harmony

Une application d'harmonisation des dépenses.

Le frontend est développé avec Vue.js 3 et le backend est développé avec Spring Boot. Elle offre une interface interactive aux utilisateurs, leur permettant de s'inscrire, créer des groupes, gérer les membres, et gérer les dépenses du groupe (les membres peuvent même ajouter l'image du ticket de la dépense !).
Les requêtes HTTP sont gérées avec Axios et les formulaires utilisent Vee-Validate et Yup.

## Prérequis

Pour lancer ce projet, vous devez avoir installé :

- Docker
- Git pour cloner le projet

## Installation

Tout d'abord clonez le projet dans un dossier à l'aide de git clone https://github.com/je-suis-professionnelle/harmony-app

Puis, ouvrez le dossier harmony-app dans votre terminal et entrez les commandes suivantes :
```sudo docker compose build```
ou
```sudo docker-compose build```

puis
```sudo docker compose up```
ou
```sudo docker-compose up```

Enfin, accédez à l'URL http://localhost:80 à l'aide de votre navigateur.

## Utilisation

L'utilisateur doit avant tout se créer un compte puis se connecter pour accéder à ses groupes.
Une fois connecté, il a accès à tous ses groupes, peut les filtrer grâce à une barre de recherche, et en créer un nouveau.
Lorsqu'il clique sur un des groupes, il a accès aux dépenses du groupe qu'il peut filtrer grâce à une barre de recherche. Il peut en créer des nouvelles, ajouter une image liée à cette dépense, et n'aura qu'à cliquer sur la dépense pour visualiser l'image. Il peut supprimer la dépense grâce à un bouton situé sur la dépense.
Il peut ajouter des membres à ce groupe, si le membre possède un compte dans cette application. Il peut également retirer les membres qu'il veut, à condition qu'ils n'aient plus de dépenses en cours dans le groupe.
Il peut gérer les labels de dépense du groupe, en ajouter ou en supprimer.
Il a évidemment accès à l'onglet balance, qui montre combien chaque membre à dépensé selon le montant visé chacun. Dans l'onglet équilibrer, il voit qui doit combien à qui, pour arriver au montant visé chacun.
