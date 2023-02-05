package roody;
public class Todo extends Task {
    private char type = 'T';
    public Todo(String description){
        super(description);
    }
    @Override
    public String saveTask(){
        return super.saveTask() + '|' + this.type;
    }
    @Override
    public char getType() {
        return type;
    }
    @Override
    public String toString(){
        char done = ' ';
        if (isDone()) done = 'X';
        return "[T][" + done + "] " + super.toString() + "";
    }
}
