package app.chatbot;

/**
 * Helper class for formatting GUI responses.
 */
public class Response {
    private StringBuilder sb;

    public Response() {
        this.sb = new StringBuilder();
    }

    public Response(String s) {
        this.sb = new StringBuilder(s);
        sb.append(System.lineSeparator());
    }

    /**
     * Adds a line separator to a String. Should be used whenever a
     * message is delivered to the user.
     * @param input
     */
    public void addLine(String input) {
        this.sb.append(input);
        this.sb.append(System.lineSeparator());
    }

    @Override
    public String toString() {
        return this.sb.toString();
    }
}
