package duke.loan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class LoanAccount {
    private String holder;
    private final Queue<Loan> activeLoans = new LinkedList<Loan>();
    private final ArrayList<Loan> record = new ArrayList<Loan>();

    public LoanAccount(String holder) {
        this.holder = holder;
    }

    public int getBalanceInCents() {
        return activeLoans.stream()
                .map(Loan::getBalanceInCents)
                .reduce(0, Integer::sum);
    }

    public void addNewOwe(int amountInCents, String description) {
        assert amountInCents < 0 : "Owe amount must be negative!";
        int oweBalance = attemptToResolveLoans(amountInCents);
        Owe newOwe = new Owe(holder, oweBalance, description);
        if (!newOwe.isResolved()) {
            activeLoans.add(newOwe);
        }
        record.add(newOwe);
    }

    public void addNewOwed(int amountInCents, String description) {
        assert amountInCents > 0 : "Owed amount must be positive!";
        int owedBalance = attemptToResolveLoans(amountInCents);
        Owed newOwed = new Owed(holder, owedBalance, description);
        if (!newOwed.isResolved()) {
            activeLoans.add(newOwed);
        }
        record.add(newOwed);
    }

    private int attemptToResolveLoans(int amountUsedToResolve) {
        boolean isBalancePositive = getBalanceInCents() > 0;
        // Attempt to resolve active loans.
        while (activeLoans.size() > 0
                && (isBalancePositive && amountUsedToResolve < 0 || !isBalancePositive && amountUsedToResolve > 0)) {
            Loan loan = activeLoans.peek();
            amountUsedToResolve = loan.updateBalance(amountUsedToResolve);
            if (loan.isResolved()) {
                activeLoans.poll();
            }
        }
        return amountUsedToResolve;
    }

    public String getRecordString() {
        return getAccountCollectionString(record);
    }

    public String getActiveLoansString() {
        return getAccountCollectionString(activeLoans);
    }

    private String getAccountCollectionString(Collection<Loan> loans) {
        StringBuilder collectionStr = new StringBuilder();
        loans.forEach(l -> collectionStr.append(l).append("\n"));
        return collectionStr.toString();
    }
}
