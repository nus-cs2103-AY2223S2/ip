public class Deadline extends Task {

    protected Times ddline;
    protected String type = "[D]";

    public Deadline(String description, String by) {
        super(description);
        ddline = new Times(by);
    }

    /**
     * Adds new deadline task
     * @param input Input Chat which starts with "deadline"
     *              Format: "deadline {description} /by {time}
     *              example: deadline return book /by 2019-10-15 1530
     */
    public static Deadline createDeadline(String input) {
        int index = input.indexOf("/");
        String description = input.substring(9, index - 1);
        String by = input.substring(index + 4);
        Deadline temp = new Deadline(description,by);
        return temp;
    }
    @Override
    public String toString() {
        return type + super.toString() + " (by: " + ddline + ")";
    }

    @Override
    public String getDescriptionAndTime() {
        return description + " (by: " + ddline + ")";
    }

}
