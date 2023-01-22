package Tasks;

import Exceptions.IncompleteCommandException;
import Exceptions.UnknownCommandException;
import Utils.DateTimeUtils;
import Utils.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Deadline extends Task {

    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private String time;
    private String deadline;

    public Deadline(String description, String deadline, String time) {
        super(description);
        this.deadlineDate = LocalDate.parse(deadline);
        this.deadlineTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("Hmm"));
        this.time = time;
        this.deadline = deadline;
    }

    /**
     * Factory method to create a Deadline task based on commands.
     * @param input command input
     * @return a Deadline task object based on command input
     */
    public static Deadline create(String input) {
        String[] splitCommands = input.trim().split(" ");
        splitCommands = StringUtils.removeWhiteSpace(splitCommands);
        int deadlineIndex = StringUtils.searchString(splitCommands, "deadline");
        int byIndex = StringUtils.searchString(splitCommands,"/by");
        if (byIndex == -1) {
            throw new IncompleteCommandException("Incomplete arguments for command deadline, I have found", null);
        }
        if (splitCommands[splitCommands.length - 1].equals("/by")) {
            throw new IncompleteCommandException("Deadline entered, you have not.", null);
        }
        if (deadlineIndex + 1 == byIndex) {
            throw new IncompleteCommandException("No Task Description, You have entered", null);
        }
        String description = StringUtils.joinString(splitCommands, deadlineIndex + 1, byIndex - 1);
        String deadlineDatePart = splitCommands[splitCommands.length - 2];
        String deadlineTimePart = splitCommands[splitCommands.length - 1];
        if (!DateTimeUtils.isCorrectDateFormat(deadlineDatePart)) {
            throw new UnknownCommandException("Not a valid date format, it is!", null);
        } else if (!DateTimeUtils.isCorrectTimeFormat(deadlineTimePart)) {
            throw new UnknownCommandException("Not a valid time format, it is!", null);
        }
        return new Deadline(description, deadlineDatePart, deadlineTimePart);
    }

    public static Deadline create(String description, String deadline, String time, String marked) {
        Deadline task = new Deadline(description, deadline, time);
        if (marked.equals("1")) {
            task.markSilent();
        }
        return task;
    }

    /**
     * Checks the status of the task and logs to stdout.
     */
    @Override
    public void mark() {
        super.mark();
        System.out.println(String.format(" [%s][%s] %s (by: %s %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.deadlineTime.format(DateTimeFormatter.ofPattern("h:mm a"))));
    }

    /**
     * Unchecks the status of the task and logs to stdout.
     */
    @Override
    public void unmark() {
        super.unmark();
        System.out.println(String.format(" [%s][%s] %s (by: %s %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.deadlineTime.format(DateTimeFormatter.ofPattern("h:mm a"))));
    }

    /**
     * Method for child classes to return their type.
     * @return string type of task
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * String representation of the Deadline Task.
     * @return string representation of the Deadline Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.deadlineTime.format(DateTimeFormatter.ofPattern("h:mm a")));
    }

    @Override
    public String writeTask() {
        return String.format("%s %d %s by %s",
                this.getTaskType(),
                super.isCompleted() ? 1 : 0,
                this.description,
                this.deadline + " " + time);
    }
}
