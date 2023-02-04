package duke.views;

import duke.commands.Command;

/**
 * Handles display of program output to user.
 */
public class UI {
    private static final String DATE_FORMAT = "dd/mm/yyyy";

    public void printInitMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n"
                + logo
                + ", your personal assistant.\n");
    }

    public void printExitMessage() {
        System.out.println("I hope you've managed to be productive today. Bye!");
    }

    private String indentString(String s) {
        return "\n\t" + s;
    }

    /**
     * Displays a list of possible commands
     * to the user
     */
    public void printCommands() {
        System.out.println(
                "Here is the list of available commands I can do:"
                        + indentString("bye")
                        + indentString("todo [name]")
                        + indentString("deadline [name] /by [" + DATE_FORMAT + "]")
                        + indentString("event [name] /from [" + DATE_FORMAT + "] /to [" + DATE_FORMAT + "]")
                        + indentString("list")
                        + indentString("mark [index]")
                        + indentString("unmark [index]")
                        + indentString("delete [index]")
                        + indentString("find [search term]")
                        + indentString("bye"));
    }

    public void printCommandOutput(Command c) {
        System.out.println(c.getCommandStatus());
    }

    public void printErrorMessage(String e) {
        System.out.println(e);
    }

}
