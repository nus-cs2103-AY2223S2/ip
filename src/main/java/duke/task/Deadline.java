package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Class contains variables and methods related to a Deadline task.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Creates an instance of Deadline.
     * @param taskName String of Deadline task name.
     * @param deadline contains LocalDate deadline of Deadline.
     */
    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toFile() {
        return String.format("D | %s | %s\n", super.toFile(), deadline);
    }

    /**
     * Creates Deadline task from String from file.
     * @param taskNameData String containing taskName from file.
     * @param doneData String containing whether task is done from file string.
     * @param deadlineData String containing deadline from file.
     * @return Deadline created from file input.
     * @throws DukeException If string input is empty or of the wrong format.
     */
    public static Deadline toDeadlineFromFileStr(String taskNameData, String doneData, String deadlineData)
            throws DukeException {
        Deadline d = null;
        doneData = doneData.trim();
        deadlineData = deadlineData.trim();
        taskNameData = taskNameData.trim();
        if (taskNameData.isEmpty()) {
            throw new DukeException("todo");
        }
        if (doneData.isEmpty()) {
            throw new DukeException("missing details");
        }
        if (deadlineData.isEmpty()) {
            throw new DukeException("timing");
        }
        try {
            LocalDate deadline = LocalDate.parse(deadlineData);
            d = new Deadline(taskNameData, deadline);
            boolean completed = Integer.parseInt(doneData) == 1;
            d.setCompleted(completed);
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
        return d;
    }

    @Override
    public String toString() {
        String s;
        String deadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.isCompleted) {
            s = "[D]" + super.toString() + " (by: " + deadline + ")";
        } else {
            s = "[D]" + super.toString() + " (by: " + deadline + ")";
        }
        return s;
    }
}
