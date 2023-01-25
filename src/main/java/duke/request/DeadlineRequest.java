package request;

import java.util.Arrays;
import dukeexception.RequestException;

public class DeadlineRequest extends Request {

    public DeadlineRequest(String request) {
        super(Commands.TODO, request);
    }

    @Override
    public String[] unwrap() throws RequestException {
        String[] values = super.value.split(" ");

        // Throws RequestExecution if there are any issues with the request
        checkRequestRequirement();

        int byIndex = Arrays.asList(values).indexOf("/by");
        String description = String.join(" ", Arrays.copyOfRange(values, 1, byIndex));
        String by = String.join(" ", Arrays.copyOfRange(values, byIndex + 1, values.length));

        return new String[] { description, by };
    }

    @Override
    public void checkRequestRequirement() throws RequestException {
        String[] values = super.value.split(" ");
        String message = "";

        if (values.length <= 1) {
            message = "Description cannot be empty";
        }

        if (!super.value.contains("/by")) {
            message = "Your request must include /by";
        }

        if (!message.isEmpty()) {
            throw new RequestException(message);
        }
    }
}
