package Duke.Tasks;

import java.util.ArrayList;

/**
 * Abstract class representing a task.
 * @author Bryan Juniano
 */

public abstract class Task {
    private String name;
    private boolean isDone;
    private ArrayList<String> tags = new ArrayList<String>();

    /**
     * Constructor for task.
     * @param name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Getter for task isDone status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks task.
     */
    public void unMark() {
        this.isDone = false;
    }

    public String getTags() {
        String output = "";
        for(int i = 0; i< tags.size(); i++) {
            output+="[" + tags.get(i)+"] ";
        }
        output.strip();
        return output;
    }

    public void setTags(ArrayList<String> tags) {
        for(int i = 0; i<tags.size(); i++) {
            if(!this.tags.contains(tags.get(i))) {
                this.tags.add(tags.get(i));
            }
        }
    }

    public void removeTags(ArrayList<String> tags) {
        for(int i = 0; i<tags.size(); i++) {
            this.tags.remove(tags.get(i));
        }
    }

    /**
     * Generates the string representation of the task.
     */
    @Override
    public String toString() {
        if(this.isDone) {
            return "[X] | " + this.name + " | " + this.getTags();
        }
        else {
            return "[ ] | " + this.name + " | " + this.getTags();
        }
    }
}