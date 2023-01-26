public class ToDo extends Task {

    public ToDo(String title, boolean done) {
        super(title, done);
    }

    @Override
    public String toString() {
        String doneString = this.getDone() ? "X" : " ";
        return String.format("[T][%s] %s", doneString, this.getTitle());
    }

    public String convertToMemoryString() {
        String doneString = this.getDone() ? "1" : "0";
        return "T, " + doneString + ", " + this.getTitle();
    }

}
