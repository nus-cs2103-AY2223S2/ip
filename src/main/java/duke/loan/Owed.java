package duke.loan;

/**
 * The Owed class represents an amount owed by the holder to the user.
 */
public class Owed extends Loan {
    public Owed(String name, int balance, int original, String description) {
        super(name, balance, original, description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateBalance(int amountInCents) {
        int change = balanceInCents + amountInCents;
        return updateBalanceWithChange(change <= 0, amountInCents);
    }

    @Override
    public String toString() {
        return String.format("Me << %s << %s [ %s ]",
                getBalanceInDollarsString(), name, description);
    }
}
