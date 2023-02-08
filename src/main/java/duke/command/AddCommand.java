package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that adds a task to the tasklist when executed.
 */
public class AddCommand extends Command {
    private Task task;
    private TaskType type;

    public AddCommand(Task task) {
        this.task = task;
    }

    public AddCommand(TaskType type) {
        this.type = type;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks);
        return ui.showAddTask(task, tasks);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String commandHelp() {
        assert this.type == TaskType.TODO
                || this.type == TaskType.EVENT
                || this.type == TaskType.DEADLINE
                : "Task type should be of todo, event or deadline.";

        switch (this.type) {
        case TODO:
            return "Showing help for command: todo\n"
                    + Ui.showSepLine()
                    + "Creates a todo task: no date/time attached.\n"
                    + Ui.showSepLine()
                    + "Usage:\n"
                    + "todo [(String) TASK]\n\n"
                    + "Example:\n"
                    + "todo CS2103T Week 5 iP\n"
                    + Ui.showSepLine();
        case EVENT:
            return "Showing help for command: event\n"
                    + Ui.showSepLine()
                    + "Creates an event task: date/time attached but lenient format\n"
                    + Ui.showSepLine()
                    + "Usage:\n"
                    + "event [(String) TASK /at TIME/DATE]\n\n"
                    + "Example:\n"
                    + "event Career Fair /at 7/2 - 8/2\n"
                    + Ui.showSepLine();
        case DEADLINE:
            return "Showing help for command: deadline\n"
                    + Ui.showSepLine()
                    + "Creates a deadline task: strict date deadline format\n"
                    + Ui.showSepLine()
                    + "Usage:\n"
                    + "deadline [(String) TASK /by yyyy-mm-dd]\n\n"
                    + "Example:\n"
                    + "deadline CS2103T Week 5 Quiz /by 2023-02-10\n"
                    + Ui.showSepLine();
        default:
            return "No such Task command";
        }

    }

    @Override
    public String toString() {
        return "Command: Add task " + task.toString();
    }
}
