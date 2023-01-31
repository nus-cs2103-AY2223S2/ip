package duke.command;

/** Enum to represent commands */
public enum CommandType {
    BYE, MARK, UNMARK, DEADLINE, TODO, EVENT, LIST, FIND, DELETE, HELP;

    public static String getAllCommandFormats() {
        String allCommandFormats = "";

        // HELP command
        allCommandFormats += "If you'd like to see all commands:\n"
                + "help\n"
                + "\n";

        // BYE command
        allCommandFormats += "If you want to exit:\n"
                + "bye\n"
                + "\n";

        // MARK command
        allCommandFormats += "If you want to mark a task done:\n"
                + "mark [index]\n"
                + "\n";

        // UNMARK command
        allCommandFormats += "If you want to mark a task as not done:\n"
                + "unmark [index]\n"
                + "\n";

        // TODO command
        allCommandFormats += "If you want to add a new TODO:\n"
                + "todo [description]\n"
                + "\n";

        // DEADLINE command
        allCommandFormats += "If you want to add a new DEADLINE:\n"
                + "deadline [description] /by [due date]\n"
                + "\n";

        // EVENT command
        allCommandFormats += "If you want to add a new EVENT:\n"
                + "event [description] /from [start date] /to [end date]\n"
                + "\n";

        // LIST command
        allCommandFormats += "If you'd like to see all your tasks:\n"
                + "list\n"
                + "\n";

        // FIND command
        allCommandFormats += "If you'd like to find a specific task:\n"
                + "find [keyword]\n"
                + "\n";

        // DELETE command
        allCommandFormats += "If you'd like to delete a task:\n"
                + "delete [index]";

        return allCommandFormats;
    }
}
