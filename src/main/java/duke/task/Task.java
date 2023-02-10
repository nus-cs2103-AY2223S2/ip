package duke.task;

/**
 * Abstract class for task representation.
 */
public abstract class Task {
    private String place;
    private String description;
    private TaskType typeOfTask;
    private boolean isDone;

    /**
     * Creates a task.
     *
     * @param place The place where the task will occur.
     * @param description The description of the task.
     * @param typeOfTask  The type of task.
     */
    public Task(String description, String place, TaskType typeOfTask) {
        this.description = description;
        this.place = place;
        this.typeOfTask = typeOfTask;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public TaskType getTypeOfTask() {
        return typeOfTask;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s @ %s", getStatusIcon(), description, place);
    }
}
