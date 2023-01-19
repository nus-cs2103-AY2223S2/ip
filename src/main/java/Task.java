public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsNotDone(){
        this.isDone = false;
    }


    public String getTask(){
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
    //public static void main(String[] args) {
    //    Task t = new Task("read book");
    //    t.markAsDone();
    //    t.getStatusIcon();
    //}
    
    
}