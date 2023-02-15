package duke.command;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Commands for adding a task.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */

public class AddCommand extends Command {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private String taskType;
    private String taskDesc;
    private String deadlineDue;
    private String from;
    private String by;


    /**
     * Constructor for Todo command.
     *
     * @param taskType the type of task added.
     * @param taskDesc the description of the task.
     */
    public AddCommand(String taskType, String taskDesc) {
        this.taskType = taskType;
        this.taskDesc = taskDesc;
    }

    /**
     * Constructor for Deadline command.
     *
     * @param taskType the type of task added.
     * @param taskDesc the description of the task.
     * @param deadline the deadline of the task.
     */

    public AddCommand(String taskType, String taskDesc, String deadline) {
        this.taskType = taskType;
        this.taskDesc = taskDesc;
        this.deadlineDue = deadline;
    }

    /**
     * Constructor for Event command.
     *
     * @param taskType the type of task added.
     * @param taskDesc the description of the task.
     * @param from     date of the event.
     * @param by       when the event ends.
     */

    public AddCommand(String taskType, String taskDesc, String from, String by) {
        this.taskType = taskType;
        this.taskDesc = taskDesc;
        this.from = from;
        this.by = by;
    }

    /**
     * Create a new Task object.
     * @param tasks
     * @param storage
     * @return Task object
     */
    public Task addToDo(TaskList tasks, Storage storage) {
        Task task = new Todo(taskDesc);
        return task;
    }

    /**
     * Create a new Task object.
     * @param tasks
     * @param storage
     * @return Task object
     */
    public Task addDeadline(TaskList tasks, Storage storage) {
        Task task = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            LocalDateTime dueDate = LocalDateTime.parse(deadlineDue.substring(3), formatter);
            task = new Deadline(taskDesc, dueDate);
            return task;
        } catch (DateTimeException e) {
            System.out.println("ERROR!! Please key in valid date format: dd-MM-yyyy HHmm");
        }
        return task;
    }

    /**
     * Create a new Task object.
     * @param tasks
     * @param storage
     * @return Task object
     */
    public Task addEvent(TaskList tasks, Storage storage) {
        Task task = new Event(taskDesc, from.substring(5), by.substring(3));
        return task;
    }

    /**
     * Add the task into the task list and update the task in the tasks.txt.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = null;

        switch (taskType) {
        case TODO:
            task = addToDo(tasks, storage);
            break;
        case DEADLINE:
            task = addDeadline(tasks, storage);
            break;
        case EVENT:
            task = addEvent(tasks, storage);
            break;
        default:
            break;
        }

        try {
            tasks.addTask(task);
            storage.update(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ui.showAddTaskMsg(task, String.valueOf(tasks.getLength()));
    }

    /**
     * Check if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
