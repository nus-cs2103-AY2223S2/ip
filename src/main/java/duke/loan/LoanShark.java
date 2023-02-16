package duke.loan;

import duke.exception.DukeException;

import java.util.HashMap;

public class LoanShark {
    private final HashMap<String, LoanAccount> loanAccounts;

    public LoanShark() {
        loanAccounts = new HashMap<>();
    }

    public Loan addLoan(int balance, int amountInCents, String description, String holder) {
        LoanAccount loanAccount;

        if (!loanAccounts.containsKey(holder)) {
            loanAccount = new LoanAccount(holder);
            loanAccounts.put(holder, loanAccount);
        } else {
            loanAccount = loanAccounts.get(holder);
        }

        if (amountInCents > 0) {
            return loanAccount.addOwed(balance, amountInCents, description);
        } else {
            return loanAccount.addOwe(balance, amountInCents, description);
        }
    }

    public void printAccountRecords(String accountHolder) {
        System.out.println(loanAccounts.get(accountHolder).getRecordString());
    }

    public String getAccountLoanRecordString(String accountHolder) {
        if (!loanAccounts.containsKey(accountHolder)) {
            return getMissingLoanAccountString(accountHolder);
        }
        return loanAccounts.get(accountHolder).getRecordString();
    }

    public String getAccountActiveLoansString(String accountHolder) {
        if (!loanAccounts.containsKey(accountHolder)) {
            return getMissingLoanAccountString(accountHolder);
        }
        return loanAccounts.get(accountHolder).getActiveLoansString();
    }

    private static String getMissingLoanAccountString(String accountHolder) {
        return String.format("No loan account belongs to %s!", accountHolder);
    }

    public String getLoanSummaryString() {
        StringBuilder sb = new StringBuilder();
        if (loanAccounts.isEmpty()) {
            return "No loans found!";
        }
        loanAccounts.forEach((holder, loanAccount) -> {
                sb.append(String.format("%s: ", holder));
                int balance = loanAccount.getBalanceInCents();
                if (balance == 0) {
                    sb.append("ALL OK!\n\n");
                } else {
                    sb.append("Balance: $")
                            .append(loanAccount.getBalanceInDollarsString())
                            .append("\n")
                            .append(loanAccount.getActiveLoansString())
                            .append("\n");
                }
            }
        );
        return sb.toString();
    }

    public void saveLoans() throws DukeException {
        LoanSharkStorage.saveAccounts(loanAccounts.values());
    }

    public void load() throws DukeException {
        LoanSharkStorage.loadAccounts(this);
    }
}
