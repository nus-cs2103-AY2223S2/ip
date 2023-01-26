import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Deadline extends Task {

    protected String by;
    protected String byTimeStr = "";
    protected LocalDate byDate;
    protected LocalTime byTime = LocalTime.now();
    public static String dateTimeParseErrMsg = "Could not parse date time format, please write yyyy-MM-dd [HHmm]. " +
            "Include 0s in the tenths place. Optionally include time, separated by a space";

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        ArrayList<String> arr = new ArrayList<>(List.of(by.split(" ")));

        try {
            this.byDate = LocalDate.parse(arr.get(0));
            this.by = byDate.format(DateTimeFormatter.ofPattern("EEEE MMM d yyyy"));

            if (arr.size() > 1) {
                this.byTime = LocalTime.parse(arr.get(1), DateTimeFormatter.ofPattern("HHmm"));
                byTimeStr = byTime.format(DateTimeFormatter.ofPattern("HHmm"));
            }
//            System.out.println("Excellent sir, I've added the task: ");
//            System.out.println(this.toString());
        } catch (DateTimeParseException d) {
            System.out.println(dateTimeParseErrMsg);
        }
    }

    public Deadline(String description, String by) {
        super(description, false);
        this.by = by;
        ArrayList<String> arr = new ArrayList<>(List.of(by.split(" ")));

        try {
            this.byDate = LocalDate.parse(arr.get(0));
            this.by = byDate.format(DateTimeFormatter.ofPattern("EEEE MMM d yyyy"));

            if (arr.size() > 1) {
                this.byTime = LocalTime.parse(arr.get(1), DateTimeFormatter.ofPattern("HHmm"));
                byTimeStr = byTime.format(DateTimeFormatter.ofPattern("HHmm"));
            }
            System.out.println("Excellent sir, I've added the task: ");
            System.out.println(this);
        } catch (DateTimeParseException d) {
            System.out.println(dateTimeParseErrMsg);
        }
    }



    @Override
    protected String getTaskFileFormat() {
        return "D" + " | " + super.getTaskFileFormat() + " | " + by;
    }



    @Override
    public String getTaskInline() {
        return "[D]" + super.getTaskInline();
    }

    @Override
    public String getTaskInline(Integer index) {
//        System.out.println(getDateTimeFormatted());
        return index.toString() + ". [D]" + super.getTaskInline() + " (by: " + by + " " + byTimeStr + ")";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + " " + byTimeStr + ")";
    }
}
