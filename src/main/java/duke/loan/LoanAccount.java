package duke.loan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class LoanAccount {
    private final String holder;
    private final Queue<Loan> activeLoans = new LinkedList<Loan>();
    private final ArrayList<Loan> record = new ArrayList<Loan>();

    public LoanAccount(String holder) {
        this.holder = holder;
    }

    public String getHolder() {
        return holder;
    }

    public int getBalanceInCents() {
        return activeLoans.stream()
                .map(Loan::getBalanceInCents)
                .reduce(0, Integer::sum);
    }

    public String getBalanceInDollarsString() {
        int balanceInCents = activeLoans.stream()
                .map(Loan::getBalanceInCents)
                .reduce(0, Integer::sum);
        int absBalanceInCents = Math.abs(balanceInCents);
        return String.format("%s.%s",
                absBalanceInCents / 100, absBalanceInCents % 100);
    }

    public Owe addOwe(int balance, int amountInCents, String description) {
        assert amountInCents < 0 : "Owe amount must be negative!";
        int oweBalance = attemptToResolveLoans(balance);
        Owe newOwe = new Owe(holder, oweBalance, amountInCents, description);
        if (!newOwe.isResolved()) {
            activeLoans.add(newOwe);
        }
        record.add(newOwe);
        return newOwe;
    }

    public Owed addOwed(int balance, int amountInCents, String description) {
        assert amountInCents > 0 : "Owed amount must be positive!";
        int owedBalance = attemptToResolveLoans(balance);
        Owed newOwed = new Owed(holder, owedBalance, amountInCents, description);
        if (!newOwed.isResolved()) {
            activeLoans.add(newOwed);
        }
        record.add(newOwed);
        return newOwed;
    }

    private int attemptToResolveLoans(int amountUsedToResolve) {
        boolean isBalancePositive = getBalanceInCents() > 0;

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
        loans.forEach(l -> collectionStr.append("\n").append(l));
        return collectionStr.toString();
    }

    public String serializeActiveLoans() {
        return serializeLoanCollection(activeLoans);
    }

    public String serializeRecord() {
        return serializeLoanCollection(record);
    }

    private String serializeLoanCollection(Collection<Loan> loanCollection) {
        StringBuilder serialized = new StringBuilder();
        loanCollection.forEach(l -> serialized.append("\n").append(l.serialize()));
        return serialized.toString();
    }
}
