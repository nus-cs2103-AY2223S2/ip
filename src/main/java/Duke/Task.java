package Duke;

public class Task {
    protected String description;
    private int isDone;

    public Task(String description, Integer isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public String getType(){
        return "Duke.Task";
    }

    public String getDes(){
        return description;
    }

    public String getStatusIcon(){
        return (isDone == 1? "[X]": "[ ]");
    }
    public void markAsDone(){
        isDone = 1;
    }
    public void unMark(){
        isDone = 0;
    }

    public String dataFormat() {
        return "not yet";
    }


    public String toString(){
        return this.getStatusIcon() + " " + this.getDes();
    }
}
