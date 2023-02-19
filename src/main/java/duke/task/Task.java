package duke.task;

import duke.tag.Tag;

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
     * Tag assigned to task
     */
    protected Tag tag;

    /**
     * Constructor method for {@code Task} class
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = new Tag("");
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
        String output = "[" + getStatusIcon() + "]" + " | " + this.description;
        return output;
    }

    /**
     * Adds a tag to task
     * @param tag tag to be added to task
     */
    public void addTag(Tag tag){
        this.tag = tag;
    }

    /**
     * Removes tag from task
     */
    public void removeTag(){
        TaskList.updateTagLibrary(this.tag);
        this.tag = new Tag("");
    }

    /**
     * Checks if task has been tagged
     * @return boolean value of whether the task is tagged or not
     */
    public boolean hasTag(){
        return this.tag.toString() == "#";
    }
}
