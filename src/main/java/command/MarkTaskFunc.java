package command;

import java.util.function.Function;

import task.Task;


public class MarkTaskFunc implements Function<CommandInput, String> {
    @Override
    public String apply(CommandInput input) throws IllegalArgumentException {
        try {
            int taskNum = input.getMainInput()
                .map(numString -> Integer.parseInt(numString) - 1)
                .orElseThrow(() -> new IllegalArgumentException("Missing task number"));
            Task task = input.getMainManager().getTaskManager().mark(taskNum, true);
            return String.format("Nice! I've marked this task as done:\n  %s",
                task.toString()
            );
        } catch (NumberFormatException numEx) {
            throw new IllegalArgumentException("Task number must be an integer", numEx);
        } catch (IndexOutOfBoundsException oobEx) {
            throw new IllegalArgumentException("Task number out of bounds", oobEx);
        }
    }
}
