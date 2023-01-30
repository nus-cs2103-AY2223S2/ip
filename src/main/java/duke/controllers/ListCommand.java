package duke.controllers;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.entities.TaskList;
import duke.enums.CommandType;
import duke.enums.TaskType;
import duke.exceptions.DukeException;

/**
 * Represents the List Command.
 * The list command can be used to list all tasks.
 */
public class ListCommand extends Command {
    /** Valid list command regex **/
    private static final Pattern VALID_LIST_CMD =
            Pattern.compile("^(list\\s*)((?<filter>todo|deadline|event)\\s*$)?");
    private final String arguments;

    /**
     * Initializes a list command.
     */
    public ListCommand(String arguments) {
        super(CommandType.LIST);
        this.arguments = arguments;
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and list all tasks in the store.
     */
    @Override
    public String execute(Supplier<? extends TaskList> store) throws DukeException {
        TaskList taskList = store.get();
        Matcher matcher = VALID_LIST_CMD.matcher(arguments);
        if (matcher.find()) {
            String filter = matcher.group("filter");
            TaskType type = Optional.ofNullable(filter)
                    .map(enumTask -> TaskType.valueOf(enumTask.trim().toUpperCase()))
                    .orElse(TaskType.ALL);

            return taskList.listTasks(task -> type.isAll() || task.getTaskType() == type, type.isAll());
        } else {
            throw new DukeException(INVALID_FORMAT_ERROR);
        }
    }
}
