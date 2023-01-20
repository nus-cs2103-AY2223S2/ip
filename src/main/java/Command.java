import exceptions.SaturdayException;

public enum Command {
    TODO("todo") {
        @Override
        public void execute(String tokens) {
            Saturday.todo(tokens);
        }
    },
    DEADLINE("deadline") {
        @Override
        public void execute(String tokens) {
            Saturday.deadline(tokens);
        }
    },
    EVENT("event") {
        @Override
        public void execute(String tokens) {
            Saturday.event(tokens);
        }
    },
    LIST("list") {
        @Override
        public void execute(String tokens) {
            Saturday.displayList();
        }
    },
    MARK("mark") {
        @Override
        public void execute(String tokens) {
            Saturday.mark(tokens);
        }
    },
    UNMARK("unmark") {
        @Override
        public void execute(String tokens) {
            Saturday.unMark(tokens);
        }
    },
    DELETE("delete") {
        @Override
        public void execute(String tokens) {
            Saturday.delete(tokens);
        }
    },
    BYE("bye") {
        @Override
        public void execute(String tokens) {
            Saturday.exit();
        }
    };

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public abstract void execute(String tokens);

    public static Command getCommand(String input) {
        for (Command c : Command.values()) {
            if (input.startsWith(c.command)) {
                return c;
            }
        }
        throw new SaturdayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
