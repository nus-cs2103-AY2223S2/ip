package duke.task;

public class Task {

    String name;
    boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }

    /**
     * Marks a Task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks a Task as uncompleted.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isRelated(String name) {
        return this.name.contains(name);
    }

    @Override
    public String toString() {
        if (isDone) {
            return "1" + " | " + this.name;
        } else {
            return "0" + " | " + this.name;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Task) {
            Task x = (Task) obj;
            if(this.name.equals(x.name)) {
                return true;
            }
        }
        return false;
    }

}
