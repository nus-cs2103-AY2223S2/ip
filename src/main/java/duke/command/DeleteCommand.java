package duke.command;

import java.util.Scanner;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

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
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
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
            ui.deleteMsg(tasks, index);
            tasks.remove(index);
            storage.saveToDisk(tasks);
            scanner.close();
        } catch (DukeException e) {
            ui.deleteError();
        }
    }
}