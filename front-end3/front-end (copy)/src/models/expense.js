class Expense {
    constructor(data) {
        this.pseudo = data.pseudo;
        this.id_group = Number(data.id_group);
        this.timestamp = data.timestamp;
        this.amount = data.amount;
        this.label = data.label;
        this.description = data.description;
    }
}

export default Expense;