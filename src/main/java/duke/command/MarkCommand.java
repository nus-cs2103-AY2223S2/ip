package duke.command;

import java.util.Scanner;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.exception.DukeException;

/**
 * A subclass of Command that represents
 * the command to mark a task in the TaskList.
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class MarkCommand extends Command {

    /**
     * Constructor of MarkCommand.
     * @param command Command from the user.
     */
    public MarkCommand(String[] command) {
        super(command);
    }

    /**
     * Method to mark the target task in the TaskList.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (this.command.length == 1) {
                throw new DukeException(null, null);
            }
            Scanner scanner = new Scanner(command[1]);
            if (!scanner.hasNextInt()) {
                scanner.close();
                throw new DukeException(null, null);
            }
            int index = scanner.nextInt() - 1;
            if (index >= tasks.size() || index < 0) {
                scanner.close();
                throw new DukeException(null, null);
            }
            tasks.get(index).markAsDone();
            ui.markMsg(tasks, index);
            storage.saveToDisk(tasks);
            scanner.close();
        } catch (DukeException e) {
            ui.markError();
        }
    }
}