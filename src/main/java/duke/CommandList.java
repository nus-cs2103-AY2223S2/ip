package duke;

/**
 * Command to see everything in the list.
 */
public class CommandList extends Command {
    private final TaskList taskList;

    /**
     * Constructor for CommandList.
     *
     * @param tasklist List of all tasks.
     */
    public CommandList(TaskList tasklist) {
        this.taskList = tasklist;
    }

    @Override
    public String execute() {
        return taskList.toString();
    }
}
