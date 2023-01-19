public class Todo extends Tasks {
    public Todo(String content) {
        super(content);
        this.type = 'T';
    }
    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.seeTaskContent();
    }
}
