public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.getDescription();
    }

    public static ToDo addToDo(String description) {
        ToDo t = new ToDo(description);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        return t;
    }
}