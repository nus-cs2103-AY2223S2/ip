package duke;

import java.util.HashMap;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import javafx.util.Pair;

/**
 * Class representing the parser.
 */
public class Parser {
    private static final BadCommandException BAD_PARAMS_ERROR =
            new BadCommandException("There are insufficient or invalid arguments!");
    private static final BadCommandException UNKNOWN_COMMAND_ERROR =
            new BadCommandException("I'm sorry, but I don't know what that means :-(");
    private static final String PARAMS_DELIMITER = "/";

    private enum Command {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        FIND("find");
        private static final HashMap<String, Command> STRING_TO_COMMAND_MAP = new HashMap<>();
        private final String commandStr;

        static {
            for (Command command: values()) {
                STRING_TO_COMMAND_MAP.put(command.commandStr, command);
            }
        }
        Command(String commandStr) {
            this.commandStr = commandStr;
        }
        public static Command valueOfCommandStr(String commandStr) {
            return STRING_TO_COMMAND_MAP.get(commandStr);
        }
        @Override
        public String toString() {
            return commandStr;
        }
    }

    /**
     * Returns the singular integer index from a provided parameter string. For commands
     * that only require a single integer value, such as 'mark'.
     *
     * @param paramsStr Given parameter string.
     * @return The singular integer index.
     * @throws BadCommandException If the parameter string is not a valid integer representation.
     */
    private int getIntegerFromParamsStr(String paramsStr) throws BadCommandException {
        int idx;
        try {
            idx = Integer.parseInt(paramsStr);
        } catch (NumberFormatException e) {
            throw BAD_PARAMS_ERROR;
        }
        return idx;
    }

    /**
     * Returns a pair whose key represents the default argument, and whose value represents the mapping between
     * the parameter names and the argument values. If there is no default argument, then the key value is set
     * to null. The default argument is always assumed to be the first argument.
     *
     * @param paramsStr The parameter string provided.
     * @param hasDefaultArgument Boolean indicating if there is a default argument.
     * @return A pair representing the above.
     * @throws BadCommandException If any delimiter is not followed by a parameter-argument.
     */
    private Pair<String, HashMap<String, String>> getMappingFromParamsStr(
            String paramsStr,
            boolean hasDefaultArgument
    ) throws BadCommandException {
        String[] paramsSplit = paramsStr.trim().split(PARAMS_DELIMITER);
        // If there's a default argument (an argument with no specified delimiter like the 'deadline' command),
        // then we take the first item in paramsSplit as the default argument.
        int startingIdx = hasDefaultArgument ? 1 : 0;
        String defaultArgument = hasDefaultArgument ? paramsSplit[0].trim() : null;

        HashMap<String, String> paramToArgMap = new HashMap<>();
        for (int i = startingIdx; i < paramsSplit.length; i++) {
            String[] paramArgSplit = paramsSplit[i].split(" ", 2);
            if (paramArgSplit.length == 0) {
                throw BAD_PARAMS_ERROR;
            }
            paramToArgMap.put(
                    paramArgSplit[0].trim(),
                    paramsSplit.length < 2 ? "" : paramArgSplit[1].trim()
            );
        }
        return new Pair<>(defaultArgument, paramToArgMap);
    }

    /**
     * Returns new task based on the given params.
     * @param commandEnum Command enum value of the command.
     * @param description Description of the task.
     * @param paramToArgMap Parameter to argument map given.
     * @return New task based on the above parameters.
     * @throws BadCommandException If insufficient or invalid arguments are given.
     */
    private Task createNewTask(Command commandEnum,
                               String description,
                               HashMap<String, String> paramToArgMap) throws BadCommandException {
        boolean hasTagsArg = paramToArgMap.containsKey("tag");
        String tags = paramToArgMap.get("tag");
        try {
            Task newTask = null;
            switch (commandEnum) {
            case TODO:
                newTask = hasTagsArg ? new Todo(description, tags) : new Todo(description);
                break;
            case DEADLINE:
                if (!paramToArgMap.containsKey("by")) {
                    throw BAD_PARAMS_ERROR;
                }
                newTask = hasTagsArg
                        ? new Deadline(description, paramToArgMap.get("by"), tags)
                        : new Deadline(description, paramToArgMap.get("by"));
                break;
            case EVENT:
                if (!paramToArgMap.containsKey("from") || !paramToArgMap.containsKey("to")) {
                    throw BAD_PARAMS_ERROR;
                }
                newTask = hasTagsArg
                        ? new Event(description, paramToArgMap.get("from"), paramToArgMap.get("to"), tags)
                        : new Event(description, paramToArgMap.get("from"), paramToArgMap.get("to")
                );
                break;
            default:
                throw UNKNOWN_COMMAND_ERROR;
            }
            return newTask;
        } catch (DukeException e) {
            throw new BadCommandException(e.getMessage());
        }

    }

    /**
     * Parses a given input string.
     *
     * @param inputStr The input string given by the user.
     * @param tasks The TaskList to be managed.
     * @param ui The Ui to be used to print messages.
     * @throws BadCommandException If the parameters of the input string are insufficient, or if
     *          the command given is not recognised.
     */
    public String parseString(String inputStr, TaskList tasks, Ui ui) throws BadCommandException {
        inputStr = inputStr.trim();
        String[] inputSplit = inputStr.split(" ", 2);
        String commandStr = inputSplit.length < 1 ? "" : inputSplit[0].trim();
        String paramsStr = inputSplit.length < 2 ? "" : inputSplit[1].trim();
        Command commandEnumValue = Command.valueOfCommandStr(commandStr);

        if (commandEnumValue == null) {
            throw UNKNOWN_COMMAND_ERROR;
        }

        switch (commandEnumValue) {
        case LIST:
            return ui.showNormalMessage(String.format(
                    "Here are the tasks in your list:\n%s",
                    tasks
            ));
        case FIND: {
            TaskList matchingTasks = tasks.getTasksByKeyword(paramsStr);
            return ui.showNormalMessage(String.format(
                    "Here are the matching tasks in your list:\n%s",
                    matchingTasks
            ));
        }
        case MARK: {
            int idx = getIntegerFromParamsStr(paramsStr) - 1;
            tasks.markTaskAsDone(idx);
            return ui.showNormalMessage(String.format(
                    "Nice! I've marked this task as done:\n\t%s",
                    tasks.getTask(idx)
            ));
        }
        case UNMARK: {
            int idx = getIntegerFromParamsStr(paramsStr) - 1;
            tasks.unmarkTaskAsDone(idx);
            return ui.showNormalMessage(String.format(
                    "OK, I've marked this task as not done yet:\n\t%s",
                    tasks.getTask(idx)
            ));
        }
        case DELETE: {
            int idx = getIntegerFromParamsStr(paramsStr) - 1;
            Task deletedTask = tasks.removeTask(idx);
            return ui.showNormalMessage(String.format(
                    "Got it. I've removed this task:\n\t%s\nNow you have %d task%s in the list.",
                    deletedTask,
                    tasks.getSize(),
                    tasks.getSize() > 1 ? "s" : ""
            ));
        }
        case TODO:
        case DEADLINE:
        case EVENT: {
            Pair<String, HashMap<String, String>> descriptionMappingPair =
                    getMappingFromParamsStr(paramsStr, true);
            String description = descriptionMappingPair.getKey();
            HashMap<String, String> paramToArgMap = descriptionMappingPair.getValue();
            Task newTask = createNewTask(commandEnumValue, description, paramToArgMap);
            assert newTask != null : "newTask should not be null";
            tasks.addTask(newTask);
            return ui.showNormalMessage(String.format(
                    "Got it. I've added this task:\n\t%s\nNow you have %d task%s in the list.",
                    newTask,
                    tasks.getSize(),
                    tasks.getSize() > 1 ? "s" : ""
            ));
        }
        default:
            throw UNKNOWN_COMMAND_ERROR;
        }
    }
}
