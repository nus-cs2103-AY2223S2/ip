import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Task {
    protected String name;
    protected boolean isDone;

    public Task() {
        this("");
    }

    public Task(String description) {
        this.name = description;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }

    /**
     * Parse the date into a date object
     * @param dateString: the string representation of the date
     * @return the date object
     */
    protected static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    /**
     * Gives the string representation of date
     * @param date: a date object
     * @return the corresponding string representation
     */
    protected static String printDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
