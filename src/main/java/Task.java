public class Task {

    protected String description;
    protected boolean isDone;
    protected static int count = 0;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
    }

    public String getStatusIcon() {
        return isDone? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
        Chattime.replyUser(String.format("Congrats! You've done this job:\n       %s", toString()));
    }

    public void unmarkDone() {
        this.isDone = false;
        Chattime.replyUser(String.format("Arghh! This job is not done yet:\n       %s", toString()));
    }

    public static String totalTask() {
        return "Now you have " + count + " tasks in the list.";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
