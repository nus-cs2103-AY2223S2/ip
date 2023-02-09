package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A subclass of Command that represents
 * the command to tag a task.
 * @author Oskar Lew
 * @version 0.2
 * @since 0.1
 */
public class TagCommand extends Command{

    /**
     * Constructor of TagCommand
     * @param command Array of user input.
     */
    public TagCommand(String[] command) {
        super(command);
    }

    /**
     * Method to execute the Tag Command in Duke.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of Duke.
     * @return Confirmation Message that a task has been tagged.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (command.length < 3 || Integer.parseInt(command[1]) > tasks.size()) {
                throw new DukeException(null, null);
            }
            int index = Integer.parseInt(command[1]);
            String[] tag = new String[command.length - 2];
            for (int i = 0; i < tag.length; i++) {
                tag[i] = command[i+2];
            }
            tasks.updateTag(index - 1, tag);
            storage.saveToDisk(tasks);
            return ui.updateTag(tasks, index - 1);
        } catch (DukeException e) {
            return ui.tagError();
        }
    }
}
