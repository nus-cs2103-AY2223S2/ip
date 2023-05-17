package command;

import gui.Ui;
import runner.Riddle;

public class Unmark {
    private final Riddle riddle;

    /**
     * Constructor for Unmark.
     * @param riddle
     */
    public Unmark (Riddle riddle) {
        this.riddle = riddle;
    }

    /**
     * Actions when unmarking.
     * @param arg Index of a task.
     * @return Message shown.
     */
    public String execute(String arg) {
        try {
            int index = Integer.parseInt(arg) - 1;
            assert index < riddle.taskList.size() : "Index Invalid";
            riddle.taskList.getTask(index).uncomplete();
            riddle.storage.saveList();
            riddle.updateInput("unmark " + arg);
            return Ui.unmarkMSG(riddle.taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            return "Index Out";
        }
    }
}
