<template>
    <a class="panel-block" @click="goToGroupe">
        <span class="panel-icon">
            <i class="fas fa-book" aria-hidden="true"></i>
        </span>
        {{ groupe.title }}
    </a>
</template>
  
<script>
export default {
    props: ['groupe'],
    methods: {
        goToGroupe() {
            this.$router.push({ name: 'Groupe', params: { groupId: this.groupe.identifiant, title: this.groupe.title } });
        }
    },
};
</script>