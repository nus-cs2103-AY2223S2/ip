package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class AddEventCommand extends Command{

    private String userInput;

    public AddEventCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        String[] inputs = userInput.split(" ");
        if (inputs.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] taskNameAndDate = userInput.split(" ", 2)[1].split(" /from ");
        if (taskNameAndDate.length < 2) {
            throw new DukeException("☹ OOPS!!! The from date of an event cannot be empty.");
        }
        String taskName = taskNameAndDate[0];
        String[] toAndFrom = taskNameAndDate[1].split(" /to ");
        if (toAndFrom.length < 2) {
            throw new DukeException("☹ OOPS!!! The to date of an event cannot be empty.");
        }
        String from = toAndFrom[0];
        String to = toAndFrom[1];
        Event userTask = new Event(taskName, LocalDate.parse(from), LocalDate.parse(to));
        tasks.addTask(userTask);
        try {
            storage.appendToFile(storage.getFilePath(), "E | 0 | " + taskName + " | " + from + " | " + to + "\n");
        } catch(IOException e) {
            throw new DukeException("Error writing to file");
        }
        ui.showToUser("Got it. I've added this task: \n    " + userTask + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
