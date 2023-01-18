package duke;

import duke.command.Command;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Utility class for formatting and printing output from Duke.
 *
 * @see Duke
 */
public class UI {

    private String[] helpMsg;

    /**
     * Defines the Duke commands from which the help message is generated.
     *
     * @param commands The commands recognized by Duke.
     */
    public void setCommands(Command[] commands) {
        this.helpMsg = generateHelp(commands);
    }

    private String[] generateHelp(Command[] commands) {
        Stream<String> strings = Arrays.stream(commands)
                .map(c -> String.format("\t%4s : %s", c.getName(), c.getHelpStr()));
        return Stream.concat(Stream.of("Usage: <command> [<args>]"), strings).toArray(String[]::new);
    }

    /**
     * Prints the help message.
     */
    public void print() {
        this.print(this.helpMsg);
    }

    /**
     * Prints the specified message with proper formatting.
     * @param msg The message to be printed.
     */
    public void print(String msg){
        this.print(msg.split("\n"));
    }

    /**
     * Prints the specified lines with proper formatting.
     * @param lines The lines to be printed.
     */
    public void print(String[] lines) {
        StringBuilder outputs = new StringBuilder();
        for (String str : lines) {
            outputs.append("\t").append(str).append("\n");
        }
        System.out.print(outputs + "-----------------------------------------------\n");
    }

    /**
     * Prints the introductory message.
     */
    public void printIntro() {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___| ,
                """;
        System.out.println("Hello, I'm\n" + logo + "how may I help?");
    }

    /**
     * Prints the error message with formatting.
     * @param e The exception to be printed.
     */
    public void error(Exception e) {
        this.print("\t[ERROR] " + e);
    }

    /**
     * Prints the error message with formatting, for a failed initialization.
     * @param e The exception to be printed.
     */
    public void loadError(Exception e) {
        this.print("\t[ERROR] While loading, the following error occurred: \n\t" + e);
    }
}
