package duke;

import java.util.HashMap;

public class Parser {
    private final String fullCommand;
    public final String baseCommand;
    public final String body;
    public final HashMap<String, String> namedParameters = new HashMap<>();

    public Parser(String command) {
        this.fullCommand = command;
        String[] splittedCommand = command.split(" +", 2);
        this.baseCommand = splittedCommand[0];
        String rawBody = splittedCommand.length > 1 ? splittedCommand[1].trim() : "";
        String body = "";

        boolean isFirstElement = true;
        for (String str : rawBody.split("\\s+/")) {
            if (isFirstElement) {
                isFirstElement = false;
                body = str;
                continue;
            }
            String[] splittedParam = str.split(" +", 2);
            namedParameters.put(splittedParam[0], splittedParam.length > 1 ? splittedParam[1] : "");
        }
        this.body = body;
    }

    public boolean hasEmptyBody() {
        return this.body.isEmpty();
    }

    @Override
    public String toString() {
        return fullCommand;
    }
}
