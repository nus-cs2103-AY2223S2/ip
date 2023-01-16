package commands;

import java.util.function.Function;

public enum Command {
    GREET(input -> "Kon aqua~~"),
    BYE(input -> "Otsu aqua~~");


    private final Function<CommandInput, String> commandFunc;

    Command(Function<CommandInput, String> commandFunc) {
        this.commandFunc = commandFunc;
    }


    public String execute(CommandInput input) throws IllegalArgumentException {
        return commandFunc.apply(input);
    }
}
