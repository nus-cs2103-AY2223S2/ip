public class ToDo extends Task {

    public ToDo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        String doneString = this.getDone() ? "X" : " ";
        return String.format("[T][%s] %s", doneString, this.getTitle());
    }

}
