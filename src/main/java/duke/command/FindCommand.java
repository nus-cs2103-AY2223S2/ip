package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    public FindCommand(String textCmd) {
        super(textCmd);
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        String[] commandSplit = textCmd.split(" ");
        String typeOfCommand = commandSplit[0];

        if (!typeOfCommand.equals("find")) {
            throw new DukeException("The Formatting of your Find Command Task is incorrect!\n"
                    + "Please follow this format: find {keyword}");
        }

        if (commandSplit.length == 1) {
            throw new DukeException("The keyword for your Find Command cannot be blank!\n");
        }

        String keyWord = textCmd.substring(5);
        return ui.printTaskByKeyWord(taskList, keyWord);
    }
}
