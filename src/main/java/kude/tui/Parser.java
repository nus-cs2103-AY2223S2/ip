package kude.tui;

import kude.DukeException;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    private String line;
    private String cmd;
    private String arg;
    private HashMap<String, String> namedArgs;

    private final static String ARG_FMT = "/(\\w+)\\s([^/]*)";

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

    public String getCommand() {
        return cmd;
    }

    public String getLine() {
        return line;
    }

    public Optional<String> getArg() {
        return arg.isEmpty() ? Optional.empty() : Optional.of(arg);
    }

    public Optional<String> getNamedArg(String name) {
        return Optional.ofNullable(this.namedArgs.get(name));
    }
}
