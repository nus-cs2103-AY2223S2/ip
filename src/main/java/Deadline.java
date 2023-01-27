import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDateTime dueDate;
    private static final DateTimeFormatter FORMATTER  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Deadline create(String str) throws DukeException{
        if (str.length() < 1) {
            throw new DukeException();
        } else {
            String[] text = str.substring(1).split(" /by ");
            if (text.length < 2) {
                throw new DukeException();
            } else {
                return new Deadline(text[0], LocalDateTime.parse(text[1], Deadline.FORMATTER));
            }
        }
    }

    public static Deadline create(String str, Boolean isDone) throws DukeException{
        if (str.length() < 1) {
            throw new DukeException();
        } else {
            String[] text = str.substring(1).split(" /by ");
            if (text.length < 2) {
                throw new DukeException();
            } else {
                return new Deadline(text[0], LocalDateTime.parse(text[1], Deadline.FORMATTER), isDone);
            }
        }
    }


    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadline(String description, LocalDateTime dueDate, Boolean isDone) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return this.dueDate.format(FORMATTER);
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm")) + ")";
    }
}