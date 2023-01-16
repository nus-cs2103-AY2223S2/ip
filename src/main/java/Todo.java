public class Todo extends Task {

    public Todo(int id, String task) {
        super(id, task);
    }

    @Override
    public String printTask() {
        return this.isDone() ? "[T][x] " + this.getTask(): "[T][ ] " + this.getTask();
    }
}
