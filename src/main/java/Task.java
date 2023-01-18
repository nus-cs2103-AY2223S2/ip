import java.util.*;

public class Task {

    String name;
    boolean completionStatus;

    public Task(String name) {
        this.name = name;
        this.completionStatus = false;
    }

    public void markAsDone() {
        this.completionStatus = true;
        return;
    }

    public void undoTask() {
        this.completionStatus = false;
        return;
    }

    @Override
    public String toString(){
        if (completionStatus == true) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
