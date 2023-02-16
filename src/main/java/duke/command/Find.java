package duke.command;

import duke.TaskList;
import duke.Ui;

public class Find extends Command {

    public Find(String input) {
        super(input);
    }

    /**
     * Searches a TaskList and prints out a list of tasks containing the queried term in a find command.
     *
     * @param tasks The current TaskList.
     * @return The current TaskList.
     */
    public TaskList execute(TaskList tasks) {
        String searchFor = input.substring(5);
        TaskList found = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(searchFor)) {
                found.add(tasks.get(i));
            }
        }
        Ui.findMessage(searchFor, found);
        return tasks;
    }
}
