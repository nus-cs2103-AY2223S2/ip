public class Deadline extends Task {

    // Attributes:
    protected String byDate;

    // Constructor:
    public Deadline(String userInput) {
        // use if-else and string.contains to check if this doesn't work
        super(userInput.substring(9, userInput.indexOf("/by ") - 1)); // title
        this.byDate = userInput.substring(userInput.indexOf("/by ") + 4); // due date
    }

    // Methods:
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}
