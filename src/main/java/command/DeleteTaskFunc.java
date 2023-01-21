package command;

import aqua.exception.IllegalSyntaxException;
import task.Task;

public class DeleteTaskFunc implements CommandFunction {
    @Override
    public String apply(CommandInput input) throws IllegalSyntaxException {
        try {
            int taskNum = input.getMainInput()
                .map(numString -> Integer.parseInt(numString) - 1)
                .orElseThrow(() -> new IllegalSyntaxException("Missing task number"));
            Task task = input.getMainManager().getTaskManager().delete(taskNum);
            return String.format(
                "Noted. I've removed this task:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list",
                task.toString(),
                input.getMainManager().getTaskManager().size()
            );
        } catch (NumberFormatException numEx) {
            throw new IllegalSyntaxException("Task number must be an integer", numEx);
        } catch (IndexOutOfBoundsException oobEx) {
            throw new IllegalSyntaxException("Task number out of bounds", oobEx);
        }
    }
}
