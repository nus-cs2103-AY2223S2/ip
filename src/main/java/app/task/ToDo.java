package app.task;

public class ToDo extends Task {

    ToDo(String description) throws InvalidInputException {
        super(description);
        this.symbol = "T";
        this.taskType = TaskTypes.Type.TODO;
    }

    /**
     * Formats the task as data to be stored in text file.
     * @return data format recognisable by the app
     */
    @Override
    public String asDataFormat() {
        return super.asDataFormat("");
    }

    @Override
    public int compareTo(Task other) {
        int result = this.getType().compareTo(other.getType());
        if (result == 0) {
            return this.description.compareTo(other.getDesc());
        } else {
            return result;
        }
    }
}
