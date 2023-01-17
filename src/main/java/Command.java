public enum Command {
    ADD("add") {
        @Override
        public void execute(String args) {
            Saturday.add(args);
        }
    },
    LIST("list") {
        @Override
        public void execute(String args) {
            // code to handle "list" command
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
                System.out.println("Please input the number of the item you would like to mark");
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
                System.out.println("Please input the number of the item you would like to mark");
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
            if (input.startsWith("unmark")) {
                return UNMARK;
            } else if (input.startsWith("mark")) {
                return MARK;
            } else if (c.command.equals(input)) {
                return c;
            }
//            if (c.command.equals(input)) {
//                return c;
//            }
        }
        return ADD;
    }
}
