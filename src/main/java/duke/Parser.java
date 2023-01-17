package duke;

import duke.command.Command;

public class Parser {
    Command[] commands;

    public Parser(Command[] commands) {
        this.commands = commands;
    }

    public Command parseCommand(String name) {
        for (Command cmd: commands) {
            if (name.equals(cmd.getName())) {
                return cmd;
            }
        }
        throw new IllegalArgumentException("Command not found: " + name);
    }

    public static String[] parseArgs(String argPart, Command cmd) {
        return Parser.parseArgs(argPart, cmd.getParams());
    }

    public static String[] parseArgs(String argPart, String[] regexes) {
        String[] outputs = new String[regexes.length + 1];
        for (int i = 0; i < regexes.length; i++) {
            String[] temp = argPart.split(regexes[i],2);
            if (temp.length > 1){
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
