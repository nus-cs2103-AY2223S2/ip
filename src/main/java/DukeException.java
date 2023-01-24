public class DukeException extends Exception {
    private static final String DIVIDER_LINE = "____________________________________________________\n";
    private String description;

    public DukeException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
