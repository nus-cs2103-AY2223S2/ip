public class ToDo extends Task {
    public ToDo(String cmd) {
        super(cmd);
    }
    /**
     * Return the String for ToDo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
