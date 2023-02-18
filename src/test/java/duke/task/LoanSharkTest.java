package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.loan.LoanShark;

public class LoanSharkTest {
    @Test
    public void addOwe() {
        LoanShark loanShark = new LoanShark();
        loanShark.addLoan(-150, -150, "Owe 1", "Holder 1");
        loanShark.addLoan(-2100, -2100, "Owe 2", "Holder 1");
        String activeLoans = loanShark.getAccountActiveLoansString("Holder 1");
        String expected = "\nMe >> 1.50 >> Holder 1 [ Owe 1 ]\n"
                + "Me >> 21.0 >> Holder 1 [ Owe 2 ]";
        assertEquals(activeLoans, expected);
    }

    @Test
    public void addOwed() {
        LoanShark loanShark = new LoanShark();
        loanShark.addLoan(150, 150, "Owed 1", "Holder 1");
        loanShark.addLoan(2100, 2100, "Owed 2", "Holder 1");
        String actual = loanShark.getAccountActiveLoansString("Holder 1");
        String expected = "\nMe << 1.50 << Holder 1 [ Owed 1 ]\n"
                + "Me << 21.0 << Holder 1 [ Owed 2 ]";
        assertEquals(expected, actual);
    }

    @Test
    public void resolveOwe() {
        LoanShark loanShark = new LoanShark();
        loanShark.addLoan(-150, -150, "Owe 1", "Holder 1");
        loanShark.addLoan(-2100, -2100, "Owe 2", "Holder 1");
        loanShark.addLoan(150, 150, "Owed 1", "Holder 1");
        loanShark.addLoan(2100, 2100, "Owed 2", "Holder 1");
        String actual = loanShark.getAccountActiveLoansString("Holder 1");
        String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    public void resolveOwed() {
        LoanShark loanShark = new LoanShark();
        loanShark.addLoan(150, 150, "Owed 1", "Holder 1");
        loanShark.addLoan(2100, 2100, "Owed 2", "Holder 1");
        loanShark.addLoan(-150, -150, "Owe 1", "Holder 1");
        loanShark.addLoan(-2100, -2100, "Owe 2", "Holder 1");
        String actual = loanShark.getAccountActiveLoansString("Holder 1");
        String expected = "";
    }
}
