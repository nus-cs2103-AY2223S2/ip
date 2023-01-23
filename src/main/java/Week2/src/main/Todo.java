package Week2.src.main;
public class Todo extends Task {

    Todo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return "[T][" + this.getDone() + "] " +this.content;
    }
}
