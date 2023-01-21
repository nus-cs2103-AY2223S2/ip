package command;

import aqua.exception.DukeIllegalArgumentException;

public class ListTaskFunc implements CommandFunction {
    @Override
    public String apply(CommandInput input) throws DukeIllegalArgumentException {
        return String.format("Here are the tasks in your list:\n%s",
            input.getMainManager().getTaskManager().toString()
        );
    }
}
