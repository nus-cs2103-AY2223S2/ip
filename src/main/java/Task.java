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

    @Override
    public String toString() {
        return this.isDone
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }
}