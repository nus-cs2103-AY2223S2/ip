public class Task {
    private static int numTasks = 0;
    private String description;
    private boolean isDone;
    public Task(String description){
        this.description = description;
        this.isDone = false;
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
    @Override
    public String toString() {
        return description;
    }
}
