package request;

import dukeexception.RequestException;

public abstract class Request {
    public enum Commands {
        LIST, UNMARK, MARK, TODO, DEADLINE, EVENT, DELETE, NULL
    };

    String value;
    Commands command;

    abstract public String[] unwrap() throws RequestException;
    abstract String checkRequestRequirement() throws RequestException;

    public Request(Commands command, String request) {
        this.value = request;
        this.command = command;
    }

    public Commands getCommand() {
        return this.command;
    }
}
