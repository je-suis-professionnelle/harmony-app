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
```console sudo docker compose build```
ou
```console sudo docker-compose build```

puis
```console sudo docker compose up'```
ou
```console sudo docker-compose up```

Enfin, accédez à l'URL http://localhost:80 à l'aide de votre navigateur.
