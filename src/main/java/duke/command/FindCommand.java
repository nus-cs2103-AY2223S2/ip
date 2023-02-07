package duke.command;

import duke.Task;
import duke.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void initCommand(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.find(keyword);
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks, please try again!");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:");
            int count = 1;
            for (Task t : matchingTasks) {
                System.out.println(count++  + "." + t.toString());
            }
        }
    }
}
