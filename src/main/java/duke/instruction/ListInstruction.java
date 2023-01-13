package duke.instruction;

import duke.customization.DisplayFormat;
import duke.exception.GeneralDukeException;
import duke.task.TaskList;

public class ListInstruction extends GeneralDukeInstruction{
    @Override
    public void run(TaskList list) throws GeneralDukeException {
        StringBuilder listContent = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.remainingTasks();i++) {
            listContent.append(i + 1).append(".").append(list.getTask(i)).append("\n");
        }
        format.displayWithBar(String.valueOf(listContent));
    }
}
