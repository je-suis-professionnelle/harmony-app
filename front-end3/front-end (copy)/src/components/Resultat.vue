<template>
  <div class="tabs is-toggle is-fullwidth is-medium">
    <ul>
      <li class="">
        <a>
          <span class="icon"><i class="fas fa-image" aria-hidden="true"></i></span>
          <div class="columns">
            <div class="column">
              <span>Vos dépenses</span>
            </div>
            <div class="column">
                <span>{{ myTotal }}€</span>
            </div>
          </div>
        </a>
      </li>
      <li>
        <a>
          <span class="icon"><i class="fas fa-music" aria-hidden="true"></i></span>
          <div class="columns">
            <div class="column">
              <span>Montant visé chacun</span>
            </div>
            <div class="column">
              <span>{{ division }}€</span>
            </div>
          </div>
        </a>
      </li>
      <li>
        <a>
          <span class="icon"><i class="fas fa-film" aria-hidden="true"></i></span>
          <div class="columns is-multiline">
            <div class="column">
              <span>TOTAL</span>
            </div>
            <div class="column">
              <span>{{ total }}€</span>
            </div>
          </div>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Resultat",
  props: {
    total: {
      type: Number,
      required: true,
    },
    division: {
      type: Number,
      required: true,
    },
    myTotal: {
      type: Number,
      required: true,
    },
  },
};
</script>
