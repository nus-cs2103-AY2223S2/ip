package jarvis.command;

import jarvis.Storage;
import jarvis.task.TaskList;
import jarvis.Ui;
import jarvis.exception.InvalidActionException;

import java.util.List;

public abstract class Command {

    /**
     * Enum of command actions with their associated keywords.
     */
    public enum Action {
        BYE("bye"),
        LIST("list"),
        MARK_DONE("mark"),
        MARK_UNDONE("unmark"),
        CREATE_TODO("todo"),
        CREATE_DEADLINE("deadline"),
        CREATE_EVENT("event"),
        DELETE_TASK("delete"),
        DEADLINE_BY("by"),
        EVENT_FROM("from"),
        EVENT_TO("to");

        private final String keyword;

        Action(String keyword) {
            this.keyword = keyword;
        }

        // @@author hansstanley-reused
        // Reused from https://stackoverflow.com/questions/604424
        // with minor modifications.
        public static Action fromString(String str) throws InvalidActionException {
            if (str != null) {
                str = str.trim();
                for (Action action : Action.values()) {
                    if (action.keyword.equalsIgnoreCase(str)) {
                        return action;
                    }
                }
            }
            throw new InvalidActionException("Invalid action string");
        }
    }

    /**
     * Executes the command.
     * @param ui Ui object.
     * @param taskList TaskList object.
     * @param storage Storage object.
     */
    public abstract void execute(Ui ui, TaskList taskList, Storage storage);

    private final Action action;
    private final String body;
    private final List<Command> subCommands;

    /**
     * Constructor for a command.
     * @param action Determines the nature of the command.
     * @param body Supplementary information for the command.
     * @param subCommands Secondary commands as additional parameters.
     */
    public Command(Action action, String body, List<Command> subCommands) {
        this.action = action;
        this.body = body;
        this.subCommands = subCommands;
    }

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
     * @return The secondary commands, if any.
     */
    public List<Command> getSubCommands() {
        return this.subCommands;
    }

    /**
     * @param action Action to check against.
     * @return Whether this command is of the given action's nature.
     */
    public boolean hasAction(Action action) {
        return this.action == action;
    }

    /**
     * @param actions Actions to check against.
     * @return Whether this command is of any of the given actions' natures.
     */
    public boolean hasAction(Action ...actions) {
        for (Action action : actions) {
            if (this.action == action) return true;
        }
        return false;
    }
}
