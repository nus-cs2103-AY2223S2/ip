<<<<<<<< HEAD:src/main/java/james/task/Task.java
package james.task;
========
package seedu;
>>>>>>>> 9a38ae0 (Update testing classes for tasks):src/main/java/seedu/Task.java

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

<<<<<<<< HEAD:src/main/java/james/task/Task.java
    public void markDone() {
        this.isDone = true;
    }

    public void markUnDone() {
        this.isDone = false;
========
    public boolean setStatus(boolean isDone) {
        this.isDone = isDone;
        return isDone;
    }

    public boolean isDone() {
        return isDone;
>>>>>>>> 9a38ae0 (Update testing classes for tasks):src/main/java/seedu/Task.java
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
    public String toStoreString() {
        String isDoneIndicator = isDone ? "1" : "0";
        return isDoneIndicator + " | " + this.description;
    }


}


