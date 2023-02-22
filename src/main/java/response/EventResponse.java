package response;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.InvalidArgumentException;
import exception.MissingArgumentException;
import storage.Event;
import storage.TaskList;

/**
 * Represents the response for the event task
 */
public class EventResponse extends Response {

    /**
     * Represents the new event to be created
     */
    private final String event;

    /**
     * Creates a response with the specified new event
     * @param event New event
     */
    public EventResponse(String event) {
        this.event = event;
    }

    /**
     * Creates a new event in the to do list
     * @param taskList The to do list to create a new event in
     * @return String to indicate that a new event was created successfully
     */
    @Override
    public String exec(TaskList taskList) throws MissingArgumentException, InvalidArgumentException {
        String[] parsed = parseArguments();
        String des = parsed[0];
        String from = parsed[1];
        String to = parsed[2];

        // Try to create LocalDate objects from String
        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(from);
            toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Start and end date format should be in the format "
                    + "YYYY-MM-DD (e.g. 2007-12-03)");
        }

        // Create Event object, add to list and print
        Event newEvent = new Event(des, fromDate, toDate);
        taskList.createToDo(newEvent);
        return String.format(
                "Alright! This task has been added into the list:"
                    + "\n\t%s"
                    + "\nNow you have %d task(s) in the list.",
                newEvent,
                taskList.count());
    }

    /**
     * Function to parse the arguments and check for errors before creating a new event
     * @return a String[] of the parsed arguments
     */
    public String[] parseArguments() {
        String[] splitFrom = event.split("/from", 2);
        String des = splitFrom[0].trim();
        if (des.equals("")) {
            throw new MissingArgumentException("The description of an event cannot be empty.");
        } else if (splitFrom.length != 2) {
            throw new MissingArgumentException("The start date cannot be empty."
                    + " Date has to be in the format of YYYY-MM-DD (e.g. 2007-12-03)");
        }

        String[] splitTo = splitFrom[1].split("/to", 2);
        String from = splitTo[0].trim();
        if (from.equals("")) {
            throw new MissingArgumentException("The start date cannot be empty."
                    + " Date has to be in the format of YYYY-MM-DD (e.g. 2007-12-03)");
        } else if (splitTo.length != 2
                || splitTo[1].trim().equals("")) {
            throw new MissingArgumentException("The end date cannot be empty."
                    + " Date has to be in the format of YYYY-MM-DD (e.g. 2007-12-03)");
        }
        String to = splitTo[1].trim();
        return new String[] {des, from, to};
    }

    /**
     * Custom equals operator to compare EventResponse objects
     * @param obj The other EventResponse object to compare to
     * @return boolean if the two are the same EventResponse
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EventResponse)) {
            return false;
        }
        EventResponse that = (EventResponse) obj;
        return this.event.equals(that.event);
    }
}
