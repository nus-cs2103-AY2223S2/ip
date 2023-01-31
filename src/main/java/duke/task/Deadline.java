package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String ddmmyyyy;
    protected String hhmm;
    protected LocalDateTime datetime;

    public Deadline(String desc, String ddmmyyyy, String hhmm) {
        super(desc);
        this.ddmmyyyy = ddmmyyyy;
        this.hhmm = hhmm;
        String[] date = ddmmyyyy.split("/");
        this.datetime = LocalDateTime.of(Integer.parseInt(date[2]),
                Integer.parseInt(date[1]), Integer.parseInt(date[0]),
                Integer.parseInt(hhmm.substring(0, 2)), Integer.parseInt(hhmm.substring(2)));
    }

    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s %s)", dateFormatter.format(datetime), timeFormatter.format(datetime));
    }

    public String getDetailsToSave() {
        return String.format("deadline %s %s %s\n%s", isDone, ddmmyyyy, hhmm, desc);
    }

}
