package duke;

import java.io.IOException;

public class DeadlineCommand extends Command {
    private Deadline deadline;

    /**
     * Constructor for a Deadline command.
     *
     * @param description Description for the Deadline task.
     * @param by Deadline for the task.
     */
    public DeadlineCommand(String description, String by) {
        deadline = new Deadline(description, by);
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        tasks.add(deadline);
        storage.save(tasks.getTasks());
        return response.showAddTask(deadline, tasks.getTasks());
    }
}
