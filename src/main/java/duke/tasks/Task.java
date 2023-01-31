package duke.tasks;

public class Task {

    String name;
    boolean completionStatus;

    static String type;

    public Task(String name) {
        this.name = name;
        this.completionStatus = false;
    }

    //getter for completion status

    public boolean getCompletionStatus() {
        return this.completionStatus;
    }

    //getter for the name of the duke.tasks

    public String getName() {
        return this.name;
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
