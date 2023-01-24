public class Parser {
    private enum CMD {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;
    }

    public static void parse(Commands commands, String cmd) {
        try {
            CMD order = CMD.valueOf(cmd.split(" ")[0].toUpperCase());
            switch (order) {
                case BYE:
                    commands.bye();
                    break;
                case LIST:
                    commands.list();
                    break;
                case MARK:
                    commands.mark(cmd);
                    break;
                case UNMARK:
                    commands.unmark(cmd);
                    break;
                case TODO:
                    commands.todo(cmd);
                    break;
                case DEADLINE:
                    commands.deadline(cmd);
                    break;
                case EVENT:
                    commands.event(cmd);
                    break;
                case DELETE:
                    commands.delete(cmd);
                    break;
            }
        } catch (IllegalArgumentException e) {
            commands.notACommand();
        }
    }
}
