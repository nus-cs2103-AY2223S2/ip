package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;
import genie.task.Task;

import java.io.IOException;

public class AddCommand extends Command {
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private final static String TODO = "todo";
    private String taskType;
    private String fullCommand;
    private Task task;

    public AddCommand(String taskType, String fullCommand) {
        this.taskType = taskType;
        this.fullCommand = fullCommand;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        switch (taskType) {
        case TODO:
            task = taskList.addToDoFromUser(fullCommand);
            break;
        case DEADLINE:
            task = taskList.addDeadlineFromUser(fullCommand);
            break;
        case EVENT:
            task = taskList.addEventFromUser(fullCommand);
            break;
        }
        int size = taskList.getTasks().size();
        ui.appendAddTaskMessage(task, size);
    }
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
