package duke.util;

import duke.Duke;
import duke.command.Command;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Utility class for formatting and printing printable from Duke.
 *
 * @see Duke
 */
public class Ui {

    private HashMap<String, String> helpDict = new HashMap<>();
    private static final List<String> HEADER_STR = Collections.singletonList("Usage: <command> [<args>]");

    /**
     * Creates the help message dictionary from the given Duke commands.
     *
     * @param commands The commands recognized by Duke.
     */
    public void setHelpDict(Command[] commands) {
        this.helpDict = generateHelp(commands);
    }

    private HashMap<String, String> generateHelp(Command[] commands) {
        return Arrays.stream(commands)
                .collect(Collectors.toMap(Command::getName,
                        Command::getHelpText,
                        (prev, next) -> next,
                        HashMap::new)
                );
    }

    /**
     * Returns the help message dictionary.
     *
     * @return The help message dictionary.
     */
    public HashMap<String, String> getHelpDict() {
        return this.helpDict;
    }

    /**
     * Returns the help message.
     *
     * @return The help message.
     */
    public Queue<String> getHelpMsg() {
        Queue<String> header = new LinkedList<>(HEADER_STR);
        header.addAll(new LinkedList<>(this.helpDict.values()));
        return header;
    }

    public static Queue<String> getHelpMsg(List<String> commands) {
        Queue<String> header = new LinkedList<>(HEADER_STR);
        header.addAll(commands);
        return header;
    }


    /**
     * Prints the help message.
     */
    public void print() {
        this.print(this.getHelpMsg());
    }

    /**
     * Prints the specified message with proper formatting.
     *
     * @param msg The message to be printed.
     */
    public void print(String msg) {
        this.print(new LinkedList<>(Arrays.asList(msg.split("\n"))));
    }

    /**
     * Prints the specified lines with proper formatting.
     *
     * @param lines The lines to be printed.
     */
    public void print(Queue<String> lines) {
        StringBuilder outputs = new StringBuilder();
        for (String str : lines) {
            outputs.append("\t").append(str).append("\n");
        }
        outputs.append("-----------------------------------------------\n");
        System.out.print(outputs);
    }

    /**
     * Prints the introductory message.
     */
    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm\n" + logo + "how may I help?");
    }

    /**
     * Prints the error message with formatting.
     *
     * @param e The exception to be printed.
     */
    public void error(Exception e) {
        this.print("\t[ERROR] " + e);
        this.print();
    }

    /**
     * Prints the error message with formatting, for a failed initialization.
     *
     * @param e The exception to be printed.
     */
    public void loadError(Exception e) {
        this.print("\t[ERROR] While loading, the following error occurred: \n\t" + e);
        this.print();
    }
}