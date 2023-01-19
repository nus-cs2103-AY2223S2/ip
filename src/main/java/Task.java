public class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String printTask() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    public void markTaskDone() {
        this.isDone = true;
        System.out.println("WOW you got something done! A miracle!! I've marked this task as done now, ur wlcm :)");
        System.out.println(printTask());
    }

    public void markTaskUndone() {
        this.isDone = false;
        System.out.println("Ugh fine, this task is now marked undone. >:/");
        System.out.println(printTask());
    }

}
