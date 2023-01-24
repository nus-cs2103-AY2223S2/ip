package tasktypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    LocalDate byDate;
    LocalTime byTime;
    LocalDateTime byDateTime;
    String forSaving;
    String doneBy;


    public Deadline(String description, String by) {
        super(description);
        String[] dateAndTime = by.split(" ");
        String date = dateAndTime[0];
        String time = dateAndTime[1];
        date = date.replace('/','-');
        this.forSaving = date + " " + time;
        time = time.substring(0, 2) + ':' + time.substring(2);

        this.byDate = LocalDate.parse(date);
        this.byTime = LocalTime.parse(time);
        this.byDateTime = LocalDateTime.of(this.byDate, this.byTime);
        this.doneBy = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT).
                format(this.byDateTime);

        Task.numTask++;
    }

    @Override
    public String getSaveFormat() {
        String done;
        if (this.done) {
            done = "1";
        } else {
            done = "0";
        }
        return "D" + ",," + done + ",," + this.description + ",," + this.forSaving;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.doneBy + ")";
    }
}


