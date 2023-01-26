package util;

import duke.DeadlineTask;
import duke.Event;
import duke.ToDo;

public class Parser {
    public static boolean handleGeneralCommand(String command, TaskList tasks) throws DukeException {
        if (command.startsWith("list")) {
            tasks.listTasks();
        } else if ((command.startsWith("mark")) || (command.startsWith("unmark")) ||
                command.startsWith("delete")) {
            tasks.manageTask(command);
        } else if (command.equals("bye")) {
            tasks.saveTaskList();
            return false;
        } else if (command.startsWith("event")){
            Event.createEvent(command, tasks);
        } else if (command.startsWith("deadline")) {
            DeadlineTask.createDeadlineTask(command, tasks);
        } else if (command.startsWith("todo")) {
            ToDo.createToDo(command, tasks);
        }
        else {
            throw new DukeException();
        }
        return true;
    }
}
