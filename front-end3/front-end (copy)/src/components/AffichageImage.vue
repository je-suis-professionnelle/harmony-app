<template>
    <div v-if="visible" class="modal is-active" id="myModal" >
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Ticket de la dépense</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>
            <div class="modal-card-body">
                <img v-if="depense.imageBlob" :src="'data:image/jpeg;base64,' + depense.imageBlob"
                    alt="Image de la dépense" />
                <p v-else>Aucun ticket pour cette dépense</p>
            </div>
            <footer class="modal-card-foot">
                <button class="button" @click="fermerModal">Fermer</button>
            </footer>
        </div>
    </div>
</template>

<script>
export default {
    name: 'AffichageImage',
    props: {
    },
    data() {
        return {
            visible: false,
            depense: null,
        };
    },
    methods: {
        ouvrirModal(depense) {
            this.visible = true;
            this.depense = depense;
        },
        fermerModal() {
            this.visible = false;
        },
    },
}

</script>