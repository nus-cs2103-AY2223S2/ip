public class Todo extends Tasks {
    public Todo(String content, boolean isDone) {
        super(content, isDone);
        this.type = 'T';
    }
    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.seeTaskContent();
    }
}
