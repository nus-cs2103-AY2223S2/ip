package duke.views;

/**
 * Handles display of program output to user.
 */
public class UI {
    private static final String DATE_FORMAT = "dd/mm/yyyy HHmm";

    public String getInitMessage() {
        return "Hello. I'm Duke, your personal assistant.\n"
                + "How may I help you today?";
    }

    private String newlineAndIndentString(String s) {
        return "\n\t" + s;
    }

    /**
     * Displays a list of possible commands
     * to the user
     */
    public String getAvailableCommands() {
        return "Here is the list of available commands I can do:"
                        + newlineAndIndentString("todo [name]")
                        + newlineAndIndentString("deadline [name] /by [" + DATE_FORMAT + "]")
                        + newlineAndIndentString("event [name] /from [" + DATE_FORMAT + "] /to [" + DATE_FORMAT + "]")
                        + newlineAndIndentString("list")
                        + newlineAndIndentString("mark [index]")
                        + newlineAndIndentString("unmark [index]")
                        + newlineAndIndentString("delete [index]")
                        + newlineAndIndentString("find [search term]")
                        + newlineAndIndentString("free")
                        + newlineAndIndentString("bye");
    }

}
