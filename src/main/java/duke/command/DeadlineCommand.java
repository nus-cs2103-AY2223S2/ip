package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A subclass of Command that represents
 * the command to add a deadline task into
 * the TaskList.
 *
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class DeadlineCommand extends Command {

    /**
     * Constructor of DeadlineCommand.
     * @param command Command from the user.
     */
    public DeadlineCommand(String[] command) {
        super(command);
    }

    /**
     * Method to add a deadline task to the TaskList.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder endTime = new StringBuilder();
            boolean b = false;
            for (int i = 1; i < command.length; i++) {
                if (b) {
                    endTime.append(command[i]);
                    if (i + 1 != command.length) {
                        endTime.append(" ");
                    }
                } else {
                    if (command[i].equals("/by")) {
                        b = true;
                        stringBuilder.setLength(stringBuilder.length() - 1);
                        continue;
                    }
                    stringBuilder.append(command[i]);
                    stringBuilder.append(" ");
                }
            }
            if (endTime.length() == 0) {
                throw new DukeException(null, null);
            }
            tasks.add(new Deadline(stringBuilder.toString(), endTime.toString()));
            ui.addMsg(tasks);
            storage.saveToDisk(tasks);
        } catch (DukeException e) {
            ui.deadlineError();
        }
    }
}
