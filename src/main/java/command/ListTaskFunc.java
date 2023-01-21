package command;

import aqua.exception.IllegalSyntaxException;

public class ListTaskFunc implements CommandFunction {
    @Override
    public String apply(CommandInput input) throws IllegalSyntaxException {
        return String.format("Here are the tasks in your list:\n%s",
            input.getMainManager().getTaskManager().toString()
        );
    }
}
