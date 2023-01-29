import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected String stringStartDate, stringEndDate, stringStartTime, stringEndTime;

    protected LocalTime startTime, endTime;

    protected LocalDate startDate, endDate;

    public Event(String taskInfo, String stringStartDate, String stringEndDate, String stringStartTime
            , String stringEndTime) {
        super(taskInfo);
        this.startDate = LocalDate.parse(stringStartDate);
        this.stringStartDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(startDate);
        this.endDate = LocalDate.parse(stringEndDate);
        this.stringEndDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(endDate);

        if (stringStartTime.equals("")) {
            this.startTime = null;
            this.stringStartTime = "";
        } else {
            this.startTime = LocalTime.parse(stringStartTime);
            this.stringStartTime = " " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .format(startTime);
        }

        if (stringEndTime.equals("")) {
            this.endTime = null;
            this.stringEndTime = "";
        } else {
            this.endTime = LocalTime.parse(stringEndTime);
            this.stringEndTime = " " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .format(endTime);
        }
    }

    @Override
    public String getTaskInfo() {
        return "[E]" + super.getTaskInfo() + "(from: " + stringStartDate + stringStartTime + " to: " + stringEndDate
                + stringEndTime + ")";
    }
}
