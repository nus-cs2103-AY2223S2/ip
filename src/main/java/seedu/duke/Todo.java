package seedu.duke;

/**
 * Todo object class.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object with the description,
     *
     * @param description details of the Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new Todo object based on the user input in the description.
     * The description is the task name.
     *
     * @param taskList the TaskList to add the Todo object into.
     * @param description     String input for description of a Todo object.
     *
     * @return String for Todo creation.
     */
    public static String createTodo(TaskList taskList, String description) {
        Todo todo = new Todo(description);
        taskList.addTask(todo);
        return Ui.addedTask() + Ui.indent(todo.toString());
    }

    /**
     * Runs the Todo creation.
     * Checks the number of tasks in the list and prints it out.
     *
     * @param taskList    the TaskList to add the Todo object into.
     * @param description String input for description of a Todo object with task name.
     *
     * @return String for Todo creation.
     */
    public static String runTodo(TaskList taskList, String description) {
        return createTodo(taskList, description) + "\n" + Ui.checkList(taskList);
    }

    /**
     * Runs the Todo read.
     *
     * @param s The array of strings to be read.
     *
     * @return String for Todo creation.
     */
    public static Task readTodo(String[] s) {
        String todoName = s[2].substring(1);
        Todo todo = new Todo(todoName);
        if (s[1].charAt(1) == '1') {
            todo.toMark(true);
        }
        return todo;
    }

    /**
     * Represents the saved file format of a Todo object.
     *
     * @return String format of a Todo object in saved file form.
     */
    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }

    /**
     * Represents a Todo object in the user interface.
     *
     * @return String format of a Todo object to be displayed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
