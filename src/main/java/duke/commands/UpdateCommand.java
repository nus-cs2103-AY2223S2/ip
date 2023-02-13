package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Task;

/**
 * The class for the Update command which extends Command class.
 */
public class UpdateCommand extends Command {
    private String input;

    /**
     * TodoCommand constructor.
     *
     * @param input The user's input.
     */
    public UpdateCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            String[] words = this.input.split(" ");
            int index = Integer.parseInt(words[1]);
            if (index > tasks.size() || index <= 0) {
                throw new DukeException(Ui.insufficientTasksMessage());
            }
            Task t = tasks.get(index - 1);

            if (words[2].equals("/description")) {
                t.update(this.input.split("/description")[1].trim());
            } else if (words[2].equals("/deadline") && (t instanceof Deadline)) {
                Deadline d = (Deadline) t;
                d.updateDeadline(this.input.split("/deadline")[1].trim());
            } else {
                throw new DukeException(Ui.wrongUpdateFormat());
            }
            storage.saveTaskList(tasks);
            return Ui.confirmationMessage("updated", tasks, t);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Ui.wrongUpdateFormat());
        }
    }
}
