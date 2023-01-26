public class ToDos extends Task {
    public ToDos(String toDosName){
        super(toDosName);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
