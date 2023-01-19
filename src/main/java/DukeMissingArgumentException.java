public class DukeMissingArgumentException extends DukeException {
    private String task;

    public DukeMissingArgumentException(String task){
        this.task = task;
    }

    @Override
    public String toString() {
        return String.format("%s The description of a %s cannot be empty.", super.toString(), this.task);
    }
}