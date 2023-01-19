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
        System.out.println("Congrats bro you've done something with your life");
        System.out.println(this.toString());
        return;
    }

    public void undoTask() {
        this.completionStatus = false;
        System.out.println("Stop being useless why u ask me to unmark");
        System.out.println(this.toString());
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
