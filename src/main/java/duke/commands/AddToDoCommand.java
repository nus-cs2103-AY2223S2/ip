package duke.commands;

import duke.exceptions.DukeException;
import duke.ui.Ui;
import duke.storage.*;
import duke.tasks.*;

import java.io.IOException;

public class AddToDoCommand extends Command{

    private String userInput;

    public AddToDoCommand(String userInput) {
        this.userInput = userInput;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        String[] inputs = userInput.split(" ");
        if (inputs.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String taskName = userInput.split(" ", 2)[1];
        ToDo userTask = new ToDo(taskName);
        tasks.addTask(userTask);
        try {
            storage.appendToFile(storage.getFilePath(), "T | 0 | " + taskName + "\n");
        } catch(IOException e) {
            throw new DukeException("Error writing to file");
        }
        ui.showToUser("Got it. I've added this task: \n    " + userTask + "\nNow you have " + tasks.getSize() + " duke.tasks in the list.");
    }
}
