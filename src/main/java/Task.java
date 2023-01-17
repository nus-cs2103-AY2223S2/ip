public class Task {
    private boolean done;
    private String description;
    public Task(String description) {  
        this.done = false; 
        this.description = description;
    }
    public boolean isDone() {
        return this.done;
    }
    public void setDone() {
        this.done = true;
    }
    public void setUnDone() {
        this.done = false;
    }
    public String getDescription(){
        return this.description;
    }
}
