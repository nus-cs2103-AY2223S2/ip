package duke;

import java.util.HashMap;

/**
 * The class that handles parsing of commands for Duke.
 */
public class Parser {
    /** The full command, including any descriptions/params. */
    private final String fullCommand;
    /** The base command. (eg. the "todo" in full command: "todo DESCRIPTION") */
    public final String baseCommand;
    /** 
     * The main body of the command; excluding any params.
     * (eg. the "DESCRIPTION" in "deadline DESCRIPTION /by 2023-01-23")
     */
    public final String body;
    /**
     * The params mapping in the command, indicated via /PARAM_NAME.
     * (eg. "deadline DESCRIPTION /by 2023-01-23" will have a entry with "by" as 
     * the key and "2023-01-23" as the value)
     */
    public final HashMap<String, String> namedParameters = new HashMap<>();

    /** 
     * Parses a command and break it down into its base command, body and 
     * params. 
     */
    public Parser(String command) {
        this.fullCommand = command;
        String[] temp = command.split(" +", 2);
        this.baseCommand = temp[0];
        String rawBody = temp.length > 1 ? temp[1].trim() : "";
        String body = "";

        boolean isFirstElement = true;
        for (String str : rawBody.split("\\s+/")) {
            if (isFirstElement) {
                isFirstElement = false;
                body = str;
                continue;
            }
            String[] temp2 = str.split(" +", 2);
            namedParameters.put(temp2[0], temp2.length > 1 ? temp2[1] : "");
        }
        this.body = body;
    }

    /**
     * Checks if the command's body is empty. (ie. command has no body)
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
