package duke.instruction;

import duke.customization.DisplayFormat;
import duke.exception.GeneralDukeException;
import duke.task.TaskList;

public class ListInstruction extends GeneralDukeInstruction{
    private static final DisplayFormat format = new DisplayFormat(50, 4);
    @Override
    public void run(TaskList list) throws GeneralDukeException {
        StringBuilder listContent = new StringBuilder();
        for (int i = 0; i < list.remainingTasks();i++) {
            listContent.append(i + 1).append(". ").append(list.getTask(i)).append("\n");
        }
        format.displayWithBar(String.valueOf(listContent));
    }
}
