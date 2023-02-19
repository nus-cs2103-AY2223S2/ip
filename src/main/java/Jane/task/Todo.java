package jane.task;
/**
 * Makes a todo event
 */
public class Todo extends Task {
    public Todo(int num, String description) {
        super(num, description);
    }
    @Override
    public String save() {
        int i = 0;
        if (this.isDone) {
            i = 1;
        }
        return String.format("T|%d| %s", i, this.description);
    }
    @Override
    public String toString() {
        return String.format("%d. [T][%s]%s", this.num, this.getStatusIcon(), this.description);
    }


}
