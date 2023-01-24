package seedu.duke;

/**
 * Todo object class.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object with the description,
     *
     * @param description details of the Todo object
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new Todo object based on the user input in the description.
     * The description is the task name.
     *
     * @param taskList the TaskList to add the Todo object into
     * @param desc     String input for description of a Todo object
     */
    public static void createTodo(TaskList taskList, String desc) {
        Ui.addedTask();
        Todo todo = new Todo(desc);
        taskList.addTask(todo);
        Ui.indent("" + todo);
    }

    /**
     * Runs the Todo creation.
     * Checks the number of tasks in the list and prints it out.
     *
     * @param taskList    the TaskList to add the Todo object into
     * @param description String input for description of a Todo object with task name
     */
    public static void runTodo(TaskList taskList, String description) {
            createTodo(taskList, description);
            Ui.checkList(taskList);
    }

    /**
     * Saved file representation of a Todo object.
     *
     * @return String format of a Todo object in saved file form
     */
    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }

    /**
     * Represents a Todo object in the user interface.
     *
     * @return String format of a Todo object to be displayed
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
