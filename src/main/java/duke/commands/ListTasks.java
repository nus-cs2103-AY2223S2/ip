package duke.commands;

import java.util.stream.Collectors;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

/**
 * Command to list all tasks.
 *
 * @author Samarth Verma
 */
public class ListTasks extends Command {

    @Override
    public void execute(TaskList list, UserInterface ui, Storage storage) {
        if (list.stream().count() == 0) {
            ui.showMessage("You have no tasks. Enjoy your free time!");
            return;
        }

        String tasks = list.stream()
                .map(task -> (list.indexOf(task) + 1) + ". " + task)
                .collect(Collectors.joining("\n"));

        ui.showMessage("Here are the tasks in your list:\n" + tasks);
    }
}