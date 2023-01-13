public class Todo extends Task{
    public Todo(String name){
        super(name);
    }

    @Override
    public String toString(){
        String s = "[ T ]" + super.toString();
        return s;
    }

}
