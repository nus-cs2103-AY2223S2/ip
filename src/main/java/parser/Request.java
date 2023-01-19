package parser;

import command.Command;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;
import dukeexeption.UnknownCommandException;

public class Request {
    private final String request;
    private String requestType;
    private String requestContent;

    public Request(String request) {
        this.request = request;
    }

    /**
     * Parse the request string to return an executable command.
     * 
     * @return A corresponding command.
     */
    public Command parse() throws UnknownCommandException, MissingArgumentException, InvalidArgumentException {
        preprocess();
        switch (this.requestType) {
            case "LIST":
                return new ListParser().parse(this.requestContent);
            case "MARK":
                return new MarkParser().parse(this.requestContent);
            case "UNMARK":
                return new UnmarkParser().parse(this.requestContent);
            case "TODO":
                return new TodoParser().parse(this.requestContent);
            case "DEADLINE":
                return new DeadlineParser().parse(this.requestContent);
            case "EVENT":
                return new EventParser().parse(this.requestContent);
            case "DELETE":
                return new DeleteParser().parse(this.requestContent);
            case "ON":
                return new OnParser().parse(this.requestContent);
            default:
                throw new UnknownCommandException();
        }
    }

    private void preprocess() {
        this.requestType = this.request.split(" ", 2)[0].toUpperCase();

        this.requestContent = this.request.split(" ", 2).length == 2
                ? this.request.split(" ", 2)[1]
                : "";
    }
}
