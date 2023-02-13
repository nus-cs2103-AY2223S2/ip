package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;
    public Event(String name, String inpString, LocalDate startDate, LocalDate endDate){
        super(name, inpString);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void updateDate(LocalDate newStartDate, LocalDate newEndDate) {
        this.startDate = newStartDate;
        this.endDate = newEndDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }


}
