public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getType(){
        return "Task";
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

    public String dataFormat() {
        return "not yet";
    }


    public String toString(){
        return this.getStatusIcon() + " " + this.getDes();
    }
}
