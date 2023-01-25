package request;

import java.util.Arrays;
import dukeexception.RequestException;

public class Request {
    public enum Commands {
        LIST, UNMARK, MARK, TODO, DEADLINE, EVENT, DELETE, BYE
    };

    String value;
    Commands command;

    public Request(Commands command, String request) {
        this.value = request;
        this.command = command;
    }

    public Request(String request) throws RequestException {
        String cmd = request.split(" ")[0];

        switch (cmd) {
        case "list":
            this.command = Commands.LIST;
            break;
        case "unmark":
            this.command = Commands.UNMARK;
            break;
        case "mark":
            this.command = Commands.MARK;
            break;
        case "todo":
            this.command = Commands.TODO;
            break;
        case "deadline":
            this.command = Commands.DEADLINE;
            break;
        case "event":
            this.command = Commands.EVENT;
            break;
        case "delete":
            this.command = Commands.DELETE;
            break;
        case "bye":
            this.command = Commands.BYE;
            break;
        default:
            throw new RequestException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public Commands getCommand() {
        return this.command;
    }

    public String[] unwrap() throws RequestException {
        String[] values = this.value.split(" ");
        String description = String.join(" ", Arrays.copyOfRange(values, 1, values.length));

        if (this.command != Commands.LIST && description.isEmpty()) {
            throw new RequestException("Command description cannot be empty!");
        }

        return new String[] { description };
    }

    public void checkRequestRequirement() throws RequestException {
    }
}
