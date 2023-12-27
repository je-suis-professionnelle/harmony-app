<template>
    <a class="panel-block is-active">
        <span class="panel-icon">
            <i class="fas fa-book" aria-hidden="true"></i>
        </span>
        bulma
    </a>
</template>
  
<script>
export default {
    props: {
        // Définir une propriété "message" qui sera passée depuis le composant parent
        nom: {
            type: String,
            required: true,
        },
    },
    data() {
        return {};
    },
    methods: {}
};
</script>