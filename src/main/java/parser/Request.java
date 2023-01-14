package parser;

import command.Command;

public class Request {
    private final String request;
    private String requestType;
    private String requestContent;

    public Request(String request) {
        this.request = request;
    }

    /**
     * Parse the request string to return an executable command.
     * @return  A corresponding command.
     */
    public Command parse() {
        preprocess();
        switch (this.requestType) {
            case "list":
                return new ListParser().parse(this.requestContent);
            case "mark":
                return new MarkParser().parse(this.requestContent);
            case "unmark":
                return new UnmarkParser().parse(this.requestContent);
            case "todo":
                return new TodoParser().parse(this.requestContent);
            case "deadline":
                return new DeadlineParser().parse(this.requestContent);
            case "event":
                return new EventParser().parse(this.requestContent);
            default:
                return null;
        }
    }

    private void preprocess() {
        this.requestType = this.request.split(" ", 2)[0];

        this.requestContent = this.request.split(" ", 2).length == 2
                ? this.request.split(" ", 2)[1]
                : "";
    }
}
