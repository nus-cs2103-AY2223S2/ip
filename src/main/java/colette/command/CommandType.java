package colette.command;

/** Enum to represent commands */
public enum CommandType {
    BYE, MARK, UNMARK, DEADLINE, TODO, EVENT, LIST, FIND, DELETE, HELP;

    /**
     * Constructs instructions on all available
     * commands.
     *
     * @return Instructions on all available commands.
     */
    public static String getAllCommandFormatString() {
        String allCommandFormatString = "";

        // HELP command
        allCommandFormatString += "If you'd like to see all commands:\n"
                + "help\n"
                + "\n";

        // BYE command
        allCommandFormatString += "If you want to exit:\n"
                + "bye\n"
                + "\n";

        // MARK command
        allCommandFormatString += "If you want to mark a task done:\n"
                + "mark [index]\n"
                + "\n";

        // UNMARK command
        allCommandFormatString += "If you want to mark a task as not done:\n"
                + "unmark [index]\n"
                + "\n";

        // TODO command
        allCommandFormatString += "If you want to add a new TODO:\n"
                + "todo [description]\n"
                + "\n";

        // DEADLINE command
        allCommandFormatString += "If you want to add a new DEADLINE:\n"
                + "deadline [description] /by [due date]\n"
                + "\n";

        // EVENT command
        allCommandFormatString += "If you want to add a new EVENT:\n"
                + "event [description] /from [start date] /to [end date]\n"
                + "\n";

        // LIST command
        allCommandFormatString += "If you'd like to see all your tasks:\n"
                + "list\n"
                + "\n";

        // FIND command
        allCommandFormatString += "If you'd like to find a specific task:\n"
                + "find [keyword]\n"
                + "\n";

        // DELETE command
        allCommandFormatString += "If you'd like to delete a task:\n"
                + "delete [index]";

        return allCommandFormatString;
    }
}
