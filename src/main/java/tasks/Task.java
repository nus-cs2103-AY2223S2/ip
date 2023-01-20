package tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    public void execute(){

    };

    public Task(String description){
        this.description = description;
        this.isDone = false;
    };

    public String mark(){
        isDone = !isDone;
        String response;
        if (isDone) {
            response = "Alright, I've marked this task as done!\n"
                    + this.toString();
        } else {
            response = "Aight, I marked the task as not done, but wtf did you do, un" + this.description + "?\n"
                    + this.toString();
        }
        return response;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return isDone
                ? "[X] " + this.description
                : "[ ] " + this.description;
    }
}
