package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Add command which adds tasks to the TaskList.
 */
public class Add extends Command {
    private String taskType;

    /**
     * Constructor for Add
     *
     * @param input The user input.
     * @param taskType The type of task being added.
     */
    public Add(String input, String taskType) {
        super(input);
        this.taskType = taskType;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param tasks The current Task List.
     * @return The modified Task List.
     */
    public TaskList execute(TaskList tasks) throws DukeException {
        switch(taskType) {
            case "todo":
                tasks.add(newToDo(input, tasks.size() + 1));
                break;
            case "deadline":
                tasks.add(newDeadline(input, tasks.size() + 1));
                break;
            case "event":
                tasks.add(newEvent(input, tasks.size() + 1));
                break;
        }
        return tasks;
    }

    /**
     * Creates a new ToDo task and prints a message for the user
     * confirming the addition of the task to the Task List.
     *
     * @param input The user input.
     * @param taskSize The current Task List size.
     * @return The created task.
     */
    public Task newToDo(String input, int taskSize) {
        Task task = new ToDo(input.substring(5));
        Ui.addMessage(task, taskSize);
        return task;
    }

    /**
     * Creates a new Deadline task and prints a message for the user
     * confirming the addition of the task to the Task List.
     *
     * @param input The user input.
     * @param taskSize The current Task List size.
     * @return The created task.
     */
    public Task newDeadline(String input, int taskSize) throws DukeException {
        int index = input.indexOf(" /by ");
        Task task = new Deadline(input.substring(9, index), input.substring(index + 5));
        Ui.addMessage(task, taskSize);
        return task;
    }

    /**
     * Creates a new Event task and prints a message for the user
     * confirming the addition of the task to the Task List.
     *
     * @param input The user input.
     * @param taskSize The current Task List size.
     * @return The created task.
     */
    public Task newEvent(String input, int taskSize) throws DukeException {
        int fromIndex = input.indexOf(" /from ");
        int toIndex = input.indexOf(" /to ");
        Task task = new Event(input.substring(6, fromIndex), input.substring(fromIndex + 7, toIndex),
                input.substring(toIndex + 5));
        Ui.addMessage(task, taskSize);
        return task;
    }

}

