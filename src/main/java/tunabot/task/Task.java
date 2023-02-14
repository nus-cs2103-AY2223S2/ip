package tunabot.task;

/**
 * Class to handle Tasks
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Initializes new Task with given name
     * @param name Name of Task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Initializes new Task with given name and status
     * @param name Name of Task
     * @param isDone Status of Task
     */
    public Task(String name, String isDone) {
        this.name = name;
        this.isDone = isDone.equals("true");
    }

    public String getName() {
        return name;
    }
    public boolean getDone() {
        return isDone;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Marks task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task that matches the format used in the save file
     * @return save file friendly format
     */
    public String saveFormat() {
        return "T;" + this.name + ";" + this.isDone;
    }
    @Override
    public String toString() {
        String box;
        if (isDone) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }
        return "[T]" + box + this.getName();
    }
}
