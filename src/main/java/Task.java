public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
        Chattime.replyUser(String.format("Congrats! You've done this job:\n       %s", this.checkItem()));
    }

    public void unmarkDone() {
        this.isDone = false;
        Chattime.replyUser(String.format("Arghh! This job is not done yet:\n       %s", this.checkItem()));
    }

    public String checkItem() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
