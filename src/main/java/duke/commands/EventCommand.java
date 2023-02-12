package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;
import duke.tasks.Task;

import java.time.DateTimeException;

public class EventCommand extends Command {
    private String input;
    private TaskList listOfTasks;

    public EventCommand(String input, TaskList listOfTasks) {
        this.input = input;
        this.listOfTasks = listOfTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String handleCommand() {
        try {
            String[] inputArgs = splitArgs(input);
            Task currentTask = new Event(inputArgs[0], inputArgs[1], inputArgs[2]);
            listOfTasks.addTask(currentTask);
            return Ui.showAdd(this.listOfTasks, currentTask.toString());
        } catch (DateTimeException e) {
            return Ui.showError(e);
        }
    }
    
    private String[] splitArgs(String input) {
        String[] inputArgs = input.split(" ", 2);
        inputArgs = inputArgs[1].split(" /from ", 2);
        String description = inputArgs[0];
        inputArgs = inputArgs[1].split(" /to ", 2);
        String from = inputArgs[0];
        String to = inputArgs[1];
        String[] result = {description, from, to};
        return result;
    }
}
