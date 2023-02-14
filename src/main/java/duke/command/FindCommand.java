package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that finds tasks using a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Represents a constructor for a FindCommand object that exits the program.
     *
     * @param keyword The keyword the FindCommand is searching for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the command as a String which is used to show to the user in the GUI.
     * Executes the find command by iterating through the task list.
     * Finds matching tasks based on the keyword given.
     *
     * @param tl TaskList which the Duke will modify.
     * @param ui Ui to be used to facilitate interactions between user and the CLI.
     * @param storage Storage to be used to handle interactions with the save file.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
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
        return toShow;
    }
}
