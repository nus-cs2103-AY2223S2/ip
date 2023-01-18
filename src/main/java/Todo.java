public class Todo extends Task {
    private char type = 'T';
    public Todo(String description){
        super(description);
    }
    @Override
    public char getType() {
        return type;
    }
    @Override
    public String toString(){
        return super.toString() + "";
    }
}
