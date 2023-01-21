public class Event extends Task {
    private String type = "[E]";

    public Event(String name) {
        super(name);
    }

    @Override
    public String status() {
        return type + super.status();
    }

    @Override
    public String toString(){
        return "Got it. I've added this task:\n " + this.status() + "\n" + "Now you have " + super.total + " tasks in the list";
    }
}
