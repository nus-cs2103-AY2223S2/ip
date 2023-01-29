package duke.command;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
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
    private String deadline;
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
        this.deadline = deadline;
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
     * Add the task into the task list and update the task in the tasks.txt.
     *
     * @param tasks
     * @param ui
     * @param storage
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (taskType) {
        case TODO:
            try {
                Todo todo = new Todo(taskDesc);
                tasks.addTask(todo);
                ui.showAddTaskMsg(todo, String.valueOf(tasks.getLength()));
                storage.update(todo);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                break;
            }
        case DEADLINE:
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime dueDate = LocalDateTime.parse(deadline.substring(3), formatter);
                Deadline deadline = new Deadline(taskDesc, dueDate);
                tasks.addTask(deadline);
                ui.showAddTaskMsg(deadline, String.valueOf(tasks.getLength()));
                storage.update(deadline);
            } catch (DateTimeException e) {
                System.out.println("ERROR!! Please key in valid date format: dd-MM-yyyy HHmm");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                break;
            }
        case EVENT:
            try {
                Event event = new Event(taskDesc, from.substring(5), by.substring(3));
                tasks.addTask(event);
                ui.showAddTaskMsg(event, String.valueOf(tasks.getLength()));
                storage.update(event);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                break;
            }
        default:
            break;
        }
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
