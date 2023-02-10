package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.gui.Ui;
import duke.task.Task;

import java.util.stream.Collectors;
import java.util.List;


/**
 * FindCommand - User enters the find command
 */
public class FindCommand extends Command {

    private String value;

    /**
     * Constructor
     * @param value Inputted by the user to find any tasks containing this value
     */
    public FindCommand(String value) {
        this.value = value;
    }

    /**
     * Finds given value in all the tasks in the TaskList
     * @return the results in a list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.stream()
                .filter(it -> it.getValue().contains( this.value ))
                .collect(Collectors.toList());

        return ui.printList(matches);
    }
}
