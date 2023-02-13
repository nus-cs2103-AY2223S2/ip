package sebastian.command;

import sebastian.exceptions.LackOfArgumentException;
import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle a command to find tasks based on a keyword
 */
public class FindCommand extends Command {

    private final String instruction;

    public FindCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Find tasks which contains user specified keyword as part of its task description
     * @param taskList TaskList instance created at the start of the session
     * @param ui Ui instance created at the start of the session
     * @param storage Storage instance created at the start of the session
     * @return a string representing the result of task execution
     * @throws LackOfArgumentException when user did not specify a keyword
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LackOfArgumentException {
        String[] insArr = instruction.split(" ");
        if (insArr.length == 1) {
            throw new LackOfArgumentException("Please specify a search keyword");
        } else if (insArr.length > 1) {
            String keyword = instruction.substring(5).trim();
            String res = taskList.findTasks(keyword).toString();
            return ui.getFormattedString(res);
        } else {
            throw new Error("Internal Error");
        }
    }
}
