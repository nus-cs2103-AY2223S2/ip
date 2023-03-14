package duke.commands;

import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;

/**
 * The class for the Deadline command which extends Command class.
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * DeadlineCommand constructor.
     *
     * @param input The user's input.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Checks if the format of the input is valid.
     *
     * @param indexBy index of the /by in the input.
     * @return a boolean
     */
    private boolean checkFormat(int indexBy) {
        return indexBy < 0
                || indexBy - 1 < 9
                || indexBy + 4 > input.length()
                || !input.substring(indexBy, indexBy + 4).equals("/by ");
    }
    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            int indexBy = input.indexOf("/");
            String[] words = this.input.split(" ");
            if (words.length <= 1) {
                throw new DukeException(Ui.emptyDescriptionError());
            }
            if (checkFormat(indexBy)) {
                throw new DukeException(Ui.wrongDeadlineCommandFormat());
            }
            Deadline d = new Deadline(input.substring(9, indexBy - 1),
                    input.substring(indexBy + 4, input.length()));

            tasks.add(d);
            storage.saveTaskList(tasks);
            return Ui.confirmationMessage("added", tasks, d);
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.wrongDeadlineDateFormat());
        }
    }
}
