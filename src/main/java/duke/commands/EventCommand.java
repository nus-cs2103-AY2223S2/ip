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

    private boolean checkFormat(int indexFrom, int indexTo) {
        return (indexFrom + 6 > indexTo - 1)
                || (indexTo + 4 > this.input.length())
                || (!(this.input.substring(indexFrom, indexFrom + 6).equals("/from ")
                && this.input.substring(indexTo, indexTo + 4).equals("/to ")));
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int indexFrom = input.indexOf("/");
            int indexTo = input.lastIndexOf("/");
            String[] words = this.input.split(" ");
            if (words.length <= 1) {
                throw new DukeException(ui.emptyDescriptionError());
            }
            if (checkFormat(indexFrom, indexTo)) {
                throw new DukeException(ui.wrongEventCommandFormat());
            }

            Event e = new Event(input.substring(6, indexFrom - 1),
                    input.substring(indexFrom + 6, indexTo - 1),
                    input.substring(indexTo + 4, input.length()));
            tasks.add(e);
            storage.saveTaskList(tasks);
            return ui.confirmationMessage("added", tasks, e);
        } catch (AssertionError ae) {
            return ae.getMessage();
        } catch (DukeException de) {
            return de.getMessage();
        } catch (DateTimeParseException date_time_e) {
            return ui.wrongEventDateFormat();
        }
    }
}
