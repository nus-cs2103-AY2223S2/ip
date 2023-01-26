package kira.task;

public abstract class Task {
    private String data;
    private boolean isDone;

    public Task(String data) {
        this.data = data;
        this.isDone = false;
    }

    /**
     * Marks the task as completed
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as incompleted
     */
    public void unmark() {
        this.isDone = false;
    }

    public String saveFormat() {
        String[] saveString = new String[] {this.data, this.isDone ? "y" : "n"};
        return String.join("\",\"", saveString);
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        String done = this.isDone ? "x" : " ";
        sBuilder.append("[" + done + "] ")
                .append(this.data);
        return sBuilder.toString();
    }
}
