package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a list command.
 */
public class CommandList extends Command {
    /**
     * Returns a list command.
     * @param command full unparsed command.
     */
    public CommandList(String command) {
        super(command);
    }

    /**
     * Prints the task list.
     * @param taskList contains the task list.
     * @param ui deals with interactions with the user.
     * @param storage deals with loading and saving tasks from file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        for (int i = 0; i < taskList.getLength(); i++) {
            System.out.printf("%d.%s\n", i + 1, taskList.getTask(i).toString());
        }
        System.out.println();
    }
}
