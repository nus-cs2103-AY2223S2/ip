package jarvis.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jarvis.exception.InvalidActionException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Abstract command class.
 */
public abstract class Command {

    /**
     * Enum of command actions with their associated keywords.
     */
    public enum Action {
        INTRO("hi", "hello", "hey"),
        BYE("bye", "goodbye"),
        LIST("list", "find"),
        MARK_DONE("mark"),
        MARK_UNDONE("unmark"),
        CREATE_TODO("todo"),
        CREATE_DEADLINE("deadline"),
        CREATE_EVENT("event"),
        DELETE_TASK("delete"),
        DEADLINE_BY("by"),
        EVENT_FROM("from"),
        EVENT_TO("to");

        private final String[] keywords;

        Action(String ...keywords) {
            this.keywords = keywords;
        }

        // @@author hansstanley-reused
        // Reused from https://stackoverflow.com/questions/604424
        // with minor modifications.
        /**
         * Converts a string into an Action.
         *
         * @param str Raw string.
         * @return The matching Action, if any.
         * @throws InvalidActionException If the raw string does not match any Action.
         */
        public static Action fromString(String str) throws InvalidActionException {
            if (str == null) {
                throw new InvalidActionException("Action string cannot be null");
            }

            str = str.trim().toLowerCase();
            for (Action action : Action.values()) {
                if (List.of(action.keywords).contains(str)) {
                    return action;
                }
            }
            throw new InvalidActionException("Invalid action string");
        }
    }

    private final Action action;
    private final String body;
    private final Map<Action, Command> commandMap;

    /**
     * Constructor for a command without subcommands.
     *
     * @param action Determines the nature of the command.
     * @param body Supplementary information for the command.
     */
    public Command(Action action, String body) {
        assert action != null;
        this.action = action;
        this.body = body;
        this.commandMap = new HashMap<>();
    }

    /**
     * Constructor for a command.
     *
     * @param action Determines the nature of the command.
     * @param body Supplementary information for the command.
     * @param subCommands Secondary commands as additional parameters.
     */
    public Command(Action action, String body, List<Command> subCommands) {
        this(action, body);

        for (Command command : subCommands) {
            this.commandMap.put(command.action, command);
        }
    }

    /**
     * Executes the command.
     *
     * @param ui Ui object.
     * @param taskList TaskList object.
     * @param storage Storage object.
     */
    public abstract void execute(Ui ui, TaskList taskList, Storage storage);

    /**
     * @return Whether the command should exit the bot.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * @return The body of the command, if any.
     */
    public String getBody() {
        return this.body;
    }

    /**
     * @param actions Actions to check against.
     * @return Whether this command is of the given actions' natures.
     */
    public boolean hasAction(Action ...actions) {
        assert actions != null;
        for (Action action : actions) {
            if (this.action == action) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the subcommand with the given action, if any.
     *
     * @param action Action of the subcommand.
     * @return Command with the associated action.
     */
    public Command getSubCommand(Action action) {
        return this.commandMap.getOrDefault(action, null);
    }
}
