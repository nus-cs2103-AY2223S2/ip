package commands;

import java.util.ArrayList;
import java.util.Collections;

import files.Storage;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;
/**
 * Represents a command to sort Deadline tasks only.
 */
public class SortDeadlinesCommand extends Command {
    public SortDeadlinesCommand() {
        super();
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Deadline> deadlines = taskList.getDeadlineTasks();
        System.out.println(deadlines);
        Collections.sort(deadlines);
        System.out.println(deadlines);
        TaskList filteredDeadlines = new TaskList(deadlines);
        System.out.println(filteredDeadlines.listItems());
        return filteredDeadlines.listItems();
    }
}
