public class Todo extends Task {
    Todo(String type, String detail, boolean marked){
        super(type, detail, marked);
    }

    Todo(String type, String detail){
        super(type, detail);
    }

    @Override
    public String toString(){
        if (marked) {
            return "[T][X] " + super.detail;
        } else {
            return "[T][ ] " + super.detail;
        }
    }
}
