public class RequestHandler {
    private enum RequestType {
        INDEX, CREATE
    }
    private String request;
    private RequestType requestType;
    /**
     * Constructor for the request handler.
     * @param request   the request message
     */
    public RequestHandler(String request) {
        this.request = request;
        parseRequest();
    }

    /**
     * Provides a sanitised reply
     * @return  A sanitised response string.
     */
    public String getReply() {
        return this.request;
    }

    private void parseRequest() {
        String firstWord = this.request.split(" ", 2)[0];
        if (firstWord.equals("list")) {
            this.requestType = RequestType.INDEX;
        } else {
            this.requestType = RequestType.CREATE;
        }
    }
}
