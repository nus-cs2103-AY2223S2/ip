public class Task {
    protected String description;
    protected boolean isDone;
    protected TypeOfTask type;

    public Task(String description, TypeOfTask type){
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

    public void markAsDone(){
        this.isDone = true;
    }

    public void unmarkAsDone(){
        this.isDone = false;
    }

    public void setTypeOfTask(TypeOfTask type){
       switch(type){
           case todo:
               this.type = TypeOfTask.todo;
               break;
           case deadline:
               this.type = TypeOfTask.deadline;
               break;
           case event:
               this.type = TypeOfTask.event;
               break;
           default:
               // do nothing
       }

    }

    public String getTypeOfTask(){
        switch(this.type){
            case todo:
                return "T";
            case deadline:
                return "D";
            case event:
                return "E";
            default:
                // do nothing
                return null;
        }
    }
}
