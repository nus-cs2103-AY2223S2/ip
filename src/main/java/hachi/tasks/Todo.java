package hachi.tasks;

/**
 * Encapsulates a Task without a date.
 */
public class Todo extends Task {
    private static final String tag = "T";

    /**
     * Todo constructor.
     *
     * @param input The description of the task.
     */
    public Todo(String input) {
        super(input);
    }

    public boolean checkInput(Todo taskOne, Todo taskTwo) {
        return taskOne.input.equals(taskTwo.input);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo task = (Todo) o;
            if (checkInput(task, this)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Formats the task into a string to be saved locally
     *
     * @return A String of tasks with specified format.
     */
    public String saveTask() {
        String completed = this.isDone ? "1" : "0";
        return this.tag + " | " + completed + " | "
                + this.input;
    }

    /**
     * Returns the string representation of the Todo object.
     *
     * @return String representation of the Todo object.
     */
    public String toString() {
        return "   [T]" + super.toString();
    }
}
