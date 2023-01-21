package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class also extends task. A deadline is a category of task in
 * duke that has a deadline(date).
 */
public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String name, boolean done, String date) {
        super(name, done);
        this.date = LocalDate.parse(date);
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String summary(){
        String s = "D___";
        String d = this.getDone()? "✓" : " X";
        return s + d + "___" + this.getTask() + "___" + this.getDate();
    }

    @Override
    public String toString(){
        String dateFormatted = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String s = "[ D ]" + super.toString() + String.format("(%s)", dateFormatted);
        return s;
    }
}
