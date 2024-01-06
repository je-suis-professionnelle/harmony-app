<template>
  <div class="tabs is-centered is-boxed is-medium">
    <ul>
      <li v-bind:class="{ 'is-active': isActive == 'expenses' }">
        <a v-on:click="isActive = 'expenses'">
          <span class="icon is-small"><i class="fas fa-image" aria-hidden="true"></i></span>
          <span>Dépenses</span>
        </a>
      </li>
      <li v-bind:class="{ 'is-active': isActive == 'balance' }">
        <a v-on:click="isActive = 'balance'">
          <span class="icon is-small"><i class="fas fa-music" aria-hidden="true"></i></span>
          <span>Balance</span>
        </a>
      </li>
      <li v-bind:class="{ 'is-active': isActive == 'equilibre' }">
        <a v-on:click="isActive = 'equilibre'">
          <span class="icon is-small"><i class="fas fa-film" aria-hidden="true"></i></span>
          <span>Equilibrer</span>
        </a>
      </li>
    </ul>
  </div>
  <div class="tab-contents">
    <div class="content" v-bind:class="{ 'is-active': isActive == 'expenses' }">
      <DepenseItem v-for="depense in this.expenses" :key="depense.identifiant" :depense="depense" />
    </div>
    <div class="content" v-bind:class="{ 'is-active': isActive == 'balance' }">
      <BalanceItem v-for="totalByMember in this.totalByMember"
        :member="totalByMember[0]"
        :totalForMember="totalByMember[1]"
        :division="this.division"
        :balance="calculateBalance(totalByMember[1])"/>
    </div>
    <div class="content" v-bind:class="{ 'is-active': isActive == 'equilibre' }">
      <EquilibreItem />
    </div>
  </div>
</template>

<script>

import DepenseItem from "../components/DepenseItem.vue";
import BalanceItem from "../components/BalanceItem.vue";
export default {

  components: {
    DepenseItem,
    BalanceItem
  },

  name: "Onglets",

  props: {
        expenses: {
      type: Array,
      required: true
    },
    division: {
      type: Number,
      required: true
    },
    myTotal: {
      type: Number,
      required: true
    },
    totalByMember: {
      type: Map,
      required: true
    },
    total: {
      type: Number,
      required: true
    },
  },

  data() {
    return {
      isActive: 'expenses',
      balanceItems: [],
      
    };
  },

  mounted() {
    console.log(this.expenses);
  },

  methods: {
    calculateBalance(totalForMember) {
      return totalForMember - this.division;
    },
  }
};

</script>