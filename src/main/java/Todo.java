public class Todo extends Task {

    //default constructor
    public Todo(String description){
        super(description,TypeOfTask.todo);
    }

    @Override
    public String toString(){
        return String.format("[T][ ] %s", super.getDescription());
    }
}
