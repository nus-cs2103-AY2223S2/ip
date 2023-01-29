package command;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

public class Find implements Command {
    private String regex = "";

    public Find(String regex) {
        this.regex = regex;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        int matches = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasMatchingObjective(regex)) matches++;
        }
        ui.print("here's your " + matches + (matches == 1 ? " match:" : " matches:"));
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasMatchingObjective(regex)) {
                ui.print(Integer.toString(i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
