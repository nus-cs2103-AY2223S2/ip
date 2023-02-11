package kude.tui;

import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Parser for command line
 */
public class Parser {
    private final String line;
    private final String cmd;
    private final String arg;
    private final HashMap<String, String> namedArgs;

    private final static String ARG_FMT = "\\s/(\\w+)\\s(.+?)(?=\\s/\\w+\\s.+|$)";

    /**
     * Creates a parser from a String
     * @param line command line
     */
    public Parser(String line) {
        this.line = line;
        this.cmd = line.split("\\s")[0];
        this.arg = line.substring(cmd.length()).split(ARG_FMT, 2)[0].trim();

        this.namedArgs = new HashMap<>();
        var matcher = Pattern.compile(ARG_FMT).matcher(line);
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
