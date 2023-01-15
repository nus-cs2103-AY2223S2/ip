import exception.InvalidActionException;
import exception.MissingParameterException;
import task.DeadlineTask;
import task.EventTask;
import task.ToDoTask;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Command {

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

    private final Action action;
    private final String body;
    private final List<Command> subCommands;

    public Command(Scanner scanner) throws InvalidActionException {
        this.action = Action.fromString(scanner.hasNext() ? scanner.next() : null);
        this.body = scanner.skip("\\s*").hasNext("[^/]+") ? scanner.next("[^/]+").trim() : null;
        this.subCommands = new LinkedList<>();

        scanner.useDelimiter("\\s*/\\s*");
        while (scanner.skip("\\s*").hasNext()) {
            this.subCommands.add(new Command(new Scanner(scanner.next())));
        }
        scanner.close();
    }

    public String getBody() {
        return this.body;
    }

    public boolean hasAction(Action action) {
        return this.action == action;
    }

    public ToDoTask toToDoTask() throws MissingParameterException {
        return new ToDoTask(this.body);
    }

    public DeadlineTask toDeadlineTask() throws MissingParameterException {
        String deadline = null;
        for (Command command : this.subCommands) {
            if (command.hasAction(Action.DEADLINE_BY) && deadline == null) {
                deadline = command.body;
            }
        }

        return new DeadlineTask(this.body, deadline);
    }

    public EventTask toEventTask() throws MissingParameterException {
        String fromDateTime = null;
        String toDateTime = null;
        for (Command command : subCommands) {
            if (command.hasAction(Action.EVENT_FROM) && fromDateTime == null) {
                fromDateTime = command.body;
            } else if (command.hasAction(Action.EVENT_TO) && toDateTime == null) {
                toDateTime = command.body;
            }
        }

        return new EventTask(this.body, fromDateTime, toDateTime);
    }
}
