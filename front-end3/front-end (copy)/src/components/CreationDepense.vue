<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Créer une dépense</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>
            <section class="modal-card-body">

                <Form :validation-schema="schema">
                    
                    
                </Form>
            </section>
            <footer class="modal-card-foot">
                <button type="submit" class="button is-success" @click="createExpense">Sauvegarder</button>
                <button class="button" @click="fermerModal">Annuler</button>
            </footer>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { Field, ErrorMessage, Form } from "vee-validate";
import * as yup from "yup";

export default {
    name: 'CreationDepense',
    components: {
        Form,
        Field,
        ErrorMessage,
    },
    props: {

    },
    data() {
        return {
            visible: false, // Initialiser la modal comme non visible
            // loggedInUserPseudo: '',
            successful: false,
            loading: false,
            message: '',
            // schema: yup.object().shape({
            //     title: yup.string()
            //         .required("Le titre est requis !")
            //         .min(5, "Le titre doit avoir au moins 5 caractères !")
            //         .max(50, "Le titre ne doit pas dépasser 50 caractères !"),
            // }),
            // groupData: {
            //     idOwner: '',
            //     title: '',
            // },
        };
    },
    created() {
        
    },
    methods: {
        ouvrirModal() {
            this.visible = true;
        },
        fermerModal() {
            this.visible = false;
        },

        // async createGroup() {
        //     console.log("dans createGroup");
        //     this.groupData.idOwner = this.loggedInUserPseudo;
        //     this.message = "";
        //     this.successful = false;
        //     this.loading = true;

        //     try {
        //         console.log("dans try");
        //         console.log("titre", this.groupData.title);
        //         // Utilisation d'Axios pour effectuer la requête POST
        //         const response = await axios.post("http://localhost:8080/groups/groups", this.groupData);
        //         // Traitement de la réponse
        //         this.message = response.data.message;
        //         this.successful = true;
        //         this.loading = false;
        //         this.fermerModal();
        //         this.$emit('groupCreated');
        //     } catch (error) {
        //         console.error("Erreur lors de la récupération des groupes :", error);
        //         console.error("Erreur détaillée :", error.response.data);
        //         console.log("catch :", error);
        //         // Gestion des erreurs
        //         this.message =
        //             (error.response &&
        //                 error.response.data &&
        //                 error.response.data.message) ||
        //             error.message ||
        //             error.toString();
        //         this.successful = false;
        //         this.loading = false;
        //         console.log(this.message);
        //     }
        // },
    },
}

</script>