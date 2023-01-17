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
        Chattime.replyUser(String.format("Congrats! You've done this job:\n       %s", this));
    }

    public void unmarkDone() {
        this.isDone = false;
        Chattime.replyUser(String.format("Arghh! This job is not done yet:\n       %s", this));
    }

    public void printAddTask() {
        Chattime.replyUser(String.format("Got it! I've added this task:\n       %s\n     %s", this, totalTask()));
    }

    public void removeTask() {
        count--;
        Chattime.replyUser(
                String.format("Okay!!! I've removed this task for you:\n       %s\n     %s", this, totalTask()));
    }

    public static String totalTask() {
        return "Now you have " + count + " tasks in the list.";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
