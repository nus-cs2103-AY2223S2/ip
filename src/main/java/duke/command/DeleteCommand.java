package duke.command;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A subclass of Command that represents
 * the command to delete a task from the TaskList.
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class DeleteCommand extends Command {

    /**
     * Constructor of DeleteCommand.
     * @param command Command from the user.
     */
    public DeleteCommand(String[] command) {
        super(command);
    }

    /**
     * Method to delete a task from the TaskList.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of Duke.
     * @return The confirmation message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (command.length == 1) {
                throw new DukeException(null, null);
            }
            Scanner scanner = new Scanner(command[1]);
            if (!scanner.hasNextInt()) {
                scanner.close();
                throw new DukeException(null, null);
            }
            int index = scanner.nextInt() - 1;
            if (tasks.get(index) == null) {
                scanner.close();
                throw new DukeException(null, null);
            }
            Task deletedTask = tasks.get(index);
            tasks.remove(index);
            storage.saveToDisk(tasks);
            scanner.close();
            return ui.deleteMsg(deletedTask, tasks.size());
        } catch (DukeException e) {
            return ui.deleteError();
        }
    }
}
