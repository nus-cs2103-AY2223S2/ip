package expense;

/**
 * TransportExpense object that can be modified to be more descriptive about the expense.
 */
public class TransportExpense extends Expense {
    private String prefix;
    /**
     * Constructor for a Transport Expense Object
     * @param description
     * @param amountSpent
     */
    public TransportExpense(String description, double amountSpent) {
        super(description, amountSpent);
        this.prefix = "Transport";
    }
    public String getCommand() {
        return "transport " + super.getDescription() + " /" + super.getAmountSpent();
    }
    @Override
    public String toString() {
        return this.prefix + ": " + super.toString();
    }
}

