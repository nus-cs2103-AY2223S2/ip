package duke.commands;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.Duke;

/**
 * Class for parsing the input from a user into tokens that commands can use.
 */
public abstract class Parser implements BiConsumer<String, Duke> {
    private final Map<String, Command> store;

    public Parser(Map<String, Command> store) {
        this.store = store;
    }

    public Parser(Stream<Command> cmds) {
        this(cmds.collect(
                Collectors.toUnmodifiableMap(Command::getLabel, Function.identity())
            ));
    }

    public abstract void output(String string);

    public void output(String string, Object ...args) {
        output(String.format(string, args));
    }

    /**
     * This method is called every time the user enters an invalid command string
     * @param input Raw string as entered by the user
     * @param instance Instance of Duke to run the command with
     */
    public void onUnknownCommand(String input, final Duke instance) {
        output("Unknown command '%s' :(", input);
    }

    /**
     * Try to execute the command as specified by the input string
     * @param input Raw string as entered by the user
     * @param instance Instance of Duke to run the command with
     */
    public void executeCommand(String input, final Duke instance) {
        String[] tokens = input.split(" ");
        Command cmd = this.store.getOrDefault(tokens[0].toLowerCase(), null);

        if (cmd != null) {
            cmd.accept(tokens, instance);
        } else {
            onUnknownCommand(input, instance);
        }
    }

    @Override
    public void accept(String input, final Duke instance) { 
        executeCommand(input, instance);
    }
}
