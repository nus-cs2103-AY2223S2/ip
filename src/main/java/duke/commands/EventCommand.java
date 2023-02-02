package duke.commands;

import java.util.Arrays;
import duke.exceptions.DukeInvalidEventCommandException;
import duke.tasks.EventTask;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * The {@code Command} class for the {@code event} command.
 */
public class EventCommand extends Command {

    private final String[] tokens;

    /**
     * Instantiates a new {@code EventCommand} object.
     *
     * @param tokens The array of strings generated from tokenising the user's input.
     */
    public EventCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * {@inheritDoc}
     *
     * @throws DukeInvalidEventCommandException If the {@code event} command is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidEventCommandException {

        int indexOfFrom = -1;
        int indexOfTo = -1;

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.equals("/from")) {
                indexOfFrom = i;
            } else if (token.equals("/to")) {
                indexOfTo = i;
            }
        }

        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new DukeInvalidEventCommandException();
        }

        String[] taskNameArray = Arrays.copyOfRange(tokens, 1, indexOfFrom);
        String[] fromArray = Arrays.copyOfRange(tokens, indexOfFrom + 1, indexOfTo);
        String[] toArray = Arrays.copyOfRange(tokens, indexOfTo + 1, tokens.length);

        if (taskNameArray.length == 0 || fromArray.length == 0 || toArray.length == 0) {
            throw new DukeInvalidEventCommandException();
        }

        String taskName = String.join(" ", taskNameArray);
        String from = String.join(" ", fromArray);
        String to = String.join(" ", toArray);

        EventTask newEventTask = new EventTask(taskName, from, to);

        taskList.addTask(newEventTask);
        ui.showMessage("Added:\n" + newEventTask);
        ui.showNumberOfTasks(taskList.getSize());
        storage.saveTaskList(taskList);
    }

    public boolean isByeCommand() {
        return false;
    }
}
