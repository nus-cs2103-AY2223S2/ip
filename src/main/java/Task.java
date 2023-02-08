import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

<<<<<<< HEAD
    String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
        return dateTime.format(dtf);
=======
    boolean getDone() {
        return this.done;
    }

    String getDesc(){
        return this.desc;
>>>>>>> branch-Level-8
    }

    @Override
    public String toString() {
        return "[" + this.getDoness() + "] " + this.desc;
    }
}
