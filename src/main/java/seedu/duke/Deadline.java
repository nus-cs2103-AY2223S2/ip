package seedu.duke;

import java.time.LocalDate;

public class Deadline extends Task{
    protected String type = "[ D ]";
    protected LocalDate date;
    public Deadline(String description, String a) {
        super(description);
        System.out.println(date);
        this.date = LocalDate.parse(a);
    }

    @Override
    public String toString() {
        return type + super.toString() + " by: " + date + " ";
    }
}
