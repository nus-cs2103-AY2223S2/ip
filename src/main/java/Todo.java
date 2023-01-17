public class Todo extends Task {
    private char type = 'T';
    public Todo(String description){
        super(description);
    }
    @Override
    public char getType() {
        return type;
    }
}
