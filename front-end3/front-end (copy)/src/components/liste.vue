<template>
  <div class="table-container">
    <table class="table is-fullwidth is-centered">
      <thead>
        <tr>
          <th>Prénom</th>
          <th>Label</th>
          <th>Prix</th>
        </tr>
      </thead>
      <tbody>
        <item-depense></item-depense>
        <item-depense></item-depense>
        <item-depense></item-depense>
        <item-depense></item-depense>
      </tbody>
    </table>
  </div>
</template>

<script>
import ItemDepense from './itemDepense.vue'; // Assuming ItemDepense is in the same directory

export default {
  components: {
    'item-depense': ItemDepense,
  },
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
/* Add your scoped styles here */
</style>
