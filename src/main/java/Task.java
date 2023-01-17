public class Task {
    private final String description;
    private final boolean done;

    /*
        default constructor for Task
        this should not be used by the client
     */
    private Task(String description, boolean isDone) {
        this.description = description;
        this.done = isDone;
    }

    // init Task object by client
    public Task(String description) {
        this(description, false);
    }

    // checking if the Task object is done
    public boolean isDone () {
        return this.done;
    }

    public String getStatusCheckbox() {
        String taskStatus = (this.done) ? "[X]" : "[]";
        return taskStatus;
    }

    // returns new Task that is marked as done
    public Task markTask() {
        System.out.println("Nice! I've marked this task as done:");
        Task markedTask = new Task(this.description, true);
        System.out.println(String.format("%s %s", markedTask.getStatusCheckbox(), this.description));
        return markedTask;
    }

    // returns new Task that is marked as undone
    public Task unmarkTask() {
        System.out.println("Ok, I've marked this task as not done yet:");
        Task unmarkedTask = new Task(this.description, false);
        System.out.println(String.format("%s %s", unmarkedTask.getStatusCheckbox(), this.description));
        return unmarkedTask;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
