import java.util.Arrays;

public enum Command {
    TODO("todo") {
        @Override
        public void execute(String args) {
            Saturday.todo(args);
        }
    },
    DEADLINE("deadline") {
        @Override
        public void execute(String args) {
            Saturday.deadline(args);
        }
    },
    EVENT("event") {
        @Override
        public void execute(String args) {
            Saturday.event(args);
        }
    },
    LIST("list") {
        @Override
        public void execute(String args) {
            Saturday.displayList();
        }
    },
    MARK("mark") {
        @Override
        public void execute(String args) {
            Saturday.mark(args);
        }
    },
    UNMARK("unmark") {
        @Override
        public void execute(String args) {
            Saturday.unMark(args);
        }
    },
    DELETE("delete") {
        @Override
        public void execute(String args) {
            Saturday.delete(args);
        }
    },
    BYE("bye") {
        @Override
        public void execute(String args) {
            Saturday.exit();
        }
    };

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public abstract void execute(String args);

    public static Command getCommand(String input) {
        for (Command c : Command.values()) {
            if (input.startsWith(c.command)) {
                return c;
            }
        }
        throw new SaturdayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
