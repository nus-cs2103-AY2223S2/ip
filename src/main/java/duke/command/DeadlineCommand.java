package duke.command;

import java.time.LocalDateTime;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.UI.UI;
import duke.UI.Parser;

public class DeadlineCommand extends AddCommand {
    public static final String COMMAND = "deadline";
    private String[] index;

    public DeadlineCommand(String[] index) {
        this.index = index;
    }

    @Override
    public void runCommand(TaskList taskList, UI ui) {
        String taskDescription = index[1];
        LocalDateTime by = Parser.parseDateTime(index[2]);
        Task taskToAdd = new Deadline(taskDescription, by);
        taskList.addTask(taskToAdd);
        ui.printResponse("Got it. I have added this task: \n" + taskToAdd);
        ui.printResponse("Now you have " + taskList.numberOfTasks() + " tasks in your list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
