<template>
    <a class="panel-block">
        <div class="container">
            <div class="columns is-centered has-text-centered">
                <div class="column">
                    {{ this.member }}
                </div>
                <div class="column">
                    {{ this.debtForMember }}€
                </div>

                <div class="column">
                    {{ this.memberReceiving }}
                </div>
            </div>
        </div>
    </a>
</template>
  
<script>

export default {

    name: "EquilibreItem",

    props: {
        member: {
            type: String,
            required: true,
        },
        debtForMember: {
            type: Number,
            required: true,
        },
        memberReceiving: {
            type: String,
            required: true,
        }
    },
};

</script>