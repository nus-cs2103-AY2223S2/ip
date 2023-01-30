package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            boolean hasKeyword = t.toString().contains(this.keyword);
            if (hasKeyword) {
                matchingTasks.add(t);
            }
        }

        if (matchingTasks.size() > 0) {
            ui.showMessage("I've found the following matching task(s) in your list!");
            ui.showMessageWithoutNewline(matchingTasks.print());
        } else {
            ui.showMessage("Oh no, I couldn't find any matching tasks! :(");
        }
    }
}
