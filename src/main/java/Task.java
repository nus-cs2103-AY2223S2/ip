public abstract class Task {
    private boolean done;
    private String task;

    public Task(String name, boolean done){
        this.done = done;
        this.task = name;
    }

    public void mark(){
        this.done = true;
    }

    public void unmark(){
        this.done = false;
    }

    public boolean getDone(){return this.done;}

    public String getTask(){return this.task;}

    public abstract String summary();

    @Override
    public String toString() {
        String checkmark = this.done ? "✓" : " ";
        return String.format("[ %s ] %s", checkmark, this.task);
    }
}
