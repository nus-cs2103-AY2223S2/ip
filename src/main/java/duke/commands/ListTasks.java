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
        String message = list.stream()
                .map(t -> String.format("%d. %s", t.id(), t))
                .collect(Collectors.joining("\n"));
    }
}
