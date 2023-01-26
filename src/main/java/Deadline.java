package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate day;

    public Deadline(String type, String detail, boolean marked, LocalDate day){
        super(type, detail, marked);
        this.day = day;
    }

    public Deadline(String type, String detail, String day){
        super(type, detail);
        this.day = LocalDate.parse(day);
    }

    /**
     * Returns deadline printed out properly.
     *
     * @return deadline in full details.
     */
    @Override
    public String toString(){
        if (marked) {
            return "[D][X] " + super.detail + " (by: " + this.day.format(DateTimeFormatter.
                    ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D][ ] " + super.detail + " (by: " + this.day.format(DateTimeFormatter.
                    ofPattern("MMM d yyyy")) + ")";
        }
    }
}