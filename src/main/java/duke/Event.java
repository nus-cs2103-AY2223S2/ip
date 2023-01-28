package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    LocalDate startDate;
    LocalDate endDate;
    public Event(String description, String startDate, String endDate) throws NeroException {
        super(description);
        try {
            this.startDate = LocalDate.parse(startDate.trim());
            this.endDate = LocalDate.parse(endDate.trim());
        } catch (DateTimeParseException e) {
            throw new NeroException("Invalid Date!");
        }
}

    public Event(String description, boolean isDone, String startDate, String endDate) throws NeroException {
        super(description, isDone);
        try {
            this.startDate = LocalDate.parse(startDate);
            this.endDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException e) {
            throw new NeroException("Invalid Date!");
        }
    }
    public String dateFormatter() {
        return "from: " + startDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " to: "
                + endDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
    public String getTaskIcon() {
        return "E";
    }



    public String toSave() {
        return this.getTaskIcon() + SEPARATOR + convertBoolean()
                + SEPARATOR + this.getDescription()
                + SEPARATOR + this.startDate + " " + this.endDate;
    }
    @Override
    public String toString() {
        return String.format("[%s]%s %s %s", this.getTaskIcon(), this.getStatusIcon(),
                this.getDescription(), this.dateFormatter());
    }

}
