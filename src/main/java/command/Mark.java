package command;

import gui.Ui;
import runner.Duke;

public class Mark {
    private final Duke duke;

    public Mark(Duke duke) {
        this.duke = duke;
    }

    public String execute(String arg) {
        try {
            int index = Integer.parseInt(arg) - 1;
            assert index < duke.taskList.size() : "Index Invalid";
            duke.taskList.getTask(index).complete();
            duke.storage.saveList();
            duke.updateInput("mark " + arg);
            return Ui.markMSG(duke.taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            return "Index Out";
        }
    }
}
