package kira.task;

/**
 * Task represents all possible task.
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs an abstract task with a description.
     *
     * @param description
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as incompleted
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Formats the task into saveload readable format.
     *
     * @return a newly formatted string
     */
    public String saveFormat() {
        String[] saveString = new String[] {this.description, this.isDone ? "y" : "n"};
        return String.join("\",\"", saveString);
    }

    /**
     * Checks if the task description contains the keyword.
     *
     * @param keyword the keyword to search for
     * @return true if this task contains keyword, false otherwise
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        String done = this.isDone ? "x" : " ";
        sBuilder.append("[" + done + "] ")
                .append(this.description);
        return sBuilder.toString();
    }

}
