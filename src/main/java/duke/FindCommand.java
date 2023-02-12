package duke;

/**
 * Encapsulates a command to find matching tasks based on a keyword.
 */
public class FindCommand extends Command {
    private String keyword;
    private TaskList tasks;

    /**
     * Constructor for creating a FindCommand object.
     *
     * @param tasks The current list of tasks.
     * @param arguments Arguments received upon going through the parser.
     * @throws DukeException if user does not enter keyword.
     */
    public FindCommand(TaskList tasks, String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("Keyword missing!");
        }
        this.tasks = tasks;
        this.keyword = arguments[1];
    }

    @Override
    public String execute() {
        return tasks.getMatchingTasksString(keyword);
    }
}
