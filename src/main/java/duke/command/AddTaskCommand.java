package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class AddTaskCommand extends Command {
    public AddTaskCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task newTask = Parser.translateUserInputToTask(userInput);
        tasks.addTask(newTask);
    }
}
