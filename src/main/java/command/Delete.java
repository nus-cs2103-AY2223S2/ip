package command;

import gui.Ui;
import runner.Duke;
import task.Task;

public class Delete {
    private final Duke duke;

    public Delete(Duke duke) {
        this.duke = duke;
    }

    /**
     * Actions when deleting.
     * @param arg String format of the Task index.
     * @returns Delete message.
     */
    public String execute(String arg) {
        try {
            int index = Integer.parseInt(arg) - 1;
            assert index < duke.taskList.size() : "Index Invalid";
            Task temp = duke.taskList.getTask(index);
            duke.taskList.remove(index);
            duke.storage.saveList();
            duke.updateDeleted(temp);
            duke.updateInput("delete " + arg);
            return Ui.deleteMSG(temp, duke.taskList.size());
        } catch (IndexOutOfBoundsException a) {
            return "OOPS!!! You can not delete air~";
        } catch (NumberFormatException b) {
            return "OOPS!!! Number Format Wrong";

        }
    }
}
