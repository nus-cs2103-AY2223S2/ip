import java.time.LocalDate;

public class Task {

    private String description;
    private boolean isDone;
    private static int count = 0;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
    }

    public String getStatusIcon() {
        return isDone? "X" : " ";
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void doneMessage() {
        Chattime.replyUser(String.format("Congrats! You've done this job:\n       %s", this));
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public void notDoneMessage() {
        Chattime.replyUser(String.format("Arghh! This job is not done yet:\n       %s", this));
    }

    public static int getCount() {
        return count;
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

    public String toDataString() {
        return String.format(" @ %d @ %s", this.isDone ? 1 : 0, this.description);
    }

    public boolean onDate(LocalDate time) {
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
