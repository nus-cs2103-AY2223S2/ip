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
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.valueOf(number);
                    Saturday.mark(i);
                }
            } else {
                throw new IllegalArgumentException("Please input the number of the item you would like to mark");
            }
        }
    },
    UNMARK("unmark") {
        @Override
        public void execute(String args) {
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.valueOf(number);
                    Saturday.unMark(i);
                }
            } else {
                throw new IllegalArgumentException("Please input the number of the item you would like to mark");
            }
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
        throw new IllegalArgumentException("Invalid command: " + input);
    }
}
