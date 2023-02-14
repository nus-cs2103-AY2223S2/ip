package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;
import genie.task.Task;

public class AddCommand extends Command {
    private String taskType;
    private String fullCommand;
    private Task task;
    public AddCommand(String taskType, String fullCommand) { //todo javadoc for command package
        this.taskType = taskType;
        this.fullCommand = fullCommand;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (taskType) {
        case "todo": // todo change to constant
            task = taskList.addToDoFromUser(fullCommand);
            break;
        case "deadline":
            task = taskList.addDeadlineFromUser(fullCommand);
            break;
        case "event":
            task = taskList.addEventFromUser(fullCommand);
            break;
        }
        ui.appendAddTaskMessage(task, taskList.getTasks().size());
    }
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
