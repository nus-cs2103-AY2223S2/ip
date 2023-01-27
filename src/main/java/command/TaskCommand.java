package command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import duke.DukeException;
import task.Task;
import task.TaskList;
import ui.TextUi;

public class TaskCommand extends CommandClass {
    protected final String commandName;

    public TaskCommand(String command, boolean doesPrint) {
        super(command, doesPrint, false);
        this.commandName = captalizeFirstChar(command.split(" ")[0]);
    }

    /**
     * Capitalize the first character in a string
     *
     * @param string the string
     * @return a new string whose first character is capitalized
     */
    protected String captalizeFirstChar(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    @Override
    public void execute(TaskList taskList, TextUi ui) throws DukeException {
        String content = getCommandContent(command);

        try {
            Class<?> c = Class.forName("task." + commandName);
            Constructor<?> cons = c.getConstructor(String.class);
            Object object = cons.newInstance(content);
            handleTask((Task) object, taskList, ui);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new DukeException(e.toString());
        }
    }

    public void handleTask(Task task, TaskList taskList, TextUi ui) {
        taskList.add(task);
        String toPrint = String.format("Got it. I've added this task:\n  %s\n" +
                        "Now you have %d tasks in the list.",
                task,
                taskList.size());
        uiPrint(ui, toPrint);
    }

    /**
     * Gets the content of the command
     *
     * @param string: the command string
     * @return the content of the command
     * @throws DukeException when the string is not complete
     */
    public String getCommandContent(String string) throws DukeException {
        String commandString = commandName.toLowerCase();
        if ((!commandString.equals("list")) && string.length() <= commandString.length() + 1) {
            throw new DukeException("The command argument is not complete.");
        }
        return string.substring(string.indexOf(commandString) + commandString.length() + " ".length());
    }
}
