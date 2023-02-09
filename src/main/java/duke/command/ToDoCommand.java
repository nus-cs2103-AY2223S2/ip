package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * A subclass of Command that represents the
 * command to add a todo task into the TaskList.
 */
public class ToDoCommand extends Command {

    /**
     * Constructor of ToDoCommand.
     * @param command Command from the user.
     */
    public ToDoCommand(String[] command) {
        super(command);
    }

    /**
     * Method to add a todo task into the TaskList.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of Duke.
     * @return Confirmation message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (command.length == 1) {
                throw new DukeException(null, null);
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < command.length; i++) {
                stringBuilder.append(command[i]);
                if (i + 1 != command.length) {
                    stringBuilder.append(" ");
                }
            }
            tasks.add(new Todo(stringBuilder.toString()));
            storage.saveToDisk(tasks);
            return ui.addMsg(tasks);
        } catch (DukeException e) {
            return ui.todoError();
        }
    }
}
