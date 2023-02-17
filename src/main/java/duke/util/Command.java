package duke.util;

import duke.exception.DukeException;
import duke.exception.ERROR;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class to encapsulate a parsed command which has a defined command type and specific parameters.
 */
public class Command {
    /**
     * All available command types.
     */
    public enum CommandType {
        EXIT,
        LIST,
        MARK,
        UNMARK,
        ADD_TASK,
        DELETE_TASK,
        FIND_TASK,
        INVALID_COMMAND
    }
    private static final String LIST_OUTPUT = "Here are the tasks in your list:";
    private static final String MARK_TASK_OUTPUT = "Nice! I've marked this task as done:\n";
    private static final String UNMARK_TASK_OUTPUT = "Okay! I've marked this task as undone:\n";
    private static final String ADD_TASK_OUTPUT = "Got it. I've added this task:\n\t%s\n" +
            "Now you have %d tasks in the list.";
    private static final String DELETE_TASK_OUTPUT = "Noted. I've removed this task:\n\t%s\n" +
            "Now you have %d tasks in the list.";
    private static final String FIND_TASK_OUTPUT = "Here are the matching tasks in your list:";
    private static final String EXIT_OUTPUT = "Bye. Hope to see you again soon!";
    private CommandType commandType;
    private String param;

    /**
     * Creates an instance of Command with the type defined but no parameters.
     *
     * @param commandType The type of command.
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Creates an instance of Command with the type defined and one parameter.
     *
     * @param commandType The type of command.
     * @param param A paramter used for the given type of command.
     */
    public Command(CommandType commandType, String param) {
        this.commandType = commandType;
        this.param = param;
    }

    /**
     * Returns the command type.
     *
     * @return The command type.
     */
    public CommandType getCommandType() {
        return this.commandType;
    }

    /**
     * Returns the parameter in this command.
     *
     * @return The command parameter.
     */
    public String getParam() {
        return this.param;
    }

    /**
     * Executes the current command using a given TaskList, UI, and StorageTextFile.
     *
     * @param taskList TaskList containing tasks to use for this command.
     * @param ui The UI to print output to.
     * @param storage StorageTextFile to save the TaskList to if this command changes the TaskList.
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String output = "";

        assert taskList != null;

        switch (this.commandType) {
            case EXIT:
                output = EXIT_OUTPUT;
                break;
            case LIST:
                output = LIST_OUTPUT;
                for (int i = 0; i < taskList.size(); ++i) {
                    assert taskList.get(i) != null;
                    output += String.format("\n%d.%s", i + 1, taskList.get(i).toString());
                }
                break;
            case MARK:
                int index = Integer.parseInt(param);
                Task task = taskList.markTask(index);
                output = MARK_TASK_OUTPUT + task.toString();
                storage.save(taskList);
                break;
            case UNMARK:
                index = Integer.parseInt(param);
                task = taskList.unmarkTask(index);
                output = UNMARK_TASK_OUTPUT + task.toString();
                storage.save(taskList);
                break;
            case DELETE_TASK:
                index = Integer.parseInt(param);
                task = taskList.deleteTask(index);
                output = String.format(DELETE_TASK_OUTPUT, task.toString(), taskList.size());
                storage.save(taskList);
                break;
            case ADD_TASK:
                task = Task.parseTaskFromInput(param);
                assert task != null;
                taskList.addTask(task);
                output = String.format(ADD_TASK_OUTPUT, task.toString(), taskList.size());
                storage.save(taskList);
                break;
            case FIND_TASK:
                TaskList results = taskList.findTasks(param);
                output = FIND_TASK_OUTPUT;
                for (int i = 0; i < results.size(); ++i) {
                    assert taskList.get(i) != null;
                    output += String.format("\n%d.%s", i + 1, results.get(i).toString());
                }
                break;
            case INVALID_COMMAND:
                throw new DukeException(ERROR.INVALID_INPUT.getMessage());
        }

        return output;
    }
}
