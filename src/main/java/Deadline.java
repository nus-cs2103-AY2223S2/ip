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
        String[] contentAndBy = content.split("/by");
        if (contentAndBy.length <= 1) {
            throw new InputFormatException("Deadline Creation", "'by' is missing.", null);
        }
        String parsedContent = contentAndBy[0].strip();
        String by = contentAndBy[1].strip();
        if (parsedContent.equals("")) {
            throw new InputFormatException("Deadline Creation", "Empty 'content'.", null);
        }
        if (by.equals("")) {
            throw new InputFormatException("Deadline Creation", "Empty 'by'.", null);
        }
        return new Deadline(parsedContent, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
