package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class TagCommand extends Command {
    String command;

    /**
     * Class constructor.
     *
     * @param command the task to be tagged.
     */
    public TagCommand(String command) {
        this.command = command;
    }

    /**
     * Adds a tag to a task and returns a "taskTagged" message.
     *
     * @param taskList the list of tasks.
     * @param storage the items read from the file.
     * @param ui methods to be used to interact with the user.
     * @return "taskTagged" message.
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage,  Ui ui) throws DukeException {
        String splitCommand = command.replaceAll("tag ", "");
        int index = Integer.parseInt(splitCommand.substring(0, splitCommand.indexOf(" "))) - 1;
        String tag = splitCommand.substring(splitCommand.indexOf(" ") + 1);

        taskList.tagTask(index, tag);
        return ui.printTagTask(taskList.getTask(index));
    }
}
