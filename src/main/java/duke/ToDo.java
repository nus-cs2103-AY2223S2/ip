package duke;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * Represents a to do task. Inherits from the Task class;
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a ToDo object after parsing an add todo task command.
     *
     * @param stringStream contains the add todo command to be parsed
     * @return a ToDo object from the given add todo command
     * @throws DukeException if the task description is empty
     */
    public static ToDo parseToDoCommand(Scanner stringStream) throws DukeException {
        String taskDesc = "";

        while (stringStream.hasNext()) {
            String temp = stringStream.nextLine();
            taskDesc += temp;
        }

        if (taskDesc.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a ToDo cannot be empty.");
        }

        ToDo newTask = new ToDo(taskDesc.trim());
        return newTask;
    }

    /**
     * Returns a ToDo object after parsing a ToDo's storage string produced by DateTimeParser's
     * dateTimeToStorageString or dateToStorageString that is split on the delimiter '/'. The format for the
     * ToDo's storage String is T/(isMarked)/(Description).
     *
     * @param parts includes "T", an integer isMarked where 1 represents marked, a task description
     * @return a ToDo object built from the parts array
     */
    public static ToDo parseToDoStringArray(String[] parts) {
        assert parts.length == 3;

        ToDo task = new ToDo(parts[2]);
        if (Integer.parseInt(parts[1]) == 1) {
            task.mark();
        }
        assert task != null;
        return task;
    }
}
