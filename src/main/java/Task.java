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
        String[] temp = new String[] {this.data, this.isDone ? "y" : "n"};
        return String.join("\",\"", temp);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        String done = this.isDone ? "x" : " ";
        ret.append("[" + done + "] ");
        ret.append(this.data);
        return ret.toString();
    }
}
