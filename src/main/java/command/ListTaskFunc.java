package command;

import java.util.function.Function;

public class ListTaskFunc implements Function<CommandInput, String> {
    @Override
    public String apply(CommandInput input) throws IllegalArgumentException {
        return String.format("Here are the tasks in your list:\n%s",
            input.getMainManager().getTaskManager().toString()
        );
    }
}
