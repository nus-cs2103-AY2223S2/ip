package duke;

import util.DukeException;
import util.TaskList;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(String taskName, boolean completion) {
        super(taskName, completion);
    }


    public static void createToDo(String command, TaskList t) throws DukeException {
        String[] input = command.split(" ");
        if (input.length <= 1) throw new DukeException("todo");
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            taskName.append(input[i]);
            if (i < input.length - 1) {
                taskName.append(" ");
            }
        }
        ToDo todo = new ToDo(taskName.toString());
        t.addTask(todo);
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }

}
