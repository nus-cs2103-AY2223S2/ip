public class Task {
    String desc;
    boolean done;

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

    @Override
    public String toString() {
        return "[" + this.getDoness() + "] " + this.desc;
    }
}
