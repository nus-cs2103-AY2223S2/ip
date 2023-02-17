package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * FindCommand finds all the tasks in the tasks list with the given keyword.
 */
public class FindCommand extends Command {

    private static Integer START_INDEX_OF_DESCRIPTION = 5;

    /**
     * Creates a FindCommand.
     *
     * @param textCmd user input.
     */
    public FindCommand(String textCmd) {
        super(textCmd);
    }

    /**
     * Produces a String for the GUI to read from.
     * The String is the list of Tasks that contain that specific given keyword
     *
     * @param ui User Interface of Duke.
     * @param storage storage of Duke.
     * @param taskList task list containing all the tasks Duke is tracking.
     * @return the String of all the tasks that contains the keyword.
     * @throws DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        String[] commandSplit = textCmd.split(" ");
        String typeOfCommand = commandSplit[0];

        if (!typeOfCommand.equals("find")) {
            throw new DukeException("The Formatting of your Find Command Task is incorrect!\n"
                    + "Please follow this format: find {keyword}");
        }

        if (commandSplit.length == 1) {
            throw new DukeException("The keyword for your Find Command cannot be blank!\n"
                    + "Please follow this format: find {keyword}");
        }

        String keyWord = textCmd.substring(START_INDEX_OF_DESCRIPTION);
        return ui.printTaskByKeyWord(taskList, keyWord);
    }
}
