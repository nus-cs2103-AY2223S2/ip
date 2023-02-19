package fideline.execution;


import fideline.save.Storage;
import fideline.task.TaskManager;
import fideline.user.Ui;




/**
 * Shows the user a list of all existing tasks that have
 * descriptions that contain the given input.
 *
 * @author Fun Leon
 */


public class FindCommand extends Command {


    private String findString;
    public FindCommand(String findString) {
        assert findString.equals("") : "find command should not allow empty args";
        this.findString = findString;
    }


    /**
     * Fetches list of all existing tasks from the taskmanager that contain
     * the given string.
     *
     * @param taskManager Manager for existing tasks and creation of new ones.
     * @param storage     Handler for storage of existing tasks locally.
     * @param ui          Handler for display messages to the user.
     */
    @Override
    public String execute(TaskManager taskManager, Storage storage, Ui ui) {
        String s = taskManager.findTask(findString);
        if (s.equals("")) {
            return ui.getEmptyFindMsg();
        } else {
            return ui.getFindMsg(s);
        }
    }
}
