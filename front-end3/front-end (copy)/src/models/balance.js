class Balance {
    constructor(data) {
        this.pseudo = data.pseudo;
        this.id_group = Number(data.id_group);
        this.amount = data.amount;
    }
}

export default Balance;