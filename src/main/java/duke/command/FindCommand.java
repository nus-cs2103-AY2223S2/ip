package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.Ui;
import duke.Task;

import java.util.stream.Collectors;
import java.util.List;


/**
 * FindCommand - User enters the find command
 */
public class FindCommand extends Command {

    private String value;

    public FindCommand(String value) {
        this.value = value;
    }

    /**
     * Finds given value in all the tasks in the TaskList
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.stream()
                .filter(it -> it.getValue().contains( this.value ))
                .collect(Collectors.toList());

        return ui.printList(matches);
    }
}
