public class ToDo extends Task {

    public ToDo(String description) {
        super(description);

        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        System.out.println("Now you have " + Task.noOfTasks + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}