package classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected String newStartDate, newEndDate, newStartTime, newEndTime,
            oldStartDate, oldEndDate, oldStartTime, oldEndTime;

    protected LocalTime startTime, endTime;

    protected LocalDate startDate, endDate;

    public Event(String taskInfo, String stringStartDate, String stringEndDate, String stringStartTime
            , String stringEndTime) {
        super(taskInfo);
        this.oldStartDate = stringStartDate;
        this.oldEndDate = stringEndDate;
        this.oldStartTime = " " + stringStartTime;
        this.oldEndTime = " " + stringEndTime;
        this.startDate = LocalDate.parse(stringStartDate);
        this.newStartDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(startDate);
        this.endDate = LocalDate.parse(stringEndDate);
        this.newEndDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(endDate);

        if (stringStartTime.equals("")) {
            this.startTime = null;
            this.newStartTime = "";
            this.oldStartTime = "";
        } else {
            this.startTime = LocalTime.parse(stringStartTime);
            this.newStartTime = " | " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .format(startTime);
        }

        if (stringEndTime.equals("")) {
            this.endTime = null;
            this.newEndTime = "";
            this.oldEndTime = "";
        } else {
            this.endTime = LocalTime.parse(stringEndTime);
            this.newEndTime = " | " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .format(endTime);
        }
    }

    @Override
    public String getTaskInfoStatus() {
        return "[E]" + super.getTaskInfoStatus() + "(from: " + this.newStartDate + this.newStartTime + " to: "
                + this.newEndDate + this.newEndTime + ")";
    }

    @Override
    public String getTaskInfo() {
        return "[E]" + super.getTaskInfoStatus() + "/from " + this.oldStartDate + this.oldStartTime + " /to "
                + this.oldEndDate + this.oldEndTime;
    }
}
