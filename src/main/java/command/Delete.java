package command;

import gui.Ui;
import runner.Riddle;
import task.Task;

public class Delete {
    private final Riddle riddle;

    /**
     * Constructor for Delete.
     * @param riddle
     */
    public Delete(Riddle riddle) {
        this.riddle = riddle;
    }

    /**
     * Actions when deleting.
     * @param arg String format of the Task index.
     * @returns Delete message.
     */
    public String execute(String arg) {
        try {
            int index = Integer.parseInt(arg) - 1;
            assert index < riddle.taskList.size() : "Index Invalid";
            Task temp = riddle.taskList.getTask(index);
            riddle.taskList.remove(index);
            riddle.storage.saveList();
            riddle.updateDeleted(temp);
            riddle.updateInput("delete " + arg);
            return Ui.deleteMSG(temp, riddle.taskList.size());
        } catch (IndexOutOfBoundsException a) {
            return "OOPS!!! You can not delete air~";
        } catch (NumberFormatException b) {
            return "OOPS!!! Number Format Wrong";

        }
    }
}
