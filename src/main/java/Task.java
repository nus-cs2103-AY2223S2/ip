public class Task {
    protected String name;
    protected boolean isDone;
    protected String startDate;
    protected String endDate;

    public Task(String name, String date1, String date2) {
        this.name = name;
        this.isDone = false;
        this.startDate = date1;
        this.endDate = date2;
    }

    public void mark() {
        this.isDone = true;
    }

    
    public void unmark() {
        this.isDone = false;
    }

    public String getFileDesc() {
        return this.isDone 
        ? "1|" + this.name 
        : "0|" + this.name;
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }
}