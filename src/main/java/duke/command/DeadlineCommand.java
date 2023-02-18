package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.Task;
import duke.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends Command {
    String command;

    /**
     * Class constructor.
     *
     * @param command the task to be added in the list.
     */
    public DeadlineCommand(String command) {
        this.command = command;
    }

    /**
     * Adds a new deadline task to the list and returns a "taskAdded" message.
     *
     * @param taskList the list of tasks.
     * @param storage the items read from the file.
     * @param ui methods to be used to interact with the user.
     * @return "taskAdded" message.
     * @throws DukeException if input is wrong.
     */
    public String execute(TaskList taskList, Storage storage,  Ui ui) throws DukeException {
        try {
            Task newTask;
            String description = command.substring(command.indexOf(" ") + 1, command.indexOf("/"));
            String stringDate = command.substring(command.indexOf("/by") + 4);

            if (description.equals("")) {
                throw new DukeException("OOPS!!! The description of a deadline task cannot be empty.");
            } else if (stringDate.equals("")) {
                throw new DukeException("Please specify the time the time period for this task.");
            } else {
                try {
                    LocalDateTime by = LocalDateTime.parse(stringDate, DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
                    newTask = new Deadline(description, by);
                } catch (Exception e){
                    throw new DukeException("The time period must be in the format: 'HH:mm dd-MM-yyyy'");
                }

            }

            taskList.addTask(newTask);
            return ui.showTaskAdded(newTask);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}