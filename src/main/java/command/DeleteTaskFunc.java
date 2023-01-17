package command;

import java.util.function.Function;

import task.Task;

public class DeleteTaskFunc implements Function<CommandInput, String> {
    @Override
    public String apply(CommandInput input) {
        try {
            int taskNum = input.getMainInput()
                .map(numString -> Integer.parseInt(numString) - 1)
                .orElseThrow(() -> new IllegalArgumentException("Missing task number"));
            Task task = input.getMainManager().getTaskManager().delete(taskNum);
            return String.format(
                "Noted. I've removed this task:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list",
                task.toString(),
                input.getMainManager().getTaskManager().size()
            );
        } catch (NumberFormatException numEx) {
            throw new IllegalArgumentException("Task number must be an integer", numEx);
        } catch (IndexOutOfBoundsException oobEx) {
            throw new IllegalArgumentException("Task number out of bounds", oobEx);
        }
    }
}
