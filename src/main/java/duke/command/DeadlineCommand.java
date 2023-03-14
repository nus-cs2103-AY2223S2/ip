package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.storage.StorageList;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Deadline command for the tasks with deadline.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String targetDate;
    private String targetTiming;
    private LocalDate unformattedTargetDate;
    private LocalTime unformattedTargetTime;
    private LocalDate currentDate;
    private LocalTime currentTime;
    private boolean haveFormatErr;

    /**
     * Constructor for deadline command
     *
     * @param fullCommand Command for the deadline task
     */
    public DeadlineCommand(String fullCommand) throws DukeException {
        String[] deadlineCommand = fullCommand.split("/by ");
        String[] deadlineMessage = deadlineCommand[0].split("deadline ");
        this.description = deadlineMessage[1];
        formatDeadlineCommand(deadlineCommand[1]);

    }

    /**
     * Method that formats the deadline command string to return required target date and time.
     *
     * @param unformattedTiming Unformatted date and time.
     */
    public void formatDeadlineCommand(String unformattedTiming) throws DukeException {
        try {
            String[] targetDateArray = unformattedTiming.split(" ");
            this.haveFormatErr = true;
            Pattern datePattern = Pattern.compile("(\\d{4})\\-(\\d{2})\\-(\\d{2})");
            Pattern timePattern = Pattern.compile("(\\d{2})\\:(\\d{2})");
            Matcher dateMatcher = datePattern.matcher(targetDateArray[0].trim());
            Matcher timeMatcher = timePattern.matcher(targetDateArray[1]);
            if (dateMatcher.matches() && timeMatcher.matches()) {
                this.unformattedTargetDate = LocalDate.parse(targetDateArray[0]);
                this.unformattedTargetTime = LocalTime.parse(targetDateArray[1], DateTimeFormatter.ofPattern("HH:mm"));
                this.targetDate = unformattedTargetDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                this.targetTiming = unformattedTargetTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
                this.haveFormatErr = false;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date and time");
        }
    }

    /**
     * Checks if the input deadline is over the current date.
     *
     * @return boolean State of deadline being over.
     */
    public boolean isDeadlineOver() {
        currentDate = LocalDate.now();
        currentTime = LocalTime.now();
        int compareDate = currentDate.compareTo(unformattedTargetDate);
        int compareTime = currentTime.compareTo(unformattedTargetTime);
        if (compareDate > 0 && compareTime > 0) {
            return true;
        } else if (compareDate > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the input has an incorrect date and time format.
     *
     * @return boolean - true or false if there is a formatting error.
     */
    public boolean isFormatCorrect() {
        return !(this.haveFormatErr);
    }

    /**
     * Execute method for deadline command to display message for deadline and warn users
     * of formatting.
     *
     * @param tasks   - task list of the current tasks.
     * @param storage - database of the history of commands.
     * @return String
     */
    public String execute(TaskList tasks, StorageList storage) {
        Deadline deadlineTask = new Deadline(description, targetDate, targetTiming);
        if (isFormatCorrect() && !isDeadlineOver()) {
            tasks.addToList(deadlineTask);
            return "Got it, I've added this task:\n" + deadlineTask + tasks.getLengthMessage();
        } else if (isFormatCorrect() && isDeadlineOver()) {
            return "Deadline is over, please insert another deadline.";

        } else {
            return "Wrong Format, Please fill in with the following format: YYYY-MM-DD HH:MM";
        }

    }

}

