package command;

import java.time.LocalDate;
import storage.TaskList;
import task.Deadline;

/**
 * Command component that executes a deadline command.
 */
public class DeadlineCommand extends Command {
    private String task;
    private LocalDate deadline;

    /**
     * Constructor for a create deadline command.
     *
     * @param task     task to be added
     * @param deadline deadline for task
     */
    public DeadlineCommand(String task, LocalDate deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    @Override
    public String run(TaskList taskList) {
        assert this.task.trim() != "";
        Deadline newDeadline = taskList.createDeadline(this.task, this.deadline);
        return "Got it. I've added this task:\n" + newDeadline + "\nNow you have " + taskList.countTask()
                + " tasks in the list.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeadlineCommand)) {
            return false;
        }

        DeadlineCommand that = (DeadlineCommand) o;

        if (!task.equals(that.task)) {
            return false;
        }
        return deadline.equals(that.deadline);
    }
}
