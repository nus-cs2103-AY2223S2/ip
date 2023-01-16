public class Task {
    private String task;
    private boolean done;

    public Task(String command) {
        this.task = command;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString(){
        String checkbox = "[" + (done ? "X" : " ") + "]";
        return checkbox + " " + this.task;
    }
}
