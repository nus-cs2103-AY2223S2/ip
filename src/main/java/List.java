/**
 * Encapsulation of the list containing tasks.
 */
public class List {
    //taskList containing the tasks added.
    //We assume that a maximum of 100 tasks can be added.
    private Task[] taskList = new Task[100];

    //counter to keep track of the number of items added so far
    private int count = 0;

    /**
     * Add tasks into the list and display added task when done.
     * @param desc The description of the task to be added.
     * @throws DukeException throw IncompleteDescException
     *                       if description of task is incomplete/missing,
     *                       or throw InvalidInputException
     *                       if the input command is invalid.
     */
    public void add(String desc) throws DukeException{
        Task t;
        if (desc.startsWith("deadline")) {
            int endIndex = desc.indexOf("/by");
            if (endIndex < 0) {
                throw new IncompleteDescException("Please add the due date/time!");
            }
            String name = desc.substring(8, endIndex).strip();
            String end = desc.substring((endIndex + 3)).strip();
            if (name.isBlank()) {
                throw new IncompleteDescException("The description of a deadline cannot be empty!");
            }
            if (end.isBlank()) {
                throw new IncompleteDescException("Please add the due date/time!");
            }
            t = new Deadline(name, end);
        }
        else if (desc.startsWith("event")) {
            int startIndex = desc.indexOf("/from");
            int endIndex = desc.indexOf("/to");
            if (startIndex < 0 || endIndex < 0) {
                throw new IncompleteDescException(
                        "Please make sure that the start and end date/time are not empty!");
            }
            String name = desc.substring(5, startIndex).strip();
            String start = desc.substring((startIndex + 5), endIndex).strip();
            String end = desc.substring(endIndex + 3).strip();
            if (name.isBlank()) {
                throw new IncompleteDescException("The description of an event cannot be empty!");
            }
            if (start.isBlank() || end.isBlank()) {
                throw new IncompleteDescException(
                        "Please make sure that the start and end date/time are not empty!");
            }
            t = new Event(name, start, end);
        }
        else if (desc.startsWith("todo")) {
            String name = desc.substring(4).strip();
            if (name.isBlank()) {
                throw new IncompleteDescException("The description of a todo cannot be empty!");
            }
            t = new ToDo(name);
        }
        else {
            throw new InvalidInputException("I'm sorry, there is no such command.");
        }
        this.taskList[count] = t;
        this.count++;
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + t);
        System.out.println("Now you have " + this.count + " task(s) in your list.\n");
    }

    /**
     * Mark the task at the given index as done.
     * @param index The index number of the task given.
     */
    public void mark(int index) {
        this.taskList[index-1].mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + this.taskList[index-1] + "\n");
    }

    /**
     * Mark the task at the given index as not done.
     * @param index The index number of the task given.
     */
    public void unmark(int index) {
        this.taskList[index-1].unmark();
        System.out.println("OK, I've marked this task as not done:");
        System.out.println(" " + this.taskList[index-1] + "\n");
    }

    /**
     * Print the list.
     */
    public void print() {
        if (this.count == 0) {
            System.out.println("There are no items in the list.\n");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < this.count; i++) {
            String toPrint = String.format("%d. %s", (i + 1), this.taskList[i]);
            System.out.println(toPrint);
        }
        System.out.println("");
    }
}
