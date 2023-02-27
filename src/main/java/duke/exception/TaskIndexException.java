package duke.exception;

public class TaskIndexException extends CommandException {
    private int max;
    private String string = "Sorry! Index is out of bounds...";
    public TaskIndexException() {
    }

    public TaskIndexException(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        String result = this.string;
        if (this.max != 0) {
            result += "\nMaximum value: " + this.max;
        }
        return result;
    }

    @Override
    public void printStackTrace() {
        this.string = this.toString();
        System.out.println(this.string);
    }
}
