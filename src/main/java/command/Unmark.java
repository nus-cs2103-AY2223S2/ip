package command;

import gui.Ui;
import runner.Duke;

public class Unmark {
    private final Duke duke;

    /**
     * Constructor for Unmark.
     * @param duke
     */
    public Unmark (Duke duke) {
        this.duke = duke;
    }

    /**
     * Actions when unmarking.
     * @param arg Index of a task.
     * @return Message shown.
     */
    public String execute(String arg) {
        try {
            int index = Integer.parseInt(arg) - 1;
            assert index < duke.taskList.size() : "Index Invalid";
            duke.taskList.getTask(index).uncomplete();
            duke.storage.saveList();
            duke.updateInput("unmark " + arg);
            return Ui.unmarkMSG(duke.taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            return "Index Out";
        }
    }
}
