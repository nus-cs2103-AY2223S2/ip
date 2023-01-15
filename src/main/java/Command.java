import exception.CommandParseException;
import exception.InvalidActionException;
import exception.MissingParameterException;
import task.DeadlineTask;
import task.EventTask;
import task.ToDoTask;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        DEADLINE_BY("by"),
        EVENT_FROM("from"),
        EVENT_TO("to");

        private final String keyword;

        Action(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }

        // @@author hansstanley-reused
        // Reused from https://stackoverflow.com/questions/604424
        // with minor modifications.
        public static Action fromString(String str) {
            str = str.trim();
            for (Action action : Action.values()) {
                if (action.keyword.equalsIgnoreCase(str)) {
                    return action;
                }
            }
            return null;
        }
    }

    /**
     * Parses a '/'-separated string of commands into
     * individual Commands.
     * @param longCommand Command string.
     * @return Array of Commands.
     */
    public static Command[] parseCommand(String longCommand) throws CommandParseException {
        if (longCommand == null) {
            throw new CommandParseException("Long command cannot be null");
        }
        String[] parts = longCommand.split("/");
        List<Command> commands = new LinkedList<>();
        for (String part : parts) {
            if (part.isBlank()) continue;
            commands.add(new Command(part));
        }
        return commands.toArray(Command[]::new);
    }

    private final Action action;
    private final String body;

    public Command(String command) throws CommandParseException {
        if (command == null) {
            throw new CommandParseException("Command cannot be null", "Couldn't hear you there, please try again.");
        }
        command = command.trim();
        if (command.isEmpty()) {
            throw new CommandParseException("Command cannot be empty", "Couldn't hear you there, please try again.");
        }

        int actionEnd = command.indexOf(' ');
        if (actionEnd == -1) {
            this.action = Action.fromString(command);
            this.body = null;
        } else {
            this.action = Action.fromString(command.substring(0, actionEnd));
            this.body = command.substring(actionEnd).trim();
        }
        if (this.action == null) {
            throw new InvalidActionException("Invalid verb provided as Action");
        }
    }

    public String getBody() {
        return this.body;
    }

    public boolean isEmpty() {
        return this.action == null && (this.body == null || this.body.isEmpty());
    }

    public boolean hasAction(Action action) {
        return this.action == action;
    }

    public ToDoTask toToDoTask() throws MissingParameterException {
        return new ToDoTask(this.body);
    }

    public DeadlineTask toDeadlineTask() throws CommandParseException, MissingParameterException {
        String fullCommand = this.toString();
        String description = null;
        String deadline = null;
        if (fullCommand != null) {
            Command[] subCommands = Command.parseCommand(fullCommand);
            for (Command command : subCommands) {
                if (command.hasAction(Action.CREATE_DEADLINE) && description == null) {
                    description = command.body;
                } else if (command.hasAction(Action.DEADLINE_BY) && deadline == null) {
                    deadline = command.body;
                }
            }
        }

        return new DeadlineTask(description, deadline);
    }

    public EventTask toEventTask() throws CommandParseException, MissingParameterException {
        String fullCommand = this.toString();
        String description = null;
        String fromDateTime = null;
        String toDateTime = null;
        if (fullCommand != null) {
            Command[] subCommands = Command.parseCommand(fullCommand);
            for (Command command : subCommands) {
                if (command.hasAction(Action.CREATE_EVENT) && description == null) {
                    description = command.body;
                } else if (command.hasAction(Action.EVENT_FROM) && fromDateTime == null) {
                    fromDateTime = command.body;
                } else if (command.hasAction(Action.EVENT_TO) && toDateTime == null) {
                    toDateTime = command.body;
                }
            }
        }
        return new EventTask(description, fromDateTime, toDateTime);
    }

    @Override
    public String toString() {
        List<String> list = Stream.of(this.action == null ? null : this.action.getKeyword(), this.body)
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toList());
        return String.join(" ", list);
    }
}
