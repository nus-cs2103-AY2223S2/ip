public class Todo extends Task {

    public Todo(String str1) {
        super(str1);
    }
    String[] mainSplit = super.task_name.split(" ", 2);
    String name = mainSplit[1];


    @Override
    public String toString() {
        if(super.status == true) {
            return "[T][X] " + name;
        }
        else {
            return "[T][ ] " + name;
        }
    }


}
