package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date; // can do more with this
    public Deadline(String name, String inpString, LocalDate date){
        super(name, inpString);
        this.date = date;
    }

    public void updateDate(LocalDate newDate) {
        this.date = newDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


}
