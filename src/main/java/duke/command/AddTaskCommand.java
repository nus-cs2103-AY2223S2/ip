package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

public class AddTaskCommand extends Command {
    public AddTaskCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        int taskCount = tasks.getSize() + 1;
        String taskWord = (taskCount == 1) ? "task" : "tasks";
        Task newTask = Parser.translateUserInputToTask(userInput);
        tasks.addTask(newTask);
        return ("Got it. I've added this task:\n   "
                + newTask
                + "\nNow you have " + taskCount + " " + taskWord + " in your list\n");
    }
}
