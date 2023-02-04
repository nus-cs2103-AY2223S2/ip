package roody;
public abstract class Task {
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
    public char getType() {
        return 'a';
    }
    public String saveTask() {
        return this.description + '|' + this.done;
    }
    @Override
    public String toString(){
        return this.description;
    }
}
