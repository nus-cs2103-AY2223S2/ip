package duke;

import duke.exceptions.DukeyException;

public class Loan extends Task {
    private String borrower;
    private String lender;
    private int amount;
    private static final String TYPE = "[L]";

    public Loan(String name, String borrower, String lender, int amount) {
        super(name, false);
        this.borrower = borrower;
        this.lender = lender;
        this.amount = amount;
    }

    public Loan(String name, String borrower, String lender, int amount, boolean isDone) {
        super(name, isDone);
        this.borrower = borrower;
        this.lender = lender;
        this.amount = amount;
    }

    public String getBorrower() {
        return this.borrower;
    }

    public String getLender() {
        return this.lender;
    }

    public int getAmount() {
        return this.amount;
    }

    public static Loan createLoan(Ui ui, String input) throws DukeyException {
        String[] inputArray = input.split("/");
        if (inputArray.length != 5) {
            throw new DukeyException("Error! Invalid Format!");
        }
        String loanName = ui.readTaskName(inputArray[1]);
        String borrower = ui.readName(inputArray[2]);
        String lender = ui.readName(inputArray[3]);
        int amount = ui.readAmount(inputArray[4]);

        return new Loan(loanName, borrower, lender, amount);
    }

    public static Loan createLoanFromLog(String[] logStringArray) {
        String name = logStringArray[2];
        boolean isMarked = !logStringArray[1].equals("0");
        String borrower = logStringArray[3];
        String lender = logStringArray[4];
        int amount = Integer.parseInt(logStringArray[5]);

        return new Loan(name, borrower, lender, amount, isMarked);

    }


    @Override
    public String getLogString() {
        return "L" + "," + getMarkedStatus() + "," + getName() + "," + getBorrower() + "," + getLender() + "," +
                getAmount();
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X]" + " " + this.getName() + " : " + this.getBorrower() + " owes " +
                    this.getAmount() + " to " + this.getLender();
        }

        return TYPE + "[ ]" + " " + this.getName() + " : " + this.getBorrower() + " owes $" +
                this.getAmount() + " to " + this.getLender();
    }
}
