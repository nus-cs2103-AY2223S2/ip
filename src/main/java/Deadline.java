public class Deadline extends Task{
    public Deadline(String description) {
        super(description);
        symbol = 'D';
        String[] strArr= description.split("/");
        String[] dueArr = strArr[1].split(" ", 2);
        String due = "(" + dueArr[0] + ": " + dueArr[1] + ")";
        this.description = strArr[0] + due;
    }
}