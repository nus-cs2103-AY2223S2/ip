public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String makeSaveFormat() {
        return String.format("T|%1$s|%2$s\n", this.isMarked ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
