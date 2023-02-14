package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.Task;
import duke.task.TaskList;

public class HandleDelete {
    public HandleDelete() {
    }

    public static String performDelete(String input, TaskList tasklist) throws WrongFormatException {
        boolean correctFormat = input.equals("delete\\s\\d+");

        if (!correctFormat) {
            throw new WrongFormatException("delete 'Task Number'");
        }

        try {
            int taskNum = Integer.parseInt(input.substring(7)) - 1;
            Task taskToRemove = tasklist.getTask(taskNum);
            String removedTaskStr = taskToRemove.toString();
            tasklist.deleteTask(taskNum);
            return "Noted. I've removed this task: \n  " + removedTaskStr
                    + "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Please enter a valid task number!";
        }
    }

}
