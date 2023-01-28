import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate bydate = null;
    protected LocalTime bytime = null;

    public Deadline(String description, String by) {
        super(description);
        // eg. 2019-12-01 10:15
        String[] strs = by.split(" ");
        this.bydate = LocalDate.parse(strs[0]);
        if (strs.length == 2) {
            this.bytime = LocalTime.parse(strs[1]);
        }
    }

    @Override
    public String storageStr() {
        return "D | " + super.getStatusValue() + " | " + super.description
                + " | " + this.by;
    }

    @Override
    public String toString() {
        String result = "[D]" + super.toString() + " (by: "
                + bydate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (bytime != null) {
            result = result + " " + bytime.toString();
        }
        return result + ")";
    }

}