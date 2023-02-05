package app.task;

public class ToDo extends Task {

    ToDo(String description) throws InvalidInputException {
        super(description);
        this.symbol = "T";
    }

    /**
     * Formats the task as data to be stored in text file.
     * @return data format recognisable by the app
     */
    @Override
    public String asDataFormat() {
        return super.asDataFormat("");
    }
}
