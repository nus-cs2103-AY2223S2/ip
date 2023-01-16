public class Todo extends Task {

    Todo(String desc) {
        super(desc);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
