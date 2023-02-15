package colette.task;

import colette.exception.ColetteException;
import colette.gui.GuiText;

/** Class that represents a task */
public abstract class Task {

    private boolean isDone = false;
    private String name;

    /**
     * Constructs a Task object with the specified
     * name.
     *
     * @param name Name to give task.
     */
    public Task(String name) {
        this.name = name;
        assert this.name != null : "Name should not be null";
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Converts the task to a string representation
     * that can be saved in storage.
     *
     * @return String representation to be saved in storage.
     */
    public abstract String getFileRepresentation();

    protected String getName() {
        return this.name;
    };

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.name;
    }

    /**
     * Converts the string representation of a task from storage
     * to a Task object.
     *
     * @param stringRepresentation String representation of a task from storage.
     * @return Task object.
     */
    public static Task createTaskFromStringRepresentation(String stringRepresentation) {
        char typeOfTask = stringRepresentation.charAt(0);
        String[] details = stringRepresentation.split("@");
        Task newTask = null;
        switch (typeOfTask) {
        case 'T':
            newTask = new Todo(details[2]);
            break;
        case 'E':
            newTask = new Event(details[2], details[3], details[4]);
            break;
        case 'D':
            newTask = new Deadline(details[2], details[3]);
            break;
        default:
            break;
        }
        newTask.setDone(details[1].equals("1"));
        return newTask;
    }

    /**
     * Checks if the name of this task
     * contains the given keyword.
     *
     * @param keyword Keyword to check for.
     * @return true if name of this task contains the
     *         given keyword, otherwise false.
     */
    public boolean nameContainsKeyword(String keyword) {
        return this.name.contains(keyword);
    }

    /**
     * Creates a task using the arguments provided.
     *
     * @param taskType Type of task to create.
     * @param arguments Arguments to create the task with.
     * @return Created task.
     * @throws ColetteException If task cannot be created.
     */
    public static Task createTask(TaskType taskType, String ... arguments) throws ColetteException {
        String name = arguments[0];
        switch (taskType) {
        case TODO:
            return new Todo(name);
        case DEADLINE:
            String by = arguments[1];
            return new Deadline(name, by);
        case EVENT:
            String startDate = arguments[1];
            String endDate = arguments[2];
            return new Event(name, startDate, endDate);
        default:
            throw new ColetteException(GuiText.generateGenericErrorMessage());
        }
    }

}
