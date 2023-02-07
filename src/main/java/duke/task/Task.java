package duke.task;

public class Task {
    public String taskString;
    public boolean isCompleted;

    public Task(String taskString) {
        this.taskString = taskString;
        this.isCompleted = false;
    }

    public String currentTaskStatus() {
        if(this.isCompleted) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public void markTask() {
        this.isCompleted = true;
    }

    public void unmarkTask() {
        this.isCompleted = false;
    }

    public String getTaskType() {
        return this.toString().substring(1, 2);
    }

    public String getTask() {
        return " ";
    }

    public String toString() {
        return this.currentTaskStatus() + " " + this.taskString;
    }
}
