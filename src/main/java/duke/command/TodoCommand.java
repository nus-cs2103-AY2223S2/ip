package duke.command;

import duke.taskstorage.TaskList;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Class for TodoCommand.
 */
public class TodoCommand extends Command {

    /**
     * Constructor for TodoCommand.
     * @param userInput User input.
     */
    public TodoCommand(String userInput) {
        super(userInput);
    }

    /**
     * Extracts todo task name from user input.
     * @param input User input.
     * @return Todo task name in String format.
     */
    public String getTodoName(String input) {
        String name = "";
        try {
            name = input.substring(5);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops! Please enter a valid todo task format.\n");
        }
        return name;
    }

    /**
     * Translates user input to ToDo task.
     * @param input User input.
     * @return ToDo task.
     */
    public Task translateInput(String input) {
        return new ToDo(getTodoName(input));
    }

    /**
     * Executes user input and adds ToDo task in current TaskList.
     * @param tasks Current TaskList.
     * @return Message to inform user that ToDo task has been added.
     */
    @Override
    public String execute(TaskList tasks) {
        int taskCount = tasks.getSize() + 1;
        String taskWord = (taskCount == 1) ? "task" : "tasks";
        Task newTask = translateInput(userInput);
        tasks.addTask(newTask);
        return Ui.addTaskMessage(newTask, taskCount, taskWord);
    }
}
