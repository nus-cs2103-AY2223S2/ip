import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    String endsBy;
    LocalDate dueDateBy;
    LocalTime dueTime;
    boolean validTime = false;


    Deadlines(String taskName) throws DukeExceptions{
        super(taskName.split("/by ")[0]);
        if (taskName.length() <= 0 || taskName.isBlank()) {
            throw new DukeExceptions("deadline");
        }
        this.endsBy = taskName.split("/by ")[1];
        String[] endsByInArr = this.endsBy.split(" ");
        String possibleDueDate = endsByInArr[0];
        if (endsByInArr.length > 1) {
            String timeInString = endsByInArr[1];
            if (timeInString.length() == 4) {
                String firstTwo = timeInString.substring(0, 2);
                String nextTwo = timeInString.substring(2, 4);
                String toParse = firstTwo + ":" + nextTwo;
                if (checkValidityOfTime(toParse)) {
                    this.dueTime = LocalTime.parse(toParse);
                    validTime = true;
                }
            } else if (timeInString.length() == 5) {
                if (checkValidityOfTime(timeInString)) {
                    this.dueTime = LocalTime.parse(timeInString);
                    validTime = true;
                }
            }
        }
        if (possibleDueDate.indexOf("/") != -1) {
            possibleDueDate = possibleDueDate.replace("/", "-");
        }
        if (checkValidityOfDate(possibleDueDate)) {
            this.dueDateBy = LocalDate.parse(possibleDueDate);
        }
    }

    public boolean hasTime() {
        return this.validTime;
    }

    public boolean checkValidityOfDate(String toCheck) throws DateTimeParseException{
        boolean isValid = true;
        try {
            LocalDate.parse(toCheck);
        } catch (DateTimeParseException DTPE) {
            isValid = false;
        }
        return isValid;
    }

    public boolean checkValidityOfTime(String toCheck) throws DateTimeParseException{
        boolean isValid = true;
        try {
            LocalTime.parse(toCheck);
        } catch (DateTimeParseException DTPE) {
            isValid = false;
        }
        return isValid;
    }

    public String taskDate() {
        String toReturn = "";
        if (this.dueDateBy.equals(null)) {
            toReturn = "This task does not have a valid due date! Please input the date in this format: YYYY-MM-DD";
        } else {
            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM/d/uuuu");
            String newFormatOfDate = this.dueDateBy.format(newFormat).replace("/", " ");
            if (validTime) {
                toReturn = "This task is due by: " + newFormatOfDate + " " + this.dueTime.toString();
            } else {
                toReturn = "This task is due by: " + newFormatOfDate;
            }
        }
        return toReturn;
    }

    @Override
    public String toString() {
        String toReturn = "";
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM/d/uuuu");
        String newFormatOfDate = this.dueDateBy.format(newFormat).replace("/", " ");
        if (this.done) {
            if (validTime) {
                toReturn = "[D][X]" + this.name + "(by: " + newFormatOfDate + " " + this.dueTime.toString() + ")";
            } else {
                toReturn = "[D][X]" + this.name + "(by: " + newFormatOfDate + ")";
            }
        } else {
            if (validTime) {
                toReturn = "[D][ ]" + this.name + "(by: " + newFormatOfDate + " " + this.dueTime.toString() + ")";
            } else {
                toReturn = "[D][ ]" + this.name + "(by: " + newFormatOfDate + ")";
            }
        }
        return toReturn;
    }
}
