public class Deadline extends Task {
    private String by;

    public Deadline(String content, String by) {
        super(content);
        this.by = by;
    }

    public static Deadline create(String content) {
        /**
         * @param content what to place in this task.
         * @returns the output Deadline object.
         */
        String source = "Deadline Creation";
        String[] contentAndBy = Parser.handleMissingField(content, "/by", "by", source);

        String parsedContent = contentAndBy[0].strip();
        String by = contentAndBy[1].strip();

        Parser.handleEmptyField(parsedContent, "content", source);
        Parser.handleEmptyField(by, "by", source);

        return new Deadline(parsedContent, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
