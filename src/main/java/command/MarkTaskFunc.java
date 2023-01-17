package command;

import exception.DukeIllegalArgumentException;
import task.Task;


public class MarkTaskFunc implements CommandFunction {
    private final boolean marker;

    public MarkTaskFunc(boolean marker) {
        this.marker = marker;
    }

    @Override
    public String apply(CommandInput input) throws DukeIllegalArgumentException {
        try {
            int taskNum = input.getMainInput()
                .map(numString -> Integer.parseInt(numString) - 1)
                .orElseThrow(() -> new DukeIllegalArgumentException("Missing task number"));
            Task task = input.getMainManager().getTaskManager().mark(taskNum, marker);
            return formMessage(task);
        } catch (NumberFormatException numEx) {
            throw new DukeIllegalArgumentException("Task number must be an integer", numEx);
        } catch (IndexOutOfBoundsException oobEx) {
            throw new DukeIllegalArgumentException("Task number out of bounds", oobEx);
        }
    }

    private String formMessage(Task task) {
        if (marker) {
            return String.format("Nice! I've marked this task as done:\n %s",
                task.toString()
            );
        } else {
            return String.format("OK, I've marked this task as not done yet:\n %s",
                task.toString()
            );
        }
    }
}
