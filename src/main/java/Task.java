public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getSymbol() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public  String getDescription(){
        return this.description;
    }
    public void markDone(){
        this.isDone = true;
    }

    public void markNotDone(){
        this.isDone = false;
    }
    public boolean checkDone(){
        return this.isDone;
    }


    @Override
    public String toString(){
        return this.getSymbol() +" "  + this.getDescription();
    }
}
