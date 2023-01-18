public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getDes(){
        return description;
    }

    public String getStatusIcon(){
        return (isDone? "[X]": "[ ]");
    }
    public void markAsDone(){
        isDone = true;
    }
    public void unMark(){
        isDone = false;
    }

    public String toString(){
        return this.getStatusIcon() + " " + this.getDes();
    }
}
