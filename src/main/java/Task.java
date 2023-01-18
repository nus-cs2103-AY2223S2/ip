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

    void markUndone(){
        this.done = false;
    }

    String getDoness(){
        return done ? "X" : " ";
    }
}
