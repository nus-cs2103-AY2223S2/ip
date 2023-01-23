package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;
import sebastian.sebastianExceptions.LackOfArgumentException;

/**
 * Class representing a find command
 */
public class FindCommand extends Command{

    private final String instruction;

    public FindCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Find tasks which contains user specified keyword as part of its task description
     * @param taskList TaskList instance created at the start of the session
     * @param ui Ui instance created at the start of the session
     * @param storage Storage instance created at the start of the session
     * @throws LackOfArgumentException when user did not specify a keyword
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LackOfArgumentException {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        } else {
            String keyword = instruction.substring(5).trim();
            ui.printFormattedString(taskList.findTasks(keyword).toString());
        }
    }
}
