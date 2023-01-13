public class Task {
    private boolean done;
    private String task;

    public Task(String name){
        this.done = false;
        this.task = name;
    }

    public void mark(){
        this.done = true;
    }

    public void unmark(){
        this.done = false;
    }

    @Override
    public String toString() {
        String checkmark = this.done ? "✓" : " ";
        return String.format("[ %s ] %s", checkmark, this.task);
    }
}
