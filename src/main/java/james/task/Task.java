package james.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
    public String toStoreString() {
        String isDoneIndicator = isDone ? "1" : "0";
        return isDoneIndicator + " | " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return description.equals(((Task) obj).description) && isDone == ((Task) obj).isDone;
        }
        return false;
    }


    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

}



