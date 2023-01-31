package duke.command;

import duke.*;
import duke.task.Task;

public class AddTaskCommand extends Command {
    public AddTaskCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task newTask = Parser.translateUserInputToTask(userInput);
        tasks.addTask(newTask);
    }
}
