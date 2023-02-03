package duke.tasktypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeExceptions;

/**
 * Class which represents a Task with a deadline.
 */
public class Deadlines extends Task {
    protected String endsBy;
    protected LocalDate dueDateBy;
    protected LocalTime dueTime;
    protected boolean validTime = false;


    /**
     * Constructor to initialize a Deadlines task/
     * @param taskName String containing the task's name and due date.
     * @throws DukeExceptions if the task name is empty.
     * @throws DateTimeParseException if the due date is an invalid format.
     */
    public Deadlines(String taskName) throws DukeExceptions, DateTimeParseException {
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

    /**
     * Function to format the time of the due date if it is a valid time but not in correct format.
     * @param toFormat String representation of the time to be formatted.
     */
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

    /**
     * Function to reformat the stored date when reading from the stored list due to differences in input and
     * output date format.
     */
    public void reformat() {
        String[] reconstructedDateArr = this.endsBy.split(" ");
        String reconstructedDateInString = reconstructedDateArr[0] + " "
                + reconstructedDateArr[1] + " " + reconstructedDateArr[2];
        reconstructedDateInString = reconstructedDateInString.replace(" ", "/");
        DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("MMM/d/uuuu");
        LocalDate reconstructedDate = LocalDate.parse(
                LocalDate.parse(reconstructedDateInString, originalFormat).toString());
        this.dueDateBy = reconstructedDate;
    }

    /**
     * Function to check the validity of the due date when first input by the user.
     * @param toCheck String representation of the date input by the user.
     * @return boolean indicating if the date input by the user is valid.
     * @throws DateTimeParseException if the date input is invalid.
     */
    public boolean checkValidityOfInitialInputDate(String toCheck) throws DateTimeParseException {
        boolean isValid = true;
        try {
            LocalDate.parse(toCheck);
        } catch (DateTimeParseException exception) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Function to check the validity of the due date when reading from the stored list.
     * @param toCheck String representation of the date when read from stored list.
     * @return boolean indicating if the date read from the stored list is valid.
     * @throws DateTimeParseException if the date read is invalid.
     */
    public boolean checkValidityOfDateFromList(String toCheck) throws DateTimeParseException {
        boolean isValid = true;
        try {
            DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("MMM/d/uuuu");
            LocalDate.parse(toCheck, originalFormat);
        } catch (DateTimeParseException exception) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Function to check if the time input by the user is valid
     * @param toCheck String representation of the time.
     * @return boolean indicating if the time is valid.
     * @throws DateTimeParseException if the time is invalid.
     */
    public boolean checkValidityOfTime(String toCheck) throws DateTimeParseException {
        boolean isValid = true;
        try {
            LocalTime.parse(toCheck);
        } catch (DateTimeParseException exception) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Function to format the deadline task's due date if it is a valid input.
     * @return String representation of the due date if valid, else string representation of prompting valid input
     *      is returned.
     */
    public String taskDate() {
        String toReturn = "";
        if (this.dueDateBy == null) {
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

    /**
     * Function to check, and accordingly print String representation of deadline.
     * @return String representation of deadline task.
     */
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
                    toReturn = "[D][X]" + this.getName() + "(by: "
                            + newFormatOfDate + " " + this.dueTime.toString() + ")";
                } else {
                    toReturn = "[D][X]" + this.getName() + "(by: "
                            + newFormatOfDate + ")";
                }
            } else {
                if (validTime) {
                    toReturn = "[D][ ]" + this.getName() + "(by: "
                            + newFormatOfDate + " " + this.dueTime.toString() + ")";
                } else {
                    toReturn = "[D][ ]" + this.getName() + "(by: " + newFormatOfDate + ")";
                }
            }
        } else {
            if (this.done) {
                toReturn = "[D][X]" + this.getName() + "(by: " + this.endsBy + ")";
            } else {
                toReturn = "[D][ ]" + this.getName() + "(by: " + this.endsBy + ")";
            }
        }
        return toReturn;
    }
}
