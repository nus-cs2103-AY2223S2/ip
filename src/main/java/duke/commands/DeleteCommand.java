package duke.commands;

import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private String input;
    private TaskList listOfTasks;

    public DeleteCommand(String input, TaskList listOfTasks) {
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
            int index = Integer.parseInt(inputArgs[1]);
            return Ui.showDelete(listOfTasks.deleteTask(index), listOfTasks);
        } catch (Exception e) {
            return Ui.showError(e);
        }
    }
}