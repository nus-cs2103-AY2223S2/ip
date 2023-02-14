package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import util.DateTimeParser;
import util.DukeException;
import util.TaskList;


/**
 * Subclass of Task which includes a deadline.
 * @author Merrick
 */
public class DeadlineTask extends Task {
    /**
     * Constructor of DeadlineTask.
     * @param taskName Description of DeadlineTask
     * @param deadline Deadline of the DeadlineTask
     */
    public DeadlineTask(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
        this.taskType = "D";
    }

    /**
     * Constructor of DeadlineTask.
     * @param taskName Description of DeadlineTask
     * @param deadline Deadline of the DeadlineTask
     * @param isCompleted Completion status of DeadlineTask
     */
    public DeadlineTask(String taskName, LocalDateTime deadline, boolean isCompleted) {
        super(taskName, isCompleted);
        assert deadline != null : "Deadline is invalid";
        this.deadline = deadline;
        this.taskType = "D";
    }

    /**
     * Outputs the deadline of the DeadlineTask in a String format
     * @return String format of the DeadlineTask's deadline.
     */
    public String formatDateTime() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Creates a DeadlineTask from user input.
     * @param command Input provided by user.
     * @param t TaskList to store the newly created DeadlineTask
     * @return Message to be shown to the user.
     * @throws DukeException If command is invalid.
     */
    public static String createDeadlineTask(String command, TaskList t) throws DukeException {
        ArrayList<String> input = new ArrayList<>(Arrays.asList(command.split(" ")));
        if (input.size() <= 1) {
            throw new DukeException("deadline");
        }
        int byIndex = input.indexOf("/by");
        StringBuilder taskName = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        for (int i = 1; i < input.size(); i++) {
            if (i < byIndex) {
                taskName.append(input.get(i));
                if (i < byIndex - 1) {
                    taskName.append(" ");
                }
            } else if (i > byIndex) {
                deadline.append(input.get(i));
                if (i < input.size() - 1) {
                    deadline.append(" ");
                }
            }
        }
        DeadlineTask d = new DeadlineTask(taskName.toString(), DateTimeParser.dateTimeParser(deadline.toString()));
        return t.addTask(d);
    }

    @Override
    public String saveTaskString() {
        return String.format(super.saveTaskString() + "|%s", this.deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), formatDateTime());
    }

    /**
     * Changes the deadline of the DeadlineTask.
     * @param days Number of days to shift back.
     * @param hours Number of hours to shift back.
     * @param minutes Number of minutes to shift back.
     * @return new deadline of the Deadline Task.
     */
    public String snoozeDeadline(int days, int hours, int minutes) {
        this.deadline = this.deadline.plusDays(days);
        this.deadline = this.deadline.plusHours(hours);
        this.deadline = this.deadline.plusMinutes(minutes);
        return String.format("New deadline is %s!", DateTimeParser.datetimeFormatter(this.deadline) );
    }

    /**
     * Changes the deadline of the DeadlineTask by a default value of 5 minutes.
     * @return New deadline of the DeadlineTask.
     */
    public String snoozeDeadline() {
        return this.snoozeDeadline(0, 0, 5);
    }
}
