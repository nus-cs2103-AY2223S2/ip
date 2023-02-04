package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a command that creates an todo task.
 *
 * @author wz2k
 */
public class CreateTodoCommand extends Command {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command for creating todos.
     *
     * @param commandMessage User's input.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public CreateTodoCommand(String commandMessage, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Creates and stores a new todo task and returns the reply
     * for todo creation.
     *
     * @return Taskbot reply to the todo creation.
     */
    @Override
    public String execute() {
        try {
            String[] commandMessageArr = commandMessage.split(" ", 2);
            assert commandMessageArr.length == 2 : "todo command should split into 2";
            Task task = new Todo(commandMessageArr[1], false);

            taskList.addTask(task);
            storage.storeTask(task);
            return "The following task has been added:\n" + "  " + task;
        } catch (IOException exception) {
            return "An error has occurred!\n" + exception.getMessage();
        }
    }
}
