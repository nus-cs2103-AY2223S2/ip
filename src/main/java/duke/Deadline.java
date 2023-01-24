package duke;

import java.time.LocalDate;
public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String instruction, String date) {
        super(instruction.substring(9));
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.getMonth() + " "
                + this.date.getDayOfMonth() + ", " + this.date.getYear() + ")";
    }
}