package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {

    protected String ddmmyyyyFrom;
    protected String hhmmFrom;
    protected LocalDateTime datetimeFrom;

    protected String ddmmyyyyTo;
    protected String hhmmTo;
    protected LocalDateTime datetimeTo;

    public Event(String desc, String ddmmyyyyFrom, String hhmmFrom, String ddmmyyyyTo, String hhmmTo) {
        super(desc);
        this.ddmmyyyyFrom = ddmmyyyyFrom;
        this.hhmmFrom = hhmmFrom;
        this.ddmmyyyyTo = ddmmyyyyTo;
        this.hhmmTo = hhmmTo;
        String[] dateFrom = ddmmyyyyFrom.split("/");
        String[] dateTo = ddmmyyyyTo.split("/");
        this.datetimeFrom = LocalDateTime.of(Integer.parseInt(dateFrom[2]),
                Integer.parseInt(dateFrom[1]), Integer.parseInt(dateFrom[0]),
                Integer.parseInt(hhmmFrom.substring(0, 2)), Integer.parseInt(hhmmFrom.substring(2)));
        this.datetimeTo = LocalDateTime.of(Integer.parseInt(dateTo[2]),
                Integer.parseInt(dateTo[1]), Integer.parseInt(dateTo[0]),
                Integer.parseInt(hhmmTo.substring(0, 2)), Integer.parseInt(hhmmTo.substring(2)));
    }

    public String toString() {
        return "[D]" + super.toString() + String.format(" (from: %s %s to: %s %s)",
                dateFormatter.format(datetimeFrom), timeFormatter.format(datetimeFrom),
                dateFormatter.format(datetimeTo), timeFormatter.format(datetimeTo));
    }

    public String getDetailsToSave() {
        return String.format("event %s %s %s %s %s\n%s", isDone, ddmmyyyyFrom, hhmmFrom, ddmmyyyyTo, hhmmTo, desc);
    }

}
