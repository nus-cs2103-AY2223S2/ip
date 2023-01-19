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
        FIND("find"),
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

    public abstract void execute(Ui ui, TaskList taskList, Storage storage);

    private final Action action;
    private final String body;
    private final List<Command> subCommands;

    public Command(Action action, String body, List<Command> subCommands) {
        this.action = action;
        this.body = body;
        this.subCommands = subCommands;
    }

    public boolean isExit() {
        return false;
    }

    public String getBody() {
        return this.body;
    }

    public List<Command> getSubCommands() {
        return this.subCommands;
    }

    public boolean hasAction(Action action) {
        return this.action == action;
    }

    public boolean hasAction(Action ...actions) {
        for (Action action : actions) {
            if (this.action == action) return true;
        }
        return false;
    }
}
