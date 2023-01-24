package presenter;

import command.CommandFactory;
import interfaces.Command;
import interfaces.CommandEventListener;

import java.util.List;

public class InputParser {
    private final List<CommandEventListener> listeners;
    private final CommandFactory commandFactory;;
    InputParser(List<CommandEventListener> listeners, CommandFactory commandFactory) {
        this.listeners = listeners;
        this.commandFactory = commandFactory;
    }

    Command parseInput(String input) {
        if (input.equals("bye")) {
            for (CommandEventListener listener : listeners) {
                listener.onCommandEvent(input);
            }
        }
        return commandFactory.createCommand(input);
    }
}
