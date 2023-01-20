public class Task {
    protected String description;
    protected boolean isDone;
    protected TypeOfTask type;

    public Task(String description,TypeOfTask type){
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public String getDescription(){
        return this.description;
    }

    public String getTypeOfTask(){
        switch(this.type){
            case deadline:
                return "D";
            case event:
                return "E";
            case todo:
                return "T";
            default:
                return null;
        }
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void unmarkAsDone(){
        this.isDone = false;
    }
}
