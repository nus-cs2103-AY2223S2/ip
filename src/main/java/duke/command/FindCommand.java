package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ArrayList<Task> matchTasks = new ArrayList<>();
        ArrayList<Integer> matchIndex = new ArrayList<>();
        for (int i = 0; i < tl.size(); i++) {
            Task t = tl.get(i);
            if (t.getTaskName().contains(keyword)) {
                matchTasks.add(t);
                matchIndex.add(i + 1);
            }
        }
        String toShow;
        if (matchTasks.isEmpty()) {
            toShow = "Meowww, there is not any tasks that matches your keyword";
        } else {
            toShow = "Meowww, here are the tasks you asked for:\n";
            for (int i = 0; i < matchTasks.size(); i++) {
                toShow += String.format("%s. %s", matchIndex.get(i), matchTasks.get(i));
                if (i + 1 < matchTasks.size()) {
                    toShow += "\n";
                }
            }
        }
        ui.showToUser(toShow);
    }
}
