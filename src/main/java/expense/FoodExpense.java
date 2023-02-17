package expense;

/**
 * FoodExpense Object to be modified to track more things in the future.
 */
public class FoodExpense extends Expense {
    private String prefix;

    /**
     * Constructs a FoodExpense object.
     *
     * @param description Description to describe the type of food bought.
     * @param amountSpent Amount of money spent on expense.
     */
    public FoodExpense(String description, double amountSpent) {
        super(description, amountSpent);
        this.prefix = "Food";
    }

    public String getCommand() {
        return "food " + super.getDescription() + " /" + super.getAmountSpent();
    }
    @Override
    public String toString() {
        return prefix + ": " + super.toString();
    }
}
