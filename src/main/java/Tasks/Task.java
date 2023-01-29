package Tasks;

public abstract class Task {

    private boolean completionStatus;
    protected String taskName;


    public Task(String taskName) {
        this.completionStatus = false;
        this.taskName = taskName;
    }

    public void toggleState(){
        completionStatus = !completionStatus;
    }

    public boolean getCompletionStatus(){
        return completionStatus;
    }

    public void loadCompletionStatus(String inp) {
        completionStatus = (inp == "1");
    }

    public abstract String toSaveData();


    @Override
    public String toString(){
        return "[" + (completionStatus ?"X":" ") + "] " + taskName;
    }

}
