package expense;

/**
 * TransportExpense object that can be modified to be more descriptive about the expense.
 */
public class TransportExpense extends Expense {
    private String prefix;
    /**
     * Constructs a Transport Expense Object
     *
     * @param description Description for type of transport expense.
     * @param amountSpent Amount of money spent on expense.
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

