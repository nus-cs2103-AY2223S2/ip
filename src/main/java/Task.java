public abstract class Task {
    private boolean isDone = false;
    private String task;

    public Task(String title) {
        this.task = title;
    }

    public void markDone() {
        this.isDone = true;
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + this.toString());
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("    " + this.toString());
    }

    public boolean checkDone() {
        return this.isDone;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + mark + "] " + this.task;
    }
}