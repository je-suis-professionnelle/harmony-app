import navigationComponent from './components/navigation.js';
import searchComponent from './components/search.js';
import ongletsComponent from './components/onglets.js';
import liste from './components/liste.js';
import itemDepenseComponent from './components/itemDepense.js';
import resultatComponent from './components/resultat.js';

const app = Vue.createApp({
    data() {
        return {
            titre: 'Voyage Palma De Majorque',
            cart:0,
            product: 'Socks',
            brand: 'Vue Mastery',
            selectedVariant: 0,
            details: ['50% cotton', '30% wool', '20% polyester'],
            variants: [
              { id: 2234, color: 'green', image: './assets/images/socks_green.jpg', quantity: 50 },
              { id: 2235, color: 'blue', image: './assets/images/socks_blue.jpg', quantity: 0 },
            ],
            // solution
            onSale: true,
            // solution,
            newUser: {
                identifiant: "abc",
                mdp: "def",
                pseudo: "ghi"
              }
        }
    },
    methods: {
        createUser() {
            // URL de votre endpoint d'ajout d'utilisateur
            const apiUrl = "http://localhost:8080/api/users";
      
            // Options de la requête
            const requestOptions = {
              method: "POST",
              headers: {
                "Content-Type": "application/json"
                // Ajoutez d'autres en-têtes si nécessaire (par exemple, jeton d'authentification)
              },
              body: JSON.stringify(this.newUser)
            };
      
            // Utilisation de Fetch pour envoyer la requête POST
            fetch(apiUrl, requestOptions)
              .then(response => {
                if (!response.ok) {
                  throw new Error("Erreur lors de l'ajout de l'utilisateur à la base de données.");
                }
                return response.json();
              })
              .then(data => {
                console.log("Utilisateur ajouté avec succès :", data);
                // Réinitialiser les valeurs du nouvel utilisateur si nécessaire
                this.newUser = {
                  firstName: "",
                  lastName: "",
                  username: ""
                };
              })
              .catch(error => {
                console.error("Erreur Fetch :", error);
                console.error("Réponse serveur complète :", error.response.text());
              })
            },
    },
    computed: {
        title() {
            return this.brand + ' ' + this.product
        },
        image() {
            return this.variants[this.selectedVariant].image
        },
        inStock() {
            return this.variants[this.selectedVariant].quantity
        },
        // solution
        sale() {
            if (this.onSale) {
                return this.brand + ' ' + this.product + ' is on sale.'
            }
            return ''
        }
        // solution
    }
});

app.component('navigation', navigationComponent);
app.component('search', searchComponent);
app.component('onglets', ongletsComponent);
app.component('liste', liste);
app.component('onglets', ongletsComponent);
app.component('itemDepense', itemDepenseComponent);
app.component('resultat', resultatComponent);

const mountedApp = app.mount('#app');
