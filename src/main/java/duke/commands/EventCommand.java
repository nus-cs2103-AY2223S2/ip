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
            String[] inputArgs = input.split(" ", 2);
            inputArgs = inputArgs[1].split(" /from ", 2);
            String description = inputArgs[0];
            inputArgs = inputArgs[1].split(" /to ", 2);
            String from = inputArgs[0];
            String to = inputArgs[1];
            Task currentTask = new Event(description, from, to);
            listOfTasks.add(currentTask);
            return Ui.showAdd(this.listOfTasks, currentTask.toString());
        } catch (DateTimeException e) {
            return Ui.showError(e);
        }
    }
}
