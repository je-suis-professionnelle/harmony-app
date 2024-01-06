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
      <DepenseItem v-for="depense in this.expenses" :key="depense.identifiant"
        @click="ouvrirSuppressionDepenseModal(depense)" :depense="depense" />
    </div>

    <div class="content" v-bind:class="{ 'is-active': isActive == 'balance' }">
      <BalanceItem v-for="totalByMember in this.totalByMember" 
        :member="totalByMember[0]"
        :totalForMember="totalByMember[1]" 
        :division="this.division" 
        :balance="calculateBalance(totalByMember[1])" 
      />
    </div>

    <div class="content" v-bind:class="{ 'is-active': isActive == 'equilibre' }">
      <EquilibreItem v-for="debtByMember in this.equilibres" 
      :member="debtByMember.from" 
      :debtForMember="debtByMember.debt"
      :memberReceiving="debtByMember.to"/>
    </div>

  </div>

  <SuppressionDepense ref="suppressionDepenseModal" @expenseDeleted="expenseDeletedHandler" :depense="clickedExpense" />
</template>

<script>
import SuppressionDepense from "../components/SuppressionDepense.vue";
import DepenseItem from "../components/DepenseItem.vue";
import BalanceItem from "../components/BalanceItem.vue";
import EquilibreItem from "../components/EquilibreItem.vue";

export default {

  components: {
    SuppressionDepense,
    DepenseItem,
    BalanceItem,
    EquilibreItem
  },

  name: "Onglets",

  emits: ['expenseDeletedP'],

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
    equilibres: {
      type: Array,
      required: true
    }
  },

  data() {
    return {
      isActive: 'expenses',
      balanceItems: [],
      clickedExpense: null,
    };
  },

  methods: {
    calculateBalance(totalForMember) {
      console.log("equilibrage", this.equilibres);
      console.log("totalByMember", this.totalByMember);
      return totalForMember - this.division;
    },
    ouvrirSuppressionDepenseModal(depense) {
      this.clickedExpense = depense;
      this.$refs.suppressionDepenseModal.ouvrirModal();
    },
    expenseDeletedHandler() {
      this.$emit('expenseDeletedP');
      console.log("expenseDeletedHandler");
    }
  }
};

</script>