package classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected String newDate, newTime, oldDate, oldTime;
    protected LocalDate date;

    protected LocalTime time;

    public Deadline(String taskInfo, String stringDate, String stringTime) {
        super(taskInfo);
        this.oldDate = stringDate;
        this.oldTime = stringTime;
        this.date = LocalDate.parse(stringDate);
        this.newDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(date);

        if (stringTime.equals("")) {
            this.time = null;
            this.newTime = "";
        } else {
            this.time = LocalTime.parse(stringTime);
            this.newTime = " | " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .format(time);
        }
    }

    @Override
    public String getTaskInfoStatus() {
        return "[D]" + super.getTaskInfoStatus() + "(by: " + this.newDate + this.newTime + ")";
    }

    @Override
    public String getTaskInfo() {
        return "[D]" + super.getTaskInfoStatus() + "/by " + this.oldDate + " " + this.oldTime;
    }
}
