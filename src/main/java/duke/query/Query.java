package duke.query;

import java.util.HashMap;

/**
 * Query represents a query to the bot.
 */
public class Query {
    private final String command;
    private String param = "";
    private final HashMap<String, String> arguments = new HashMap<String, String>();

    public Query(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParam() {
        return this.param;
    }

    public String getArgument(String key) {
        return arguments.get(key);
    }

    public void setArgument(String key, String val) {
        arguments.put(key, val);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder argStr = new StringBuilder();
        arguments.forEach((key, value) -> argStr.append(String.format(" %s:%s", key, value)));
        return String.format("%s : %s%s", command, param, argStr);
    }
}
