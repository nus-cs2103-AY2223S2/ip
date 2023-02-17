package command;

import duke.DukeException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import task.Task;
import task.TaskList;

/**
 * A class for many tasks
 */
public class TaskCommand extends Command {
    protected static final int SECOND_CHAR_INDEX = 1;
    protected final String commandName;

    /**
     * Default constructor
     *
     * @param command the user-input command
     * @param doesPrint whether to print messages
     * @param numComponents number of non-blank strings separated by spaces
     * @throws DukeException when the input command cannot be parsed
     */
    public TaskCommand(String command, boolean doesPrint, int numComponents) throws DukeException {
        super(command, doesPrint, numComponents);
        this.commandName = captalizeFirstChar(command.split(STRING_SPACE)[0]);
    }

    /**
     * Capitalize the first character in a string
     *
     * @param string the string
     * @return a new string whose first character is capitalized
     */
    protected String captalizeFirstChar(String string) {
        return string.substring(0, SECOND_CHAR_INDEX).toUpperCase() + string.substring(SECOND_CHAR_INDEX);
    }

    /**
     * Execute the task
     * @param taskList the list of tasks
     * @throws DukeException when class name invalid
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        String content = getCommandContent(command);

        try {
            Class<?> c = Class.forName("task." + commandName);
            Constructor<?> cons = c.getConstructor(String.class);
            Object object = cons.newInstance(content);
            return handleTask((Task) object, taskList);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new DukeException(e.toString());
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            throw new DukeException(t.toString());
        }
    }

    /**
     * Handles the task, a subroutine of execute
     *
     * @param task the task to execute
     * @param taskList the task list
     * @return the response message of handling the task
     */
    public String handleTask(Task task, TaskList taskList) {
        taskList.add(task);
        String toPrint = String.format("Got it. I've added this task:\n  %s\n"
                        + "Now you have %d tasks in the list.",
                task,
                taskList.size());
        return toPrint;
    }

    /**
     * Gets the content of the command
     *
     * @param string the command string
     * @return the content of the command
     * @throws DukeException when the string is not complete
     */
    public String getCommandContent(String string) throws DukeException {
        return super.getCommandContent(string, commandName);
    }
}
