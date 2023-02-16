package duke.loan;

public abstract class Loan {
    protected static final String AMOUNT_NEGATIVE_ERROR_MSG = "Amount cannot be negative!";

    protected String name;
    protected int balanceInCents;
    protected int originalAmount;
    protected String description;

    public Loan(String name, int balanceInCents, int originalAmount, String description) {
        this.name = name;
        this.balanceInCents = balanceInCents;
        this.originalAmount = originalAmount;
        this.description = description;
    }

    public int getBalanceInCents() {
        return this.balanceInCents;
    }

    public boolean isResolved() {
        return balanceInCents == 0;
    }

    /**
     * @param amountInCents amount to update balance
     * @return change if amount exceeds required
     */
    public abstract int updateBalance(int amountInCents);

    protected void resolve() {
        this.balanceInCents = 0;
    }

    protected String getBalanceInDollarsString() {
        if (isResolved()) {
            return "RESOLVED!";
        }
        int absBalanceInCents = Math.abs(balanceInCents);
        return String.format("%s.%s",
                absBalanceInCents / 100, absBalanceInCents % 100);
    }

    protected int updateBalanceWithChange(boolean shouldReturnChange, int amountInCents) {
        int change = balanceInCents + amountInCents;
        if (shouldReturnChange) {
            resolve();
            return change;
        } else {
            balanceInCents += amountInCents;
            return 0;
        }
    }

    public String serialize() {
        return String.join("|", name, "" + balanceInCents, "" + originalAmount, description);
    }
}
