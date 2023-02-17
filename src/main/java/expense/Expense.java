package expense;

/**
 * Class to encapsulate expenses.
 */
public class Expense {
    private String description;
    private double amountSpent;
    /**
     * Constructs a new Expense object.
     *
     * @param description description of expense object
     * @param amountSpent amount of money spent on the particular expense.
     */
    public Expense(String description, double amountSpent) {
        this.description = description;
        this.amountSpent = amountSpent;
    }

    /**
     * Gets amountSpent.
     *
     * @return amount of money spent on this expense.
     */
    public double getAmountSpent() {
        return this.amountSpent;
    }

    public String getCommand() {
        return this.description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return description + " $" + amountSpent;
    }
}
