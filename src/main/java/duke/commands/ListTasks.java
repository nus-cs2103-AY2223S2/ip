package duke.commands;

import java.util.stream.Collectors;

import duke.tasks.TaskList;
import duke.ui.UserInterface;

public class ListTasks extends Command {

    @Override
    public void execute(TaskList list, UserInterface ui) {
        String message = list.stream().map(t -> String.format("%d. %s", t.id(), t)).collect(Collectors.joining("\n"));
        ui.showMessage(message);
    }
}
