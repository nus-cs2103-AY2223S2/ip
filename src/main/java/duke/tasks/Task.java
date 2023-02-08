package duke.tasks;

public class Task {
    public final String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
      this.isDone = true;
    }
    
    public void unmark() {
      this.isDone = false;
    }

    @Override
    public String toString() {    
      return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

