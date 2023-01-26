package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    
    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String getTiming() {
        return "No timing";
    }

    /**
     * Method to check if the description of
     * the task contains the word that the user
     * is searching for.
     * @param word Search term of Find
     * @return True if the word exists in the description.
     */
    public boolean hasWord(String word) {
        return false;
    }

    public String toString() {
        return "";
    }
}
