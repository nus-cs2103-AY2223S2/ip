package duke.tasktypes;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeExceptions;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    String endsBy;
    LocalDate dueDateBy;
    LocalTime dueTime;
    boolean validTime = false;


    public Deadlines(String taskName) throws DukeExceptions, DateTimeParseException{
        super(taskName.split("/by ")[0]);
        if (taskName.length() <= 0 || taskName.isBlank()) {
            throw new DukeExceptions("deadline");
        }
        this.endsBy = taskName.split("/by ")[1];
        String[] endsByInArr = this.endsBy.split(" ");
        String possibleDueDate = endsByInArr[0];
        if (endsByInArr.length > 1) {
            String timeInString = endsByInArr[1];
            formatTimeIfValid(timeInString);
        }
        if (possibleDueDate.indexOf("/") != -1) {
            possibleDueDate = possibleDueDate.replace("/", "-");
        }
        if (checkValidityOfInitialInputDate(possibleDueDate)) {
            this.dueDateBy = LocalDate.parse(possibleDueDate);
        }
    }

    public void formatTimeIfValid(String toFormat) {
        if (toFormat.length() == 4) {
            String firstTwo = toFormat.substring(0, 2);
            String nextTwo = toFormat.substring(2, 4);
            String toParse = firstTwo + ":" + nextTwo;
            if (checkValidityOfTime(toParse)) {
                this.dueTime = LocalTime.parse(toParse);
                validTime = true;
            }
        } else if (toFormat.length() == 5) {
            if (checkValidityOfTime(toFormat)) {
                this.dueTime = LocalTime.parse(toFormat);
                validTime = true;
            }
        }
    }

    public boolean hasTime() {
        return this.validTime;
    }

    public void reformat() {
        String[] reconstructedDateArr = this.endsBy.split(" ");
        String reconstructedDateInString = reconstructedDateArr[0] + " " + reconstructedDateArr[1] + " " + reconstructedDateArr[2];
        reconstructedDateInString = reconstructedDateInString.replace(" ", "/");
        DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("MMM/d/uuuu");
        LocalDate reconstructedDate = LocalDate.parse(LocalDate.parse(reconstructedDateInString, originalFormat).toString());
        this.dueDateBy = reconstructedDate;
    }

    public boolean checkValidityOfInitialInputDate(String toCheck) throws DateTimeParseException {
        boolean isValid = true;
        try {
            LocalDate.parse(toCheck);
        } catch (DateTimeParseException DTPE) {
            isValid = false;
        }
        return isValid;
    }

    public boolean checkValidityOfDateFromList(String toCheck) throws DateTimeParseException {
        boolean isValid = true;
        try {
            DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("MMM/d/uuuu");
            LocalDate.parse(toCheck, originalFormat);
        } catch (DateTimeParseException DTPE) {
            isValid = false;
        }
        return isValid;
    }

    public boolean checkValidityOfTime(String toCheck) throws DateTimeParseException {
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
        if (this.dueDateBy == null) {
            String[] checkIfTrulyNull = this.endsBy.split(" ");
            if (checkIfTrulyNull.length >= 3) {
                String checkingInString = checkIfTrulyNull[0] + "/" + checkIfTrulyNull[1] + "/" + checkIfTrulyNull[2];
                if (checkValidityOfDateFromList(checkingInString)) {
                    reformat();
                    if (checkIfTrulyNull.length == 4) {
                        formatTimeIfValid(checkIfTrulyNull[3]);
                    }
                } else {
                    System.out.println("The date is invalid!");
                }
            }
        }
        if (this.dueDateBy != null) {
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
        } else {
            if (this.done) {
                toReturn = "[D][X]" + this.name + "(by: " + this.endsBy + ")";
            } else {
                toReturn = "[D][ ]" + this.name + "(by: " + this.endsBy + ")";
            }
        }
        return toReturn;
    }
}
