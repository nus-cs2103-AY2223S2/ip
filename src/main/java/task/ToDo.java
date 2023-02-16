package task;

import dukeexception.DukeException;

/**
 * Handles all toDos.
 */
public class ToDo extends Task {
    /**
     * Private constructor.
     *
     * @param str Task name.
     * @param checked Boolean for whether current task is marked.
     */
    private ToDo(String str, boolean checked) {
        super(str, checked);
    }

    /**
     * Factory method that returns toDo task from user input.
     *
     * @param inputLine User input.
     * @return ToDo task.
     * @throws DukeException Checks valid user input.
     */
    public static ToDo generate(String[] inputLine) throws DukeException {
        if (inputLine.length < 2) {
            throw new DukeException("Missing description");
        }
        return new ToDo(inputLine[1], false);
    }

    /**
     * Factory method that returns toDo task from saved file.
     *
     * @param taskLine Input from saved file.
     * @return ToDo task.
     */
    public static ToDo generateTask(String[] taskLine) {
        boolean check = taskLine[1].equals("1");
        return new ToDo(taskLine[2], check);
    }
    @Override
    public String getTaskType() {
        return "T";
    }
    @Override
    public String getStoreTaskString() {
        return this.getTaskType() + " | " + this.getCheckedString() + " | " + this.getTask();
    }
    @Override
    public String toString() {
        boolean checked = this.isChecked();
        String str = this.getTask();
        if (checked) {
            return "[T][X] " + str;
        } else {
            return "[T][ ] " + str;
        }
    }
}
