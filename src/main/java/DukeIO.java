
public interface DukeIO {
    String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String GREET = "Hello, I'm Ekud! What can I do for you?";
    String FAREWELL = "Bye. Always at your service.";
    String FAREWELL_COMMAND = "bye";

    String MARK_COMMAND = "mark ";
    String UNMARK_COMMAND = "unmark ";
    String DELETE_COMMAND = "delete ";
    String LIST_COMMAND = "list";

    String INVALID_COMMAND_EXCEPTION_MESSAGE = "Invalid Command received.";

    String LEFT_WRAP = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    String RIGHT_WRAP = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    String INDENT = "    ";
    static String wrapContent(String content) {
        return LEFT_WRAP + content + RIGHT_WRAP;
    }
}
