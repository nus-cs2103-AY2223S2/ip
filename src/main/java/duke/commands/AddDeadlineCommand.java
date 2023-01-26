package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class AddDeadlineCommand extends Command{

    private String userInput;

    public AddDeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        String[] inputs = userInput.split(" ");
        if (inputs.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] taskNameAndDeadline = userInput.split(" ", 2)[1].split(" /by ");
        if (taskNameAndDeadline.length < 2) {
            throw new DukeException("☹ OOPS!! The date of a deadline cannot be empty.");
        }
        String taskName = taskNameAndDeadline[0];
        String deadline = taskNameAndDeadline[1];
        Deadline userTask = new Deadline(taskName, LocalDate.parse(deadline));
        tasks.addTask(userTask);
        try {
            storage.appendToFile(storage.getFilePath(), "D | 0 | " + taskName + " | " + deadline + "\n");
        } catch(IOException e) {
            throw new DukeException("Error writing to file");
        }
        ui.showToUser("Got it. I've added this task: \n    " + userTask + "\nNow you have " + tasks.getSize() + " duke.tasks in the list.");
    }
}
