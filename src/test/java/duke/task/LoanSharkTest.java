package duke.task;

import org.junit.jupiter.api.Test;

import duke.loan.LoanShark;

public class LoanSharkTest {
    @Test
    public void testAddDebt() {
        LoanShark loanShark = new LoanShark();
        loanShark.addLoan(50, "Grab Food", "JIM");
        loanShark.addLoan(150, "Macdonalds", "JIM");
        loanShark.addLoan(-150, "Mala", "JIM");
        loanShark.addLoan(-2100, "Netflix", "JIM");
        loanShark.printAccountRecords("JIM");
    }
}
