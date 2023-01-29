package duke.task;

/**
 * super class {@code Task} that encapsulates common attributes of a task:
 * description and isDone
 */
public class Task {
    /**
     * Description of task
     */
    protected String description;
    /**
     * Boolean value of whether task is done or not
     */
    protected boolean isDone;

    /**
     * Constructor method for {@code Task} class
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gives status of task. If marked completed, gives an X else blank.
     * @return String representation of isDone
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks task as not done yet
     */
    public void unMark(){
        this.isDone = false;
    }

    /**
     * Provides String representation of task
     * @return String representation of task
     */
    @Override
    public String toString() {
        String output = "[" + getStatusIcon() + "]" + " | "  +this.description;
        return output;
    }
}
