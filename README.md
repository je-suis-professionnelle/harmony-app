# harmony

Une application d'harmonisation des dépenses.

Le frontend est développé avec Vue.js 3 et le backend est développé avec Spring Boot. Elle offre une interface interactive aux utilisateurs, leur permettant de s'inscrire, créer des groupes, gérer les membres, et gérer les dépenses du groupe.
Les requêtes HTTP sont gérées avec Axios et les formulaires utilisent Vee-Validate et Yup.

Prérequis

Pour lancer ce projet, vous devez avoir installé :

- Docker
- Git pour cloner le projet

Installation

Tout d'abord clonez le projet dans un dossier à l'aide de git clone <url>

Puis, ouvrez les dossier harmony-app dans votre terminal et entrez les commandes suivantes :
'''bash sudo docker compose build''' ou '''bash sudo docker-compose build'''
'''bash sudo docker compose up''' ou '''bash sudo docker-compose up'''

Enfin, accédez à l'URL http://localhost:80 à l'aide de votre navigateur.
