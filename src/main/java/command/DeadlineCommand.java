package command;

import storage.TaskList;
import task.Deadline;

public class DeadlineCommand extends Command {
    private String task;
    private String deadline;

    /**
     * Constructor for a create deadline command.
     * @param task      task to be added
     * @param deadline  deadline for task
     */
    public DeadlineCommand(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    @Override
    public String run(TaskList taskList) {
        Deadline newDeadline = taskList.createDeadline(this.task, this.deadline);
        return "Got it. I've added this task:\n" + newDeadline +
                "\nNow you have " +taskList.countTask() + " tasks in the list.";
    }
}
