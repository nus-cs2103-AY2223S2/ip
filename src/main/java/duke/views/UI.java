package duke.views;

import duke.commands.Command;

/**
 * Handles display of program output to user.
 */
public class UI {
    private static final String DATE_FORMAT = "dd/mm/yyyy";

    public String getInitMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return "Hello. I'm Duke, your personal assistant.\n"
                + "How may I help you today?";
    }

    private String indentString(String s) {
        return "\n\t" + s;
    }

    /**
     * Displays a list of possible commands
     * to the user
     */
    public String getAvailableCommands() {
        return "Here is the list of available commands I can do:"
                        + indentString("bye")
                        + indentString("todo [name]")
                        + indentString("deadline [name] /by [" + DATE_FORMAT + "]")
                        + indentString("event [name] /from [" + DATE_FORMAT + "] /to [" + DATE_FORMAT + "]")
                        + indentString("list")
                        + indentString("mark [index]")
                        + indentString("unmark [index]")
                        + indentString("delete [index]")
                        + indentString("find [search term]")
                        + indentString("bye");
    }

    public void printCommandOutput(Command c) {
        System.out.println(c.getCommandStatus());
    }

    public void printErrorMessage(String e) {
        System.out.println(e);
    }

}
