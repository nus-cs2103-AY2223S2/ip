package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a duke.DukeCommand object that represents a command.
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
     * the command.  For example, the command of the command "todo read book" is "todo".
     */
    private final String command;

    /**
     * the description of the command.  For example, the description of the command "todo read book" is "read book".
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
     * Executes the command.
     * @param taskList task list.
     * @param ui ui.
     * @param storage storage.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (this) {
        case BYE:
            ui.printGoodbye();
            break;
        case LIST:
            ui.printTaskList(taskList);
            break;
        case DELETE:
            int taskToDelete = Integer.parseInt(this.description) - 1;
            try {
                Task deletedTask = taskList.deleteTask(taskToDelete);
                ui.printTaskDeleted(deletedTask, taskList.getSize());
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case TODO:
            try {
                ToDo todo = Parser.parseTodo(this.description);
                taskList.addTask(todo);
                ui.printTaskAdded(todo, taskList.getSize());
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case DEADLINE:
            try {
                Deadline deadline = Parser.parseDeadline(this.description);
                taskList.addTask(deadline);
                ui.printTaskAdded(deadline, taskList.getSize());
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case EVENT:
            try {
                Event event = Parser.parseEvent(this.description);
                taskList.addTask(event);
                ui.printTaskAdded(event, taskList.getSize());
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case MARK:
            try {
                int taskToMark = Integer.parseInt(this.description) - 1;
                Task markedTask = taskList.markTaskDone(taskToMark);
                ui.printTaskMarked(markedTask);
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case FIND:
            TaskList foundTasks = taskList.findTasks(this.description);
            ui.printTasksFound(foundTasks);
            break;
        case UNMARK:
            try {
                int taskToUnmark = Integer.parseInt(this.description) - 1;
                Task unmarkedTask = taskList.markTaskUndone(taskToUnmark);
                ui.printTaskUnmarked(unmarkedTask);
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case INVALID:
            ui.prettifyOut("Invalid command!");
            break;
        default:
            break;
        }
    }
}
