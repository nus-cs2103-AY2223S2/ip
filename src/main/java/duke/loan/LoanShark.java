package duke.loan;

import java.util.HashMap;

public class LoanShark {
    private final HashMap<String, LoanAccount> loanAccounts;

    public LoanShark() {
        loanAccounts = new HashMap<>();
    }

    public Loan addLoan(int amountInCents, String description, String holder) {
        LoanAccount loanAccount;

        if (!loanAccounts.containsKey(holder)) {
            loanAccount = new LoanAccount(holder);
            loanAccounts.put(holder, loanAccount);
        } else {
            loanAccount = loanAccounts.get(holder);
        }

        if (amountInCents > 0) {
            return loanAccount.addNewOwed(amountInCents, description);
        } else {
            return loanAccount.addNewOwe(amountInCents, description);
        }
    }

    public void printAccountRecords(String accountHolder) {
        System.out.println(loanAccounts.get(accountHolder).getRecordString());
    }

    public String getAccountLoanRecordString(String accountHolder) {
        if (!loanAccounts.containsKey(accountHolder)) {
            return String.format("No loan account belongs to %s!", accountHolder);
        }
        return loanAccounts.get(accountHolder).getRecordString();
    }

    public String getAccountActiveLoansString(String accountHolder) {
        if (!loanAccounts.containsKey(accountHolder)) {
            return String.format("No loan account belongs to %s!", accountHolder);
        }
        return loanAccounts.get(accountHolder).getActiveLoansString();
    }
}
