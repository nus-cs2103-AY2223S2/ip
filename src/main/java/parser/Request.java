package parser;

import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;
import dukeexeption.UnknownCommandException;

import command.Command;

/**
 * The entry point of user input commands.
 */
public class Request {
    private String request;
    private String requestType;
    private String requestContent;

    public Request(String request) {
        this.request = request;
    }

    /**
     * Parses the request string to return an executable command.
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
        case "FIND":
            return new FindParser().parse(this.requestContent);
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
