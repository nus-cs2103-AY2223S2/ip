public class Task {
    private static int numTasks = 0;
    protected String description;
    protected boolean isDone;
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public static void addTask(){
        numTasks++;
    }
    public static int getNumTasks(){
        return numTasks;
    }
    public String getStatusIcon(){
        return (isDone ? "X" : " "); //mark done task with X
    }
    public boolean getIsDone(){
        return isDone;
    }
    public void setIsDone(boolean done){
        this.isDone = done;
    }
    @Override
    public String toString() {
        return description;
    }
}
