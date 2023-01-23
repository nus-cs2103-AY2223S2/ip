package Tasks;

public abstract class Task {

    private boolean completionStatus;
    private String taskName;


    public Task(String taskName) {
        this(taskName,false);
    }

    public Task(String taskName, boolean state){
        this.completionStatus = state;
        this.taskName = taskName;
    }
    public void toggleState(){
        completionStatus = !completionStatus;
    }

    public boolean getCompletionStatus(){
        return completionStatus;
    }

    @Override
    public String toString(){
        return "[" + (completionStatus ?"X":" ") + "] " + taskName;
    }

}
