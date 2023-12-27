class Expense {
    constructor(pseudo, idGroup, timestamp, amount, label, description) {
        this.pseudo = pseudo;
        this.idGroup = idGroup;
        this.timestamp = timestamp;
        this.amount = amount;
        this.label = label;
        this.description = description;
    }
}

export default Expense;