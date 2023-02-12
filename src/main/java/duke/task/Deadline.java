package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
public class Deadline extends Task {

    protected Optional<LocalDate> by;
    protected String strBy;
    protected String tag;
    public Deadline(String description, String strDate) {
        super(description);
        this.tag = "[D]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.by = Optional.of(LocalDate.parse(strDate, formatter));
        } catch (Exception e) {
            this.strBy = strDate;
        }
    }
    public Deadline(String description, String strDate, boolean isMark) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.by = Optional.of(LocalDate.parse(strDate, formatter));
        } catch (Exception e) {
            this.strBy = strDate;
        }
        this.tag = "[D]";
        super.markTask(isMark);
    }
    public String getDate() {
        if (strBy == null) {
            DateTimeFormatter date = DateTimeFormatter.ofPattern("MMM dd yyyy");
            String formattedDate = this.by.get().format(date);
            return formattedDate;
        }
        return strBy;
    }
    public String getTag() {
        return this.tag;
    }
    @Override
    public String toString() {
        return this.tag + super.toString() + " (by: " + this.getDate() + ")";
    }
}
