<template>
    <a class="panel-block">
        <div class="container">
            <div class="columns is-centered has-text-centered">
                <div class="column">
                    {{ member }}
                </div>
                <div class="column">
                    {{ this.balance }}€
                </div>

                <div class="column">
                    <!-- {{ depense.description }} -->
                </div>
                <div class="column">
                    <!-- {{ depense.amount }} -->
                </div>

            </div>
        </div>
    </a>
</template>
  
<script>

export default {

    name: "BalanceItem",

    props: {
        member: {
            type: String,
            required: true,
        },
        totalForMember: {
            type: Number,
            required: true,
        },
        division: {
            type: Number,
            required: true,
        },
        balance: {
            type: Number,
            default: 0,
        }
    },
};

</script>