import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String fixDateToParse(String date) {
        String error = "0000-01-01";
        String[] arr = date.split("/");
        String year = "";
        String month = "";
        String day = "";
        if (arr.length == 3) {
            year = arr[2];
            if (arr[1].length() == 1) {
                month = "0" + arr[1];
            } else if (arr[1].length() == 2) {
                month = arr[1];
            } else {
                return error;
            }

            if (arr[0].length() == 1) {
                day = "0" + arr[0];
            } else if (arr[0].length() == 2) {
                day = arr[0];
            } else {
                return error;
            }

            return year + "-" + month + "-" + day;
        } else {
            return error;
        }
    }
    public String split(String by) {
        String[] arr = by.split(" ", 2);
        String stringToParse = fixDateToParse(arr[0]);
        LocalDate d = LocalDate.parse(stringToParse);
        if (arr.length == 1) {
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " + arr[1];
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    public abstract String toText();
}