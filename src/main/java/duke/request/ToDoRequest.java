package request;

import java.util.Arrays;
import dukeexception.RequestException;

public class ToDoRequest extends Request {

    public ToDoRequest(String request) {
        super(Commands.TODO, request);
    }

    @Override
    public String[] unwrap() throws RequestException {
        String[] values = super.value.split(" ");

        // Throws RequestExecution if there are any issues with the request
        checkRequestRequirement();

        String description = String.join(" ", Arrays.copyOfRange(values, 1, values.length));
        return new String[] { description };
    }

    @Override
    public void checkRequestRequirement() throws RequestException {
        String message = "";

        if (super.value.split(" ").length <= 1) {
            message = "Description cannot be empty";
        }

        if (!message.isEmpty()) {
            throw new RequestException(message);
        }
    }
}
