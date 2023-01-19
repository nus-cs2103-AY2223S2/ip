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
        if (isDone) {
            String response = "Alright, I've marked this task as done!\n"
                    + this.toString();
            return response;
        } else {
            String response = "Aight, I marked the task as not done, but wtf did you do, un" + this.description + "?\n"
                    + this.toString();
            return response;
        }
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
