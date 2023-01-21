package command;

import aqua.exception.IllegalSyntaxException;

@FunctionalInterface
public interface CommandFunction {
    public String apply(CommandInput input) throws IllegalSyntaxException;
}
