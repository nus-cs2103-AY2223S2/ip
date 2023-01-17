package response;

import exception.MissingArgumentException;
import storage.Deadline;
import storage.ToDoList;

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
     * @param toDoList The to do list to create a new deadline in
     * @return String to indicate that a new deadline was created successfully
     */
    @Override
    public String exec(ToDoList toDoList) {
        String[] splitBy = deadline.split(" /by ", 2);
        String des = splitBy[0].trim();
        if (des.equals("")) {
            throw new MissingArgumentException("The description of a deadline cannot be empty.");
        } else if (splitBy.length != 2
                || splitBy[1].trim().equals("")) {
            throw new MissingArgumentException("The deadline cannot be empty.");
        }
        String by = splitBy[1].trim();

        Deadline newD = new Deadline(des, by);
        toDoList.createToDo(newD);
        return String.format("Alright! This task has been added into the list:\n %s", newD);
    }
}
