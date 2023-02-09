package response;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.InvalidArgumentException;
import exception.MissingArgumentException;
import storage.Deadline;
import storage.TaskList;

/**
 * Represents a response to a deadline input
 */
public class DeadlineResponse extends Response {

    /**
     * Represents the new deadline to be created
     */
    private String deadline;

    /**
     * Creates a response with the specified new deadline
     * @param deadline New deadline
     */
    public DeadlineResponse(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Creates a new deadline in the to do list
     * @param taskList The to do list to create a new deadline in
     * @return String to indicate that a new deadline was created successfully
     */
    @Override
    public String exec(TaskList taskList) throws MissingArgumentException, InvalidArgumentException {
        // Parsing the String to get the task description and deadline
        String[] splitBy = deadline.split("/by", 2);
        String des = splitBy[0].trim();
        if (des.equals("")) {
            throw new MissingArgumentException("The description of a deadline cannot be empty.");
        } else if (splitBy.length != 2
                || splitBy[1].trim().equals("")) {
            throw new MissingArgumentException("The deadline cannot be empty."
                    + " Deadline has to be in the format of YYYY-MM-DD (e.g. 2007-12-03)");
        }
        String by = splitBy[1].trim();

        // Try to create LocalDate object from String
        LocalDate byDate;
        try {
            byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Deadline date format should be in the format "
                    + "YYYY-MM-DD (e.g. 2007-12-03)");
        }

        // Create Deadline object, add to list and print
        Deadline newD = new Deadline(des, byDate);
        taskList.createToDo(newD);
        return String.format(
                "Alright! This task has been added into the list:"
                        + "\n\t%s"
                        + "\nNow you have %d task(s) in the list.",
                newD,
                taskList.count());
    }

    /**
     * Custom equals operator to compare DeadlineResponse objects
     * @param obj The other DeadlineResponse object to compare to
     * @return boolean if the two are the same DeadlineResponse
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeadlineResponse)) {
            return false;
        }
        DeadlineResponse that = (DeadlineResponse) obj;
        return this.deadline.equals(that.deadline);
    }
}
