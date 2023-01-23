package duke.task;

public class Task {
    private String content;
    boolean done = false;

    Task(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        String mark = this.getDoneStatus() ? "X" : " ";
        return String.format("[%s] %s", mark, this.content);
    }

    public boolean getDoneStatus() {
        return this.done;
    }

    public void setDoneStatus(boolean status) {
        this.done = status;
    }

    public String getContent() {
        return this.content;
    }
}
