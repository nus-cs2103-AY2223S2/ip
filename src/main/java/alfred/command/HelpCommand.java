package alfred.command;

import java.util.HashMap;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.TaskList;
import alfred.ui.Ui;

/**
 * Represents a help command object which users can execute to find out what are the available commands.
 */
public class HelpCommand extends Command {

    private final HashMap<String, String> getCommandGuide = new HashMap<>();
    private static final String[] ORDER_OF_TASK = new String[]{
        "todo", "deadline", "event",
        "mark", "unmark", "delete",
        "list", "listDate", "exit",
        "find", "help"
    };

    /**
     * Constructs a HelpCommand object that creates a table that maps the task to its particular help message.
     */
    public HelpCommand() {
        getCommandGuide.put("deadline", "To add a deadline: deadline <name> /by <deadline>");
        getCommandGuide.put("delete", "To delete a task: delete <task-index>");
        getCommandGuide.put("event", "To add an event: event <name> /from <start-date> /to <end-date>");
        getCommandGuide.put("exit", "To exit the program: exit");
        getCommandGuide.put("find", "To find tasks that contains certain words: find <key-words>");
        getCommandGuide.put("help", "To seek help regarding the program: help");
        getCommandGuide.put("list", "To list the given tasks: list");
        getCommandGuide.put("listDate", "To list the given tasks that contain such date: list <date>");
        getCommandGuide.put("mark", "To mark a task: mark <task-index>");
        getCommandGuide.put("todo", "To add a todo task: todo <task-name>");
        getCommandGuide.put("unmark", "To unmark a task: unmark <task-index>");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        StringBuilder message = new StringBuilder("Here are some commands to help you work your way through"
                + " the program :)\n");
        for (String task : ORDER_OF_TASK) {
            message.append("    ");
            message.append(getCommandGuide.get(task));
            message.append("\n");
        }
        return ui.getCommandMessage(message.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}