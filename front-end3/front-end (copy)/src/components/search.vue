<template>
  <div class="field has-addons">
    <div>
      <h2 class="title is-2">{{ titre }}</h2>
    </div>
    <div class="control">
      <input class="input" type="text" placeholder="Find a repository" />
    </div>
    <div class="control">
      <a class="button is-info">
        Search
      </a>
    </div>
    <a class="button">
      <span class="material-symbols-outlined">
        tune
      </span>
    </a>
    <a class="button">
      <span class="material-symbols-outlined">
        sort
      </span>
    </a>
  </div>
</template>

<script>
export default {
  data() {
    return {
      titre: "",
    };
  },
  methods: {},
};
</script>

<style scoped>
/* Add your scoped styles here */
</style>
