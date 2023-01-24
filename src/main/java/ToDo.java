public class ToDo extends Task{
    private String type = "[T]";

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String status() {
        return type + super.status();
    }

    @Override
    public String removeTask() {
        total--;
        return "Noted I've removed this task:\n "
                + this.status() + "\n" + "Now you have "
                + super.total
                + " tasks in the list";
    }
    @Override
    public String toString(){
        return "Got it. I've added this task:\n " + this.status() + "\n" + "Now you have " + super.total + " tasks in the list";
    }
}
