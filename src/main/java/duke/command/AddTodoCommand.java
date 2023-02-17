package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.IncompleteCommandException;
import duke.task.ToDo;

/**
 * A command representing the user adding a new Todo to the task list.
 */
public class AddTodoCommand extends Command {
    private String contents;

    /**
     * A constructor for AddToDoCommand.
     *
     * @param tasks TaskList object to add Todo object to
     * @param contents Description of task
     */
    public AddTodoCommand(TaskList tasks, String contents) {
        super(tasks);
        this.contents = contents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() throws DukeException {
        try {
            ToDo task = Parser.parseToDo(contents.trim());
            tasks.addTask(task);
            return tasks.addTaskText(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IncompleteCommandException("todo");
        }
    }
}
