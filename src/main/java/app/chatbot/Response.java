package app.chatbot;

/**
 * Helper class for formatting GUI responses.
 */
public class Response {
    private final boolean isSuccess;
    private StringBuilder sb;

    public Response(boolean isSuccess) {
        this.sb = new StringBuilder();
        this.isSuccess = isSuccess;
    }

    public Response(String s, boolean isSuccess) {
        this.sb = new StringBuilder(s);
        sb.append(System.lineSeparator());
        this.isSuccess = isSuccess;
    }

    /**
     * Adds a line separator to a String. Should be used whenever a
     * message is delivered to the user.
     * @param input
     */
    public Response addLine(String input) {
        this.sb.append(input);
        this.sb.append(System.lineSeparator());
        return this;
    }

    public Response addBlankLine() {
        this.sb.append(System.lineSeparator());
        return this;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    @Override
    public String toString() {
        return this.sb.toString();
    }
}
