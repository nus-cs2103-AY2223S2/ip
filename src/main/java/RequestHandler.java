public class RequestHandler {
    String request;
    /**
     * Constructor for the request handler.
     * @param request   the request message
     */
    public RequestHandler(String request) {
        this.request = request;
    }

    /**
     * Provide a sanitised reply
     * @return  A sanitised response string.
     */
    public String getReply() {
        return this.request;
    }
}
