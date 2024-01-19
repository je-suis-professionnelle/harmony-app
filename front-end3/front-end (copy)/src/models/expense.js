class Expense {
    constructor(data) {
        this.pseudo = data.pseudo;
        this.idGroup = Number(data.idGroup);
        this.timestamp = data.timestamp;
        this.amount = data.amount;
        this.label = data.label;
        this.description = data.description;
        this.imageBlob = data.imageBlob;
    }
}

export default Expense;