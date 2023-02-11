package duke.loan;

public class Owed extends Loan {
    public Owed(String name, int amountLentInCents, String description) {
        super(name, amountLentInCents, description);
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
        return String.format("Me << %s << %s %s %s",
                getBalanceInDollarsString(), name, getResolvedIndicator(), description);
    }
}
