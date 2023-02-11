package duke.loan;

import java.util.HashMap;

public class LoanShark {
    private final HashMap<String, LoanAccount> loanAccounts;

    public LoanShark() {
        loanAccounts = new HashMap<>();
    }

    public void addLoan(int amountInCents, String description, String holder) {
        LoanAccount loanAccount;

        if (!loanAccounts.containsKey(holder)) {
            loanAccount = new LoanAccount(holder);
            loanAccounts.put(holder, loanAccount);
        } else {
            loanAccount = loanAccounts.get(holder);
        }

        if (amountInCents > 0) {
            loanAccount.addNewOwed(amountInCents, description);
        } else if (amountInCents < 0) {
            loanAccount.addNewOwe(amountInCents, description);
        }
    }

    public void printAccountRecords(String accountHolder) {
        System.out.println(loanAccounts.get(accountHolder).getRecordString());
    }

    public String getAccountActiveLoansString(String accountHolder) {
        return loanAccounts.get(accountHolder).getRecordString();
    }
}
