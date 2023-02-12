package vic.commands;

import vic.exceptions.DukeException;
import vic.tasks.ITask;
import vic.tasks.Todo;
import vic.utilities.Parser;

/**
 * Represents AddTodo action command. A <code>AddTodo</code> object corresponds to
 * the action adding a todos task to task list
 */
public class AddTodo extends ICommand {
    public AddTodo(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {
        getParser().forTodo();
        ITask task = new Todo(getParser().getDescription());
        getParser().getTaskManager().add(task);
        setMsg(task + "\nAdded" + "\nNow you have " + getParser()
                .getTaskManager().size() + " duke.tasks in the list.");

        return false;

    }
}
