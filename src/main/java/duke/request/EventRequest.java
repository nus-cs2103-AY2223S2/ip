package request;

import java.util.Arrays;
import dukeexception.RequestException;

public class EventRequest extends Request {

    public EventRequest(String request) {
        super(Commands.TODO, request);
    }

    @Override
    public String[] unwrap() throws RequestException {
        String[] values = super.value.split(" ");

        // Throws RequestExecution if there are any issues with the request
        checkRequestRequirement();

        int fromIndex = Arrays.asList(values).indexOf("/from");
        int toIndex = Arrays.asList(values).indexOf("/to");
        String description = String.join(" ", Arrays.copyOfRange(values, 1, fromIndex));
        String from = String.join(" ", Arrays.copyOfRange(values, fromIndex + 1, toIndex));
        String to = String.join(" ", Arrays.copyOfRange(values, toIndex + 1, values.length));

        return new String[] { description, from, to };
    }

    @Override
    public void checkRequestRequirement() throws RequestException {
        String[] values = super.value.split(" ");
        String message = "";

        if (values.length <= 1) {
            message = "Description cannot be empty";
        }

        if (!super.value.contains("/from")) {
            message = "Your request must include /from";
        }

        if (!super.value.contains("/to")) {
            message = "Your request must include /to";
        }

        if (super.value.indexOf("/from") > super.value.indexOf("/to")) {
            message = "Your request must be in the following order: <Description> /from <Datetime> /to <Datetime>";
        }

        if (!message.isEmpty()) {
            throw new RequestException(message);
        }
    }
}
