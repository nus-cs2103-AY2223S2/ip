package command;

import gui.Ui;
import runner.Riddle;

public class Mark {
    private final Riddle riddle;

    /**
     * Constructor for Mark.
     * @param riddle
     */
    public Mark(Riddle riddle) {
        this.riddle = riddle;
    }

    /**
     * Actions when marking.
     * @param arg Index of a task.
     * @return Message shown.
     */
    public String execute(String arg) {
        try {
            int index = Integer.parseInt(arg) - 1;
            assert index < riddle.taskList.size() : "Index Invalid";
            riddle.taskList.getTask(index).complete();
            riddle.storage.saveList();
            riddle.updateInput("mark " + arg);
            return Ui.markMSG(riddle.taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            return "Index Out";
        }
    }
}
