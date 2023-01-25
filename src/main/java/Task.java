public class Task {
    private String desc;
    private boolean done;

    Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    void toggleDone() {
        this.done = !this.done;
    }

    String getDoness(){
        return done ? "X" : " ";
    }

    boolean getDone() {
        return this.done;
    }

    String getDesc(){
        return this.desc;
    }

    @Override
    public String toString() {
        return "[" + this.getDoness() + "] " + this.desc;
    }
}
