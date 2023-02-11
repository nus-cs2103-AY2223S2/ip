package duke.task;

import duke.exception.DukeException;
import duke.TaskList;

/**
 * Class contains variables and methods related to Todo task.
 */
public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    public static void processTodo(String command, TaskList lst) throws DukeException {
        String taskName = command.trim();
        if (taskName.isEmpty()) {
            throw new DukeException("todo");
        } else {
            Todo todo = new Todo(taskName);
            lst.addTask(todo);
        }
    }

    @Override
    public String toFile() {
        return String.format("T | %s\n", super.toFile());
    }

    public static Todo toTodoFromFileStr(String taskNameData, String doneData) throws DukeException {
        doneData = doneData.trim();
        taskNameData = taskNameData.trim();
        if (taskNameData.isEmpty()) {
            throw new DukeException("todo");
        }
        if (doneData.isEmpty()) {
            throw new DukeException("missing details");
        }
        Todo t = new Todo(taskNameData);
        boolean completed = Integer.parseInt(doneData) == 1;
        t.setCompleted(completed);
        return t;
    }

    @Override
    public String toString() {
        String s;
        if (this.isCompleted) {
            s = "[T]" + super.toString();
        } else {
            s = "[T]" + super.toString();
        }
        return s;
    }
}
