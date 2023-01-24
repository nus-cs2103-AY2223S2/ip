public class Task {
    private static int numTasks = 0;
    private String description;
    private boolean isDone;
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }
    public static void addTask(){
        numTasks++;
    }
    public static void deleteTask(){
        numTasks--;
    }
    public static int getNumTasks(){
        return numTasks;
    }
    public String getStatusIcon(){
        return (isDone ? "X" : " "); //mark done task with X
    }
    public void setIsDone(boolean done){
        this.isDone = done;
    }
    public boolean getIsDone(){
        return this.isDone;
    }
    public String getDescription(){
        return this.description;
    }
    @Override
    public String toString() {
        return description;
    }
}
