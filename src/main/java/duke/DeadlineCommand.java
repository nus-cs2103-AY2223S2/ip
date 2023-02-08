package duke;

import java.io.IOException;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructor for a Deadline command.
     *
     * @param description Description for the Deadline task.
     * @param by Deadline for the task.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        storage.save(tasks.getTasks());
        return response.showAddTask(deadline, tasks.getTasks());
    }
}
