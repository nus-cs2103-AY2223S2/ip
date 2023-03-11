package roody.commands;

import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.exceptions.RoodyException;
import roody.tasks.Task;

/**
 * Represents a command to find relevant commands by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a find command.
     * @param keyword Keyword to search for in commands.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws RoodyException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            // Splits by "|"
            String[] words = task.saveTask().split("\\|");
            // Further splits description by whitespace
            String[] desc = words[0].split("\\s");
            for (String word : desc) {
                // Searches for a match to keyword
                if (word.equals(keyword)) {
                    foundTasks.add(task);
                }
            }
        }
        return ui.showFoundTasks(foundTasks);
    }
}
