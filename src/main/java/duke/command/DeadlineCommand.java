package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.task.Deadline;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that adds a deadline to the task list.
 */
public class DeadlineCommand extends Command {
    private final Deadline task;

    /**
     * Represents a constructor for a DeadlineCommand object.
     *
     * @param taskName Name of the task.
     * @param byTime Deadline of the task.
     */
    public DeadlineCommand(String taskName, LocalDateTime byTime) {
        this.task = new Deadline(taskName, byTime);
    }

    /**
     * Returns the command as a String which is used to show to the user in the GUI.
     * Executes the command and adds a deadline to the task list.
     *
     * @param tl TaskList which the Duke will modify.
     * @param ui Ui to be used to facilitate interactions between user and the CLI.
     * @param storage Storage to be used to handle interactions with the save file.
     * @throws DukeException If there is an error in executing the command.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        tl.add(task);
        String toShow = "Meowww, I've added this task:\nAdded: " + task + "\n";
        toShow += ui.stringOfTaskNumbers(tl);
        ui.showToUser(toShow);

        try {
            storage.update(tl);
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
            throw new DukeException("Problem with updating in deadline");
        }
        return toShow;
    }
}
