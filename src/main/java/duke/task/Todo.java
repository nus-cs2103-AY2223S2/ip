package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;

/**
 * Class contains variables and methods related to Todo task.
 */
public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toFile() {
        return String.format("T | %s\n", super.toFile());
    }

    @Override
    public boolean isTaskInSchedule(LocalDate date) {
        return false;
    }

    /**
     * Creates an instance of Todo from parsed String in file.
     * @param taskNameData String containing task name.
     * @param doneData String containing whether task is completed.
     * @return An instance of Todo.
     * @throws DukeException if any of the inputs are empty.
     */
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
