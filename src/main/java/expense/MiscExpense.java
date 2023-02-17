package expense;

/**
 * Class of expenses that do not neatly fall into Food or Transport, such as purchases of goods for oneself.
 */
public class MiscExpense extends Expense {
    private String prefix;
    /**
     * Constructs a MiscExpense object.
     *
     * @param description Description to describe the type of expense.
     * @param amount Amount of money spent on expense.
     */
    public MiscExpense(String description, double amount) {
        super(description, amount);
        this.prefix = "Misc";
    }
    public String getCommand() {
        return "misc " + super.getDescription() + " /" + super.getAmountSpent();
    }
    @Override
    public String toString() {
        return this.prefix + ": " + super.toString();
    }
}
