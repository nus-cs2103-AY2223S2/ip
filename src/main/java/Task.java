public class Task {
    String desc;
    boolean done;

    Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    void markDone(){
        this.done = true;
    }
}
