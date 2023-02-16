package jeno.command;

import jeno.exception.JenoException;
import jeno.storage.Note;
import jeno.storage.Storage;
import jeno.storage.TaskList;
import jeno.task.Task;
import jeno.task.ToDo;
import jeno.ui.Ui;

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
    public String getTodoName(String input) throws JenoException {
        String name = "";
        try {
            name = input.substring(5);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new JenoException("Oops! Please enter a valid todo task format.\n");
        }
        return name;
    }

    /**
     * Translates user input to ToDo task.
     * @param input User input.
     * @return ToDo task.
     */
    public Task translateInput(String input) throws JenoException {
        return new ToDo(getTodoName(input));
    }

    /**
     * Executes user input and adds ToDo task in current TaskList.
     * @param tasks Current TaskList.
     * @param notes Current Note.
     * @return Message to inform user that ToDo task has been added.
     */
    @Override
    public String execute(TaskList tasks, Note notes) throws JenoException {
        int taskCount = tasks.getSize() + 1;
        String taskWord = (taskCount == 1) ? "task" : "tasks";
        Task newTask = translateInput(userInput);
        tasks.addTask(newTask);
        Storage.saveTasksToTaskLog(tasks);
        return Ui.addTaskMessage(newTask, taskCount, taskWord);
    }
}
