package duke;

import duke.command.Command;

/**
 * Utility class for parsing user input and command arguments.
 *
 * @see duke.Duke
 * @see duke.command.Command
 */
public class Parser {
  Command[] commands;

    /**
     * Creates a new Parser.
     *
     * @param commands The commands that the parser recognizes.
     */
    public Parser(Command[] commands) {
        this.commands = commands;
    }

    /**
     * Parses the specified command name and returns the corresponding command.
     * @param name The name of the command to be parsed.
     * @return The command corresponding to the specified name.
     */
    public Command parseCommand(String name) {
        for (Command cmd: commands) {
            if (name.equals(cmd.getName())) {
                return cmd;
            }
        }
        throw new IllegalArgumentException("Command not found: " + name);
    }

    /**
     * Parses the specified argument string and returns an array of arguments.
     * @param argPart The argument string to be parsed.
     * @param cmd The command that the arguments are for.
     * @return An array of arguments.
     */
    public static String[] parseArgs(String argPart, Command cmd) {
        return Parser.parseArgs(argPart, cmd.getParams());
    }

    /**
     * Parses the specified argument string and returns an array of arguments.
     * @param argPart The argument string to be parsed.
     * @param regexes The regular expressions to be used to split the argument string.
     * @return An array of arguments.
     */
    public static String[] parseArgs(String argPart, String[] regexes) {
        String[] outputs = new String[regexes.length + 1];
        for (int i = 0; i < regexes.length; i++) {
            String[] temp = argPart.split(regexes[i],2);
            if (temp.length > 1) {
                outputs[i] = temp[0];
                argPart = temp[1];
            } else {
                throw new IllegalArgumentException("Missing argument.");
            }
        }
        outputs[regexes.length] = argPart;
        return outputs;
    }
}
