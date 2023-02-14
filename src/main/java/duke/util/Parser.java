package duke.util;

import duke.command.Command;
import duke.command.NestCommand;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for parsing user input, command arguments, and subcommands.
 *
 * @see duke.Duke
 * @see duke.command.Command
 */
public class Parser {
    private final HashMap<String, Command> baseCommandTokens;

    /**
     * Creates a new Parser.
     *
     * @param commands The commands that the parser recognizes.
     */
    public Parser(Command[] commands) {
        this.baseCommandTokens = Command.makeCommandMap(commands);
    }

    /**
     * Given a list of input-words, and a command with subcommands, attempts to parse base arguments to their
     * commands and recursively parse subcommand arguments to their subcommands.
     *
     * @param words         the input, as whitespace-delimited words
     * @param baseCmd       the base or root command
     */
    public Function<Stateful, Stateful> parseArgs(Queue<String> words, NestCommand baseCmd) {
        Set<String> subCommandTokens = baseCmd.getSubCommands().keySet();

        HashMap<String, Queue<String>> subCommandMap = Parser.extractTokenArgs(subCommandTokens, words);

        return getComposedStateOperation(baseCmd, subCommandMap);
    }

    private Function<Stateful, Stateful> getComposedStateOperation(NestCommand baseCmd, HashMap<String, Queue<String>> subCommandMap) {
        Function<Stateful, Stateful> baseFunction = baseCmd.getFurnishedFunction(subCommandMap.remove(""));

        for (Map.Entry<String, Queue<String>> entry : subCommandMap.entrySet()) {
            String token = entry.getKey();
            Queue<String> input = entry.getValue();
            Command subCommand = baseCmd.getSubCommands().get(token);
            Function<Stateful, Stateful> subFunction;
            if (subCommand.hasSubCommands()) {
                subFunction = parseArgs(input, (NestCommand) subCommand);
            } else {
                subFunction = subCommand.getFurnishedFunction(input);
            }
            baseFunction = baseFunction.andThen(subFunction);
        }
        return baseFunction;
    }

    /*private static Queue<String> extractCommandWords(Queue<String> words, Set<String> otherTokens) {
        if (otherTokens == null) {
            return words;
        }
        Queue<String> subCommandWords = new LinkedList<>();
        while (!words.isEmpty()) {
            String word = words.peek();
            if (otherTokens.contains(word)) {
                break;
            }
            subCommandWords.add(words.poll());
        }
        return subCommandWords;
    }*/

    /**
     * Given a list of input-words, and a set of tokens, attempts to parse arguments to their respective tokens.
     * The parsing is independent of the order of the tokens.
     *
     * @param tokens            the tokens to parse
     * @param words             the input, as whitespace-delimited words
     * @param allowOptional     whether arguments are allowed to be blank
     * @return                  a map of tokens to their respective arguments
     */
    public static HashMap<String, Queue<String>> extractTokenArgs(Set<String> tokens, Queue<String> words, boolean allowOptional) {
        HashMap<String, Queue<String>> outputs = new HashMap<>();
        String prevToken = "";
        Set<String> unmatchedTokens = new HashSet<>(tokens);
        unmatchedTokens.add(prevToken);
        Queue<String> subWords = new LinkedList<>();
        while (!words.isEmpty()) {
            String word = words.poll();
            if (unmatchedTokens.contains(word)) {
                if (!subWords.isEmpty()) {
                    outputs.put(prevToken, subWords);
                    unmatchedTokens.remove(prevToken);
                }
                prevToken = word;
                subWords = new LinkedList<>();
            } else {
                subWords.add(word);
            }
        }
        if (allowOptional || !subWords.isEmpty()) {
            outputs.put(prevToken, subWords);
            unmatchedTokens.remove(prevToken);
        }
        boolean isMissingOne = !unmatchedTokens.isEmpty() || subWords.isEmpty() || outputs.get("") == null;
        boolean isMissingAll = outputs.isEmpty();
        if (isMissingAll || (!allowOptional && isMissingOne)) {
            if (unmatchedTokens.remove("")){
                unmatchedTokens.add("<base argument>");
            }
            throw new IllegalArgumentException("Missing argument for: " + unmatchedTokens);
        }
        return outputs;
    }

    public static HashMap<String, Queue<String>> extractTokenArgs(Set<String> tokens, Queue<String> words) {
        return extractTokenArgs(tokens, words, true);
    }

    /**
     * Given a list of input-words, and a set of tokens, attempts to parse arguments to their respective tokens.
     * This differs from {@link #extractTokenArgs(Set, Queue)} in that it joins the argument-lists into a single string.
     *
     * @param tokens            the tokens to parse
     * @param words             the input, as whitespace-delimited words
     * @return                  a map of tokens to their respective arguments
     */
    public static HashMap<String, String> extractTokensWithJoin(Set<String> tokens, Queue<String> words) {
        return extractTokenArgs(tokens, words, false).entrySet().stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        e -> String.join(" ", e.getValue()),
                        (a, b) -> b,
                        HashMap::new
                )
        );
    }

    /**
     * Parses the specified command name and returns the corresponding command.
     *
     * @param name The name of the command to be parsed.
     * @return The command corresponding to the specified name.
     */
    public Command parseCommand(String name, HashMap<String, String> aliases) {
        if (baseCommandTokens.containsKey(name)) {
            return baseCommandTokens.get(name);
        }
        if (aliases.containsKey(name)) {
            return baseCommandTokens.get(aliases.get(name));
        }
        for (Command command : baseCommandTokens.values()) {
            if (command.getName().contains(name)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Command not found: " + name);
    }
}
