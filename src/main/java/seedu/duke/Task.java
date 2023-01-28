package seedu.duke;

public class Task {

    private int id;
    private String task;
    private boolean done;

    public Task(int id, String task) {
        this.id = id;
        this.task = task;
        this.done = false;
    }

    public String printTask() {
        return done ? "[x] " + task: "[ ] " + task;
    }

    public void markDone(int index) {
        this.done = true;
        System.out.println("\tCongratulations, Mr Stark. You have completed task " + index);
        System.out.println("\t" + printTask());
    }

    public void unmark(int index) {
        this.done = false;
        System.out.println("\tjarvis.Task " + index + " has been unmarked.");
        System.out.println("\t" + printTask());
    }

    public int getId() {
        return this.id;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getTask() {
        return this.task;
    }

    public boolean hasTask(String task) {
        return (this.task.contains(task));
    }
}
