import Tasks.Task;
import Tasks.ToDo;

import java.io.IOException;

public class CommandToDo extends Command{
    @Override
    public void handle() throws DukeException {
        try {
            String toDoName = Duke.userScan.nextLine();
            // ERROR: To-Do description is blank.
            if (toDoName.strip().length()==0) {
                throw new DukeException(Duke.ui.formatLogicError("Tasks.ToDo description cannot be blank."));
            }
            Task taskToAdd = new ToDo(toDoName);
            Duke.taskList.add(new ToDo(toDoName));
            Duke.ui.print("Task added:\n " + taskToAdd + "\n" + "There are now " + Duke.taskList.size() +
                    " task(s) in your list.");
            Duke.dukeSave.saveTaskList(Duke.taskList);
        }
        catch (IOException err) {
            throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
        }
    }
}
