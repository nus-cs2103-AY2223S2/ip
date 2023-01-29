import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected String stringDate, stringTime;
    protected LocalDate date;

    protected LocalTime time;

    public Deadline(String taskInfo, String stringDate, String stringTime) {
        super(taskInfo);
        this.date = LocalDate.parse(stringDate);
        this.stringDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(date);

        if (stringTime.equals("")) {
            this.time = null;
            this.stringTime = "";
        } else {
            this.time = LocalTime.parse(stringTime);
            this.stringTime = " " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .format(time);
        }
    }

    @Override
    public String getTaskInfo() {
        return "[D]" + super.getTaskInfo() + "(by: " + stringDate + stringTime + ")";
    }
}
