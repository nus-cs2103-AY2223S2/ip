package duke.command;

import java.util.function.Function;


public class ArgCommand extends Command {
    private final Function<String[], String[]> function;

    public ArgCommand(String name, String helpStr, String[] params, Function<String[], String[]> function) {
        super(name, helpStr, true, params);
        this.function = function;
    }

    @Override
    public String[] execute(String[] args) {
        return function.apply(args);
    }
}