package kude.processor;

import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Parser for command line
 */
public class Parser {
    private static final String NAMED_ARGUMENT_REGEX = "\\s/(\\w+)\\s(.+?)(?=\\s/\\w+\\s.+|$)";

    private final String line;
    private final String cmd;
    private final String arg;
    private final HashMap<String, String> namedArgs;

    /**
     * Creates a parser from a String
     * @param line command line
     */
    public Parser(String line) {
        this.line = line;
        this.cmd = line.split("\\s")[0];
        this.arg = line.substring(cmd.length()).split(NAMED_ARGUMENT_REGEX, 2)[0].trim();

        this.namedArgs = new HashMap<>();
        var matcher = Pattern.compile(NAMED_ARGUMENT_REGEX).matcher(line);
        while (matcher.find()) {
            var name = matcher.group(1).trim();
            var value = matcher.group(2).trim();
            this.namedArgs.put(name, value);
        }
    }

    /**
     * Gets the command
     */
    public String getCommand() {
        return cmd;
    }

    /**
     * Gets the original command line
     */
    public String getLine() {
        return line;
    }

    /**
     * Gets the main argument
     */
    public Optional<String> getArg() {
        return arg.isEmpty() ? Optional.empty() : Optional.of(arg);
    }

    /**
     * Gets a named argument
     */
    public Optional<String> getNamedArg(String name) {
        return Optional.ofNullable(this.namedArgs.get(name));
    }
}
