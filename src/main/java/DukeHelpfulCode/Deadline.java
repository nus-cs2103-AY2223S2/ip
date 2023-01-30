package DukeHelpfulCode;

public class Deadline extends Task{
    // tasks that need to be done before a specific date/time
    private String dateTime;

    public Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    public String toString(){
        return "[D] " + super.toString() + " (by: " + this.dateTime + ")";
    }

}
