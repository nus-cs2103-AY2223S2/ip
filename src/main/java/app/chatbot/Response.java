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

    public void addLine(String input) {
        this.sb.append(input);
        this.sb.append(System.lineSeparator());
    }

    @Override
    public String toString() {
        return this.sb.toString();
    }
}
