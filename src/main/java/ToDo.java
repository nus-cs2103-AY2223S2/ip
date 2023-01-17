public class ToDo extends Task{
    public ToDo(String description){
        super(description);
        super.isDone = false;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
