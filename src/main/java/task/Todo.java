package task;

import dukeexception.DukeException;

/**
 * Todo class to deal with todo tasks.
 */
public class Todo extends Task {

    /**
     * Class constructor.
     *
     * @param description The description of the task.
     * @param isDone      Marks or unmarks the task.
     */
    private Todo(String description, boolean isDone) {
        super(description, isDone);
    }


    /**
     * Returns todo task based on the user input.
     *
     * @param input User Input.
     * @return Todo task.
     * @throws DukeException Checks the validation of input.
     */
    public static Todo generate(String input) throws DukeException {
        if (input.trim().equals("todo")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
        String[] inputLine = input.split(" ", 2);
        if (inputLine.length < 2) {
            throw new DukeException("\t ☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
        return new Todo(inputLine[1], false);
    }

    /**
     * Returns a todo task from the file.
     *
     * @param taskLine Each line from the input file.
     * @return Todo task.
     */
    public static Todo generateTask(String[] taskLine) {
        boolean isDone = taskLine[1].equals("1");
        return new Todo(taskLine[2], isDone);
    }
    @Override
    public String getTaskType() {
        return "T";
    }
    @Override
    public String storeTaskString() {
        return this.getTaskType() + " | " + this.getMarkedString() + " | " + this.getDescription();
    }
    @Override
    public String toString() {
        boolean checked = this.isDone();
        String str = this.getDescription();
        if (checked) {
            return "[T][X] " + str;
        } else {
            return "[T][ ] " + str;
        }
    }
}