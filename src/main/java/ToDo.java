public class ToDo extends Task {

    ToDo(String description) {
        super(description);
        this.symbol = "T";
    }

    ToDo(String description, String isDone) {
        super(description, isDone);
        this.symbol = "T";
    }
}
