<template>
  <div class="tabs is-centered is-boxed is-medium">
    <ul>
      <li v-bind:class="{ 'is-active': isActive == 'expenses' }">
        <a v-on:click="isActive = 'expenses'">
          <span class="icon is-small">
            <i class="fas fa-sack-dollar"></i>
          </span>
          <span>Dépenses</span>
        </a>
      </li>
      <li v-bind:class="{ 'is-active': isActive == 'balance' }">
        <a v-on:click="isActive = 'balance'">
          <span class="icon is-small"><i class="fas fa-balance-scale-right"></i></span>
          <span>Balance</span>
        </a>
      </li>
      <li v-bind:class="{ 'is-active': isActive == 'equilibre' }">
        <a v-on:click="isActive = 'equilibre'">
          <span class="icon is-small"><i class="fas fa-file-invoice-dollar"></i></span>
          <span>Equilibrer</span>
        </a>
      </li>
    </ul>
  </div>

  <div class="tab-contents">

    <div class="content" v-bind:class="{ 'is-active': isActive == 'expenses' }">
      <DepenseItem v-if="this.expenses.length!=0" v-for="depense in this.expenses" :key="depense.identifiant" :depense="depense" @expenseDeletedD="expenseDeletedHandler" @itemClicked="displayExpenseImage"/>
      <div v-else class="box has-text-centered">
        <p>Vous n'avez pas de dépenses pour le moment !</p>
      </div>
    </div>

    <div class="content" v-bind:class="{ 'is-active': isActive == 'balance' }">
      <BalanceItem v-for="totalByMember in this.totalByMember" 
        :member="totalByMember[0]"
        :totalForMember="totalByMember[1]" 
        :division="Number(this.division)" 
        :balance="calculateBalance(totalByMember[1])" 
      />
    </div>

    <div class="content" v-bind:class="{ 'is-active': isActive == 'equilibre' }">
      <EquilibreItem v-if="this.equilibres.length!=0" v-for="debtByMember in this.equilibres" 
      :member="debtByMember.from" 
      :debtForMember="debtByMember.debt"
      :memberReceiving="debtByMember.to"/>
      <div v-else class="box has-text-centered">
        <p>Vous êtes à équilibre !</p>
      </div>
    </div>

  </div>

  <AffichageImage ref="affichageImageModal"/>

</template>

<script>
import DepenseItem from "../components/DepenseItem.vue";
import BalanceItem from "../components/BalanceItem.vue";
import EquilibreItem from "../components/EquilibreItem.vue";
import SuppressionDepense from "../components/SuppressionDepense.vue";
import AffichageImage from "../components/AffichageImage.vue";

export default {

  components: {
    SuppressionDepense,
    DepenseItem,
    BalanceItem,
    EquilibreItem,
    AffichageImage
  },

  name: "Onglets",
  emits: ['expenseDeletedO'],

  props: {
    expenses: {
      type: Array,
      required: true
    },
    division: {
      type: Number,
      required: true
    },
    totalByMember: {
      type: Map,
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
      return totalForMember - this.division;
    },
    expenseDeletedHandler() {
      this.$emit('expenseDeletedO');
    },
    displayExpenseImage(depense) {
      this.$refs.affichageImageModal.ouvrirModal(depense);
    }
  }
};

</script>