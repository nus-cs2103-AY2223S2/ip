public class Deadline extends Task {
    private String type = "[D]";

    public Deadline(String name) {
        super(name);
    }

    @Override
    public String status() {
        return type + super.status();
    }

    @Override
    public String remove() {
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
