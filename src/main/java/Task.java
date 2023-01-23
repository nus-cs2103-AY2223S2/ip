import java.time.LocalDate;

public abstract class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getTaskStatus() {
        //Done tasks are marked with X
        return (isDone ? "[X]" : "[ ]");
    }

    //Mark current task as done
    public boolean markAsDone() {
        //Only mark as done if task is not done
        if (!this.isDone) {
            this.isDone = true;
            return true;
        }
        return false;
    }

    //Mark current task as undone
    public boolean markAsUndone() {
        //Only remark as not done if task is done
        if (this.isDone) {
            this.isDone = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getTaskStatus() + " " + this.taskDescription;
    }

    public abstract boolean isToday(LocalDate date);
}
