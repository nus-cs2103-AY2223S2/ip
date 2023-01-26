import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {

    private LocalDate date = null;
    private LocalTime time = null;

    public Deadlines(String name, String sTime) throws DukeException {
        super(name);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(sTime, dtf);
            date = dateTime.toLocalDate();
            time = dateTime.toLocalTime();
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(sTime, df);
                this.date = date;
            } catch (DateTimeParseException e2) {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public LocalTime getTime() {
        return this.time;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String toString() {
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String result = "";
        if (this.getStatus()) {
            result += "[D][X] " + this.getName() + " (by: ";
        } else {
            result += "[D][ ] " + this.getName() + " (by: ";
        }
        result += this.getDate().format(df);
        result += this.getTime() == null ? "" : " " + this.getTime().format(tf);
        result += ")";
        return result;
    }
}
