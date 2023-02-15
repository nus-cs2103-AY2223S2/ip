package duke.backend;

import static com.sun.javafx.application.PlatformImpl.exit;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a duke.backend.DukeCommand object that represents a command.
 */
public enum DukeCommand {
    BYE("bye"),
    LIST("list"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find"),
    MARK("mark"),
    UNMARK("unmark"),
    INVALID("invalid");

    /**
     * the command.  For example, the command of the command "to-do read book" is "to-do".
     */
    private final String command;

    /**
     * the description of the command.  For example, the description of the command "to-do read book" is "read book".
     */
    private String description;

    DukeCommand(String command) {
        this.command = command;
        this.description = "";
    }

    /**
     * @param description the description of the command.
     */
    private void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param input the user input.
     * @return a DukeCommand object. If the command is invalid, returns DukeCommand.INVALID.
     */
    public static DukeCommand getCommand(String input) {
        for (DukeCommand c : DukeCommand.values()) {
            if (c.getCommand().equals(input.split(" ")[0])) {
                if (c != LIST && c != BYE) {
                    c.setDescription(input.split(" ", 2)[1].trim());
                }
                return c;
            }
        }
        return INVALID;
    }

    /**
     * Executes the command and returns the message to display to user.
     * @param taskList the task list.
     * @param storage the storage.
     * @return the output message.
     */
    public String execute(TaskList taskList, Storage storage) {
        switch (this) {
        case BYE:
            exit();
            return ResponseGenerator.goodbyeMessage();
        case LIST:
            return ResponseGenerator.taskListToMessage(taskList);
        case DELETE:
            int taskToDelete = Integer.parseInt(this.description) - 1;
            try {
                Task deletedTask = taskList.deleteTask(taskToDelete);
                String response = ResponseGenerator.printTaskDeleted(deletedTask, taskList.getSize());
                storage.save(taskList);
                return response;
            } catch (DukeException e) {
                return ResponseGenerator.dukeExceptionMessage(e);
            }
        case TODO:
            try {
                ToDo todo = Parser.parseTodo(this.description);
                taskList.addTask(todo);
                String response = ResponseGenerator.printTaskAdded(todo, taskList.getSize());
                storage.save(taskList);
                return response;
            } catch (DukeException e) {
                return ResponseGenerator.dukeExceptionMessage(e);
            }
        case DEADLINE:
            try {
                Deadline deadline = Parser.parseDeadline(this.description);
                taskList.addTask(deadline);
                String response = ResponseGenerator.printTaskAdded(deadline, taskList.getSize());
                storage.save(taskList);
                return response;
            } catch (DukeException e) {
                return ResponseGenerator.dukeExceptionMessage(e);
            }
        case EVENT:
            try {
                Event event = Parser.parseEvent(this.description);
                taskList.addTask(event);
                String response = ResponseGenerator.printTaskAdded(event, taskList.getSize());
                storage.save(taskList);
                return response;
            } catch (DukeException e) {
                return ResponseGenerator.dukeExceptionMessage(e);
            }
        case MARK:
            try {
                int taskToMark = Integer.parseInt(this.description) - 1;
                Task markedTask = taskList.markTaskDone(taskToMark);
                String response = ResponseGenerator.printTaskMarked(markedTask);
                storage.save(taskList);
                return response;
            } catch (DukeException e) {
                return ResponseGenerator.dukeExceptionMessage(e);
            }
        case FIND:
            TaskList foundTasks = taskList.findTasks(this.description);
            return ResponseGenerator.tasksFoundToMessage(foundTasks);
        case UNMARK:
            try {
                int taskToUnmark = Integer.parseInt(this.description) - 1;
                Task unmarkedTask = taskList.markTaskUndone(taskToUnmark);
                String response = ResponseGenerator.printTaskUnmarked(unmarkedTask);
                storage.save(taskList);
                return response;
            } catch (DukeException e) {
                return ResponseGenerator.dukeExceptionMessage(e);
            }
        default:
            return "Invalid command!";
        }
    }
}
