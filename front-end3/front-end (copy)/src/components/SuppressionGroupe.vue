<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Supprimer le groupe</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>
            <footer class="modal-card-foot">
                <button type="submit" class="button is-success" @click="deleteGroup">Supprimer</button>
                <button class="button" @click="fermerModal">Annuler</button>
            </footer>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: 'SuppressionGroupe',
    props: {
        groupId: {
            type: Number,
            required: true,
        },
    },
    data() {
        return {
            visible: false,
        };
    },
    methods: {
        ouvrirModal() {
            this.visible = true;
        },
        fermerModal() {
            this.visible = false;
        },

        deleteGroup() {

            let config = {
                headers: { 'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken },
                params: {
                    id: this.groupId
                },
            }

            axios.delete('http://localhost:8080/groups/group', config
            )
                .then(response => {
                    this.fermerModal();
                    this.$router.push({ name: 'Groupes' });
                })
                .catch(error => {
                    console.error("Erreur lors de la suppression :", error.response.data.message);
                    console.error("Erreur lors de la suppression :", error.response.data);
                    console.error("Erreur lors de la suppression :", error.response);
                });
        }
    },
}

</script>