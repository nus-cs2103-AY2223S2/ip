package duke.commands;

import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;

/**
 * The class for the Event command which extends Command class.
 */
public class EventCommand extends Command {
    private String input;

    /**
     * EventCommand constructor.
     *
     * @param input The user's input.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Checks if the format of the input is valid.
     *
     * @param indexFrom the index of the /from.
     * @param indexTo the index of the /to.
     * @return a boolean
     */
    private boolean checkFormat(int indexFrom, int indexTo) {
        return indexFrom < -1
                || indexFrom - 1 < 6
                || (indexFrom + 6 > indexTo - 1)
                || (indexTo + 4 > this.input.length())
                || (!(this.input.substring(indexFrom, indexFrom + 6).equals("/from ")
                && this.input.substring(indexTo, indexTo + 4).equals("/to ")));
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException{
        try {
            int indexFrom = input.indexOf("/");
            int indexTo = input.lastIndexOf("/");
            String[] words = this.input.split(" ");
            if (words.length <= 1) {
                throw new DukeException(Ui.emptyDescriptionError());
            }
            if (checkFormat(indexFrom, indexTo)) {
                throw new DukeException(Ui.wrongEventCommandFormat());
            }

            Event e = new Event(input.substring(6, indexFrom - 1),
                    input.substring(indexFrom + 6, indexTo - 1),
                    input.substring(indexTo + 4, input.length()));
            tasks.add(e);
            storage.saveTaskList(tasks);
            return Ui.confirmationMessage("added", tasks, e);
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.wrongEventDateFormat());
        }
    }
}
