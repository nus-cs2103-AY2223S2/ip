public class ToDo extends Task {

    ToDo(String description) {
        super(description);
        this.symbol = "T";
    }

    @Override
    public String asDataFormat() {
        return super.asDataFormat("");
    }
}
