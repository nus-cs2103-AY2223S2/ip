package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

        private LocalDate bydate;
        private LocalTime bytime;

        public Deadline(String description, String bydate, String bytime) {
            super(description);
            this.bydate = LocalDate.parse(bydate);
            this.bytime = LocalTime.parse(bytime, DateTimeFormatter.ofPattern("HHmm"));
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " +
                    this.bydate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                    this.bytime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
        }
}

