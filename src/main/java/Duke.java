import java.util.*;
public class Duke {
    protected enum CommandEnum {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    /**
     * Instantiates a new ToDo task and returns it.
     * @param inputArr An array containing the input by the user.
     * @return Returns the new ToDo task.
     * @throws DukeException If name of the task is not given.
     */
    private static ToDo getTodo(String[] inputArr) throws DukeException {
        if (inputArr.length <= 1 || inputArr[1].isBlank()) {
            throw new IncompleteDescException("The description of a todo cannot be empty!");
        }
        return new ToDo(inputArr[1]);
    }

    /**
     * Instantiate a new Deadline object and returns it.
     * @param inputArr The input given by the user.
     * @return Returns the new Deadline object.
     * @throws DukeException If name or due date/time is not provided.
     */
    private static Deadline getDeadline(String[] inputArr) throws DukeException {
        if (inputArr.length <= 1) {
            throw new IncompleteDescException("The description of a deadline cannot be empty!");
        }
        int endIndex = inputArr[1].indexOf("/by");
        if (endIndex < 0) {
            throw new IncompleteDescException("Please add the due date/time!");
        }
        String name = inputArr[1].substring(0, endIndex).strip();
        String end = inputArr[1].substring((endIndex + 3)).strip();
        if (name.isBlank()) {
            throw new IncompleteDescException("The description of a deadline cannot be empty!");
        }
        if (end.isBlank()) {
            throw new IncompleteDescException("Please add the due date/time!");
        }
        return new Deadline(name, end);
    }

    /**
     * Instantiate a new Event object and returns it.
     * @param inputArr The input given by the user.
     * @return Returns the new Event.
     * @throws DukeException If name or start date/time or due date/time is not given.
     */
    private static Event getEvent(String[] inputArr) throws DukeException {
        if (inputArr.length <= 1) {
            throw new IncompleteDescException("The description of an event cannot be empty!");
        }
        int startIndex = inputArr[1].indexOf("/from");
        int endIndex = inputArr[1].indexOf("/to");
        if (startIndex < 0 || endIndex < 0) {
            throw new IncompleteDescException(
                    "Please make sure that the start and end date/time are not empty!");
        }
        String name = inputArr[1].substring(0, startIndex).strip();
        String start = inputArr[1].substring((startIndex + 5), endIndex).strip();
        String end = inputArr[1].substring(endIndex + 3).strip();
        if (name.isBlank()) {
            throw new IncompleteDescException("The description of an event cannot be empty!");
        }
        if (start.isBlank() || end.isBlank()) {
            throw new IncompleteDescException(
                    "Please make sure that the start and end date/time are not empty!");
        }
        return new Event(name, start, end);
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Storage storage = new Storage();
        TaskList myList = new TaskList(storage.read());
        System.out.println("Hi! I am Duke. How may I help you today?");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ", 2);
            CommandEnum cmd;
            try {
                cmd = CommandEnum.valueOf(inputArr[0].toUpperCase());
            }
            catch (IllegalArgumentException i){
                System.out.println("OPPS! I'm sorry, there is no such command.\nPlease try again.\n");
                continue;
            }
            switch(cmd) {
                case BYE:
                    storage.write(myList.getTaskList());
                    System.out.println("Bye! Hope to see you again soon!\n");
                    return;

                case LIST:
                    myList.print();
                    break;

                case MARK:
                    if (inputArr.length <= 1) {
                        System.out.println("Please give the index of the item to be marked.");
                        continue;
                    }
                    try {
                        myList.mark(Integer.parseInt(inputArr[1]) - 1);
                    }
                    catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;

                case UNMARK:
                    if (inputArr.length <= 1) {
                        System.out.println("Please give the index of the item to be unmarked.");
                        continue;
                    }
                    try {
                        myList.unmark(Integer.parseInt(inputArr[1]) - 1);
                    }
                    catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;

                case DELETE:
                    if (inputArr.length <= 1) {
                        System.out.println("Please give the index of the item to be deleted.");
                        continue;
                    }
                    try {
                        myList.delete(Integer.parseInt(inputArr[1]) - 1);
                    }
                    catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;

                case TODO:
                    Task t;
                    try {
                        t = Duke.getTodo(inputArr);
                        myList.add(t);
                    }
                    catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;

                case DEADLINE:
                    Deadline d;
                    try {
                        d = Duke.getDeadline(inputArr);
                        myList.add(d);
                    }
                    catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;

                case EVENT:
                    Event ev;
                    try {
                        ev = Duke.getEvent(inputArr);
                        myList.add(ev);
                    }
                    catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;

                default:
                    System.out.println("OPPS! I'm sorry, there is no such command.\nPlease try again.\n");
                    break;
            }
        }
    }
}