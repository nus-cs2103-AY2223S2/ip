package duke;

import java.util.HashMap;

/**
 * The class that handles parsing of commands for Duke.
 */
public class Parser {
    /** The base command (eg. the "todo" in full command: "todo DESCRIPTION"). */
    public final String baseCommand;
    /**
     * The main body of the command; excluding any params. (eg. the "DESCRIPTION" in
     * "deadline DESCRIPTION /by 2023-01-23")
     */
    public final String body;
    /**
     * The params mapping in the command, indicated via /PARAM_NAME. (eg. "deadline
     * DESCRIPTION /by 2023-01-23" will have a entry with "by" as the key and
     * "2023-01-23" as the value)
     */
    public final HashMap<String, String> namedParameters = new HashMap<>();
    /** The full command, including any descriptions/params. */
    private final String fullCommand;

    /**
     * Parses a command and break it down into its base command, body and params.
     */
    public Parser(String command) {
        this.fullCommand = command;
        String[] splittedCommand = command.split(" +", 2);
        this.baseCommand = splittedCommand[0];
        String rawBody = splittedCommand.length > 1 ? splittedCommand[1].trim() : "";
        String body = "";

        boolean isFirstElement = true;
        for (String str : rawBody.split("/")) {
            if (isFirstElement) {
                isFirstElement = false;
                body = str.trim();
                continue;
            }
            String[] splittedParam = str.split(" +", 2);
            namedParameters.put(splittedParam[0], splittedParam.length > 1 ? splittedParam[1] : "");
        }
        this.body = body;
    }

    /**
     * Checks if the command's body is empty (ie. command has no body).
     * 
     * @return Whether the command's body is empty.
     */
    public boolean hasEmptyBody() {
        return this.body.isEmpty();
    }

    @Override
    public String toString() {
        return fullCommand;
    }
}
