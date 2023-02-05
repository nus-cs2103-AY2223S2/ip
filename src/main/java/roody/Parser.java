package roody;

import java.util.Arrays;

public class Parser {
    static String[] commands;
    public Parser(){}
    public static String[] parse(String command) throws RoodyException {
        // splits once by whitespace to filter by first input
        commands = command.toLowerCase().split("\\s", 2);
        if (commands[0].equals("event") || commands[0].equals("deadline")) {
            // split by /
            String[] splitCmd = command.split("/", 0);

            // case of no extra input
            if (splitCmd.length == commands.length) {
                throw new RoodyException("No date was detected! - /by {date} /from {date} /to {date}");
            }

            // seperates command from description
            commands = new String[splitCmd.length+1];
            for (int i = 1; i < splitCmd.length; i++) {
                commands[i + 1] = splitCmd[i];
            }
            String[] cmdAndDesc = splitCmd[0].split("\\s", 2);
            commands[0] = cmdAndDesc[0];
            commands[1] = cmdAndDesc[1];

            // seperates additional inputs
            commands[2] = splitCmd[1].split("\\s", 0)[1];
            if (commands[0].equals("event")) {
                commands[3] = splitCmd[2].split("\\s", 0)[1];
            }
        }
        for (String str : commands) {
            str.trim();
        }
        return commands;
    }
}
