package command;

import java.util.function.Function;

public class ListTaskFunc implements Function<CommandInput, String>{
    @Override
    public String apply(CommandInput input) throws IllegalArgumentException {
        return input.getMainManager().getTaskManager().toString();
    }
}
